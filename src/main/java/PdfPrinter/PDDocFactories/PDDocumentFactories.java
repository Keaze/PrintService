package PdfPrinter.PDDocFactories;

import java.nio.file.Path;

public class PDDocumentFactories {
    public static PDDocumentFactory get(byte[] bytes) {
        return new PddFromBytes(bytes);
    }

    public static PDDocumentFactory get(Path path) {
        return new PddFromFile(path.toString());
    }
}
