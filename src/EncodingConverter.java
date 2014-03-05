/**
 * <p>Выполняет трансформацию кодировок.</p>
 */
public interface EncodingConverter {

    /**
     * <p>Выполнить копирование указанного текстового файла, выполняя трансформацию кодировки.</p>
     *
     * @param inputFile Имя исходного файла
     * @param outputFile Имя выходной файла
     * @param sourceEncoding  Имя исходной кодировки
     * @param destEncoding  Имя выходной  кодировки
     * @return true если выходной файл успешно сохранен
     */
    boolean tryConvert(String inputFile, String outputFile, String sourceEncoding, String destEncoding);
}
