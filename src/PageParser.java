/**
 * <p>Операции над HTML страницами</p>
 */
public interface PageParser {
    /**
     * <p>Убрать HTML теги из файла.</p>
     *
     * @param htmlFile Имя исходного файла
     * @param textFile Имя выходной файла
     * @return true если удалось сохранить выходной файл
     */
    boolean tryStripTags(String htmlFile, String textFile);
    /**
     * <p>Получить список ссылок находящихся на HTML странице</p>
     *
     * @return массив ссылок
     */
    String[] getLinks(String htmlFile);
}
