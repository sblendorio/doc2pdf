package eu.sblendorio.doc2pdf;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

public class MainDoc2Pdf {

    public static void main(String[] args) {

        try {
            System.out.println("START");
            InputStream templateInputStream = new FileInputStream("/Users/sblendorio/Documents/docsample.doc");
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);

            String outputfilepath = "/Users/sblendorio/Documents/pdfsample.pdf";
            FileOutputStream os = new FileOutputStream(outputfilepath);
            Docx4J.toPDF(wordMLPackage,os);
            os.flush();

            os.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}