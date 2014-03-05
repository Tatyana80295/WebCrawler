import java.io.File;
import java.nio.charset.Charset;
import java.util.UUID;

public class WebCrawlerImpl implements WebCrawler {

    private final PageTaskManager pageTaskManager;
    private final PageDownloader pageDownloader;
    private final EncodingDetector encodingDetector;
    private final EncodingConverter encodingConverter;
    private final PageParser pageParser;
    private final PageFileManager pageFileManager;
    private static final Charset utf8Charset = Charset.forName("utf-8");


    public WebCrawlerImpl(PageTaskManager pageTaskManager, PageDownloader pageDownloader, EncodingDetector encodingDetector,
                          EncodingConverter encodingConverter, PageParser pageParser, PageFileManager pageFileManager){

        this.pageTaskManager = pageTaskManager;
        this.pageDownloader = pageDownloader;
        this.encodingDetector = encodingDetector;
        this.encodingConverter = encodingConverter;
        this.pageParser = pageParser;
        this.pageFileManager = pageFileManager;
    }

    @Override
    public void run()  {
        while(true) {

            String link = pageTaskManager.getTask();
            try {
                if (link != null) {
                    if (!tryRunLink(link)) {
                        pageTaskManager.uncompleteTask(link);
                    }
                }
            }
            catch (RuntimeException e) {
                pageTaskManager.uncompleteTask(link);
                throw e;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean tryRunLink(String link) {

        String htmlFile = getTempFileName();
        if (!pageDownloader.tryDownloadHtml(link, htmlFile))
            return false;

        Charset pageEncoding = encodingDetector.detectEncoding(htmlFile);
        if (!pageEncoding.equals(utf8Charset)) {
            String convertedFile = getTempFileName();
            if (!encodingConverter.tryConvert(htmlFile, convertedFile, pageEncoding.toString(), utf8Charset.toString())) {
                deleteFile(htmlFile);
                return false;
            }

            deleteFile(htmlFile);
            htmlFile = convertedFile;
        }
        String[] nextLinks = pageParser.getLinks(htmlFile);
        String textFile = getTempFileName();
        if (!pageParser.tryStripTags(htmlFile, textFile)){
            deleteFile(htmlFile);
            return false;
        }

        deleteFile(htmlFile);
        if (!pageFileManager.trySavePage(link, textFile)) {
            deleteFile(textFile);
            return false;
        }

        pageTaskManager.completeTask(link, nextLinks);
        return true;

    }

    private static void deleteFile(String fileName) {
        if (!new File(fileName).delete())
            throw new RuntimeException("Cannot delete file " + fileName);
    }

    private static String getTempFileName() {
        return UUID.randomUUID().toString();
    }
}
