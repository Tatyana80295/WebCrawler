/**
 * <p>Менеджер задач.</p>
 */
public interface PageTaskManager {
    /**
     * <p>Получить задачу для обработки</p>
     *
     * @return ссылка для скачивания
     */
    String getTask();
    /**
     * <p>Вернуть задачу, обратно в очередь</p>
     *
     * @param link Ссылка
     */
    void uncompleteTask(String link);
    /**
     * <p>Пометить задачу, как завершенную. Положить список новых ссылок в очередь</p>
     *
     * @param link Ссылка
     * @param newLinks Список новых ссылок
     */
    void completeTask(String link, String[] newLinks);
}
