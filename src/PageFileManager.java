/**
 * <p>Хранилище сохраненных страниц</p>
 */
public interface PageFileManager {

    /**
     * <p>Сохранить загруженную страницу</p>
     *
     * @param link Ссылка
     * @param pageFile Имя файла
     * @return true если удалось сохранить страницу
     */
    boolean trySavePage(String link, String pageFile);
    /**
     * <p>Получить содержимое ранее сохранненной страницы</p>
     *
     * @param link Ссылка
     * @return содержимое страницы, null если страница не была ранее сохранена
     */
    String getPage(String link);
    /**
     * <p>Получить список ссылок ранее сохранненных страниц</p>
     *
     * @return массив ссылок
     */
    String[] listPages();
}
