package PdfPrinter.PDDocFactories;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class PddFromFile implements PDDocumentFactory {
    private final String path;

    public PddFromFile(String path) {
        this.path = path;
    }

    @Override
    public PDDocument getPDDocument() {
        try {
            return PDDocument.load(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
