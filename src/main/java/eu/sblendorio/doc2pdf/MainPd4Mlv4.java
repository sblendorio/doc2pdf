package eu.sblendorio.doc2pdf;

import com.pd4ml.PD4ML;
import com.pd4ml.PageMargins;
import com.pd4ml.PageSize;
import org.apache.commons.lang3.math.NumberUtils;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MainPd4Mlv4 {

    public static void main(String[] args) throws Exception {
        int times = args == null || args.length == 0 ? 1 : NumberUtils.toInt(args[0]);
        String html = Files.readString(Paths.get("src/main/resources/ciccio.html"));
        byte[] pdf = null;
        long start = System.currentTimeMillis();

        for (int i=0; i < times; i++)
            pdf = html2pdf(html);

        long stop = System.currentTimeMillis();
        long duration = stop - start;

        System.out.println("Duration v4 = " + duration);
    }

    private static byte[] html2pdf(String html) throws Exception {
        PD4ML pd4ml = new PD4ML();
        boolean pdfLandscape = false;
        pd4ml.setPageSize(pdfLandscape ? new PageSize(842, 595) : new PageSize(595, 842));
        pd4ml.setPageMargins(new PageMargins(0, 0, 0, 0));
        pd4ml.setHtmlWidth(1190);
        pd4ml.useTTF("src/main/resources/fonts");
        try (var bais = new ByteArrayInputStream(html.getBytes(UTF_8));
             var baos = new ByteArrayOutputStream()) {
            pd4ml.readHTML(bais);
            pd4ml.writePDF(baos);
            return baos.toByteArray();
        }

    }

    public static void main2(String[] args) throws Exception {
        PD4ML pd4ml = new PD4ML();
        boolean pdfLandscape = false;
        pd4ml.setPageSize(pdfLandscape ? new PageSize(842, 595) : new PageSize(595, 842));
        pd4ml.setPageMargins(new PageMargins(0, 0, 0, 0));
        pd4ml.setHtmlWidth(1190);
        pd4ml.useTTF("src/main/resources/fonts");

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
