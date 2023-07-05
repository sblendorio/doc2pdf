package eu.sblendorio.doc2pdf;

public class BenchmarkHtml2Pdf {
    public static void main(String[] args) throws Exception {
        final int TIMES = 3;
        MainPd4Mlv3.main(new String[] {""+TIMES});
        MainPd4Mlv4.main(new String[] {""+TIMES});
    }
}
