package eu.sblendorio.doc2pdf;

import com.pd4ml.PD4ML;
import com.pd4ml.PageMargins;
import com.pd4ml.PageSize;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainPd4Mlv4 {
    public static void main(String[] args) throws Exception {
        PD4ML pd4ml = new PD4ML();
        boolean pdfLandscape = false;
        pd4ml.setPageSize(pdfLandscape ? new PageSize(842, 595) : new PageSize(595, 842));
        pd4ml.setPageMargins(new PageMargins(0, 0, 0, 0));
        pd4ml.setHtmlWidth(1190);
        pd4ml.useTTF("/Users/sblendorio/tmp/fonts");

        String html = Files.readString(Paths.get("/Users/sblendorio/tmp/ciccio.html"));
        ByteArrayInputStream bais = new ByteArrayInputStream(html.getBytes());

        // read and parse HTML
        pd4ml.readHTML(bais);
        File pdf = File.createTempFile("result", ".pdf");
        FileOutputStream fos = new FileOutputStream(pdf);

        // render and write the result as PDF
        pd4ml.writePDF(fos);

        // alternatively or additionally:
        // pd4ml.writeRTF(rtfos, false);
        // BufferedImage[] images = pd4ml.renderAsImages();
        // open the just-generated PDF with a default PDF viewer
        Desktop.getDesktop().open(pdf);
    }
}
