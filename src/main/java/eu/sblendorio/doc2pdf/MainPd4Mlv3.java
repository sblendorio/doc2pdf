package eu.sblendorio.doc2pdf;

import org.apache.commons.lang3.math.NumberUtils;
import org.zefer.pd4ml.PD4ML;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MainPd4Mlv3 {

    public static void main(String[] args) throws Exception {
        int times = args == null || args.length == 0 ? 1 : NumberUtils.toInt(args[0]);
        String html = Files.readString(Paths.get("src/main/resources/ciccio.html"));
        byte[] pdf = null;
        long start = System.currentTimeMillis();

        for (int i=0; i < times; i++)
            pdf = html2pdf(html);

        long stop = System.currentTimeMillis();
        long duration = stop - start;

        System.out.println("Duration v3 = " + duration);

    }

    private static byte[] html2pdf(String html) throws Exception {
        PD4ML pd4ml = new PD4ML();
        boolean pdfLandscape = false;
        pd4ml.setPageSize(pdfLandscape ? new Dimension(842, 595) : new Dimension(595, 842));
        pd4ml.setPageInsets(new Insets(0, 0, 0, 0));
        pd4ml.setHtmlWidth(1190);
        pd4ml.useTTF("src/main/resources/fonts", true);
        try (var bais = new ByteArrayInputStream(html.getBytes(UTF_8));
             var baos = new ByteArrayOutputStream()) {
            pd4ml.render(new InputStreamReader(bais), baos);
            return baos.toByteArray();
        }
    }

    public static void main2(String[] args) throws Exception {
        PD4ML pd4ml = new PD4ML();
        boolean pdfLandscape = false;
        pd4ml.setPageSize(pdfLandscape ? new Dimension(842, 595) : new Dimension(595, 842));
        pd4ml.setPageInsets(new Insets(0, 0, 0, 0));
        pd4ml.setHtmlWidth(1190);
        pd4ml.useTTF("src/main/resources/fonts", true);

        String html = Files.readString(Paths.get("/Users/sblendorio/tmp/ciccio.html"));
        ByteArrayInputStream bais = new ByteArrayInputStream(html.getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pd4ml.render(new InputStreamReader(new ByteArrayInputStream(html.getBytes(UTF_8))), baos);


        // read and parse HTML
        File pdf = File.createTempFile("result", ".pdf");
        FileOutputStream fos = new FileOutputStream(pdf);

        Files.write(pdf.toPath(), baos.toByteArray());

        Desktop.getDesktop().open(pdf);
    }
}
