import java.nio.charset.Charset;

/**
 * <p>Выполняет определение кодировоки файла.</p>
 */
public interface EncodingDetector {
    /**
     * <p>Выполнить определение кодировоки файла.</p>
     *
     * @param file Имя исходного файла
     * @return Кодировка файла, null если не удалось определить кодировку
     */
    Charset detectEncoding(String file);
}
