package eu.sblendorio.doc2pdf;

import org.zefer.pd4ml.PD4ML;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainPd4Mlv3 {
    public static void main(String[] args) throws Exception {
        PD4ML pd4ml = new PD4ML();
        boolean pdfLandscape = false;
        pd4ml.setPageSize(pdfLandscape ? new Dimension(842, 595) : new Dimension(595, 842));
        pd4ml.setPageInsets(new Insets(0, 0, 0, 0));
        pd4ml.setHtmlWidth(1190);
        pd4ml.useTTF("/Users/sblendorio/tmp/fonts", true);

        String html = Files.readString(Paths.get("/Users/sblendorio/tmp/ciccio.html"));
        ByteArrayInputStream bais = new ByteArrayInputStream(html.getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pd4ml.render(new InputStreamReader(new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8))), baos);


        // read and parse HTML
        File pdf = File.createTempFile("result", ".pdf");
        FileOutputStream fos = new FileOutputStream(pdf);

        Files.write(pdf.toPath(), baos.toByteArray());

        Desktop.getDesktop().open(pdf);
    }
}
