package PdfPrinter;

import PdfPrinter.PDDocFactories.PDDocumentFactories;
import PdfPrinter.PDDocFactories.PDDocumentFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfPrinter {

    private final String printerName;

    private PdfPrinter(String printerName) {
        this.printerName = printerName;
    }

    public static void main(String[] args) throws Exception {
        final String printerName = "Hewlett-Packard HP LaserJet M402dn @ raspberrypi";
        final Path path = Paths.get("C:\\Users\\daniel\\Downloads\\Planungsabstract_Lernvideos.pdf");
        PdfPrinter printer = createPdfPrinter(printerName);
        final PDDocumentFactory pdfFactory = PDDocumentFactories.get(Files.readAllBytes(path));
        printer.print(pdfFactory);

    }

    public static PdfPrinter createPdfPrinter(String printerName) {
        return new PdfPrinter(printerName);
    }

    public void print(PDDocumentFactory pdfFactory) {
        try (PDDocument document = pdfFactory.getPDDocument()) {
            PrintService myPrintService = findPrintService();
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(document));
            job.setPrintService(myPrintService);
            job.print();
        } catch (IOException | PrinterException e) {
            e.printStackTrace();
        }
    }

    private PrintService findPrintService() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }

    public String getPrinterName() {
        return printerName;
    }
}
