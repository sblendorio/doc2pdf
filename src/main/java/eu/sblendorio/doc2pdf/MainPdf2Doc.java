package eu.sblendorio.doc2pdf;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

public class MainPdf2Doc {
    public static void main(String[] args) {

        //Create a PdfDocument object
        PdfDocument doc = new PdfDocument();

        //Load a sample PDF document
        doc.loadFromFile("/Users/sblendorio/tmp/in-pdf.pdf");

        //Convert PDF to Doc and save it to a specified path
        doc.saveToFile("/Users/sblendorio/tmp/out-doc.doc", FileFormat.DOC);

        //Convert PDF to Docx and save it to a specified path
        doc.saveToFile("/Users/sblendorio/tmp/out-docx.docx", FileFormat.DOCX);
        doc.close();

    }
}
