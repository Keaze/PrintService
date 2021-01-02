package PdfPrinter.PDDocFactories;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;

public class PddFromBytes implements PDDocumentFactory {

    private final byte[] bytes;

    PddFromBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public PDDocument getPDDocument() {
        try {
            return PDDocument.load(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
