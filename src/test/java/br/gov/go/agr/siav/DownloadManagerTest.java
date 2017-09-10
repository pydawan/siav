package br.gov.go.agr.siav;

import java.io.File;
import java.io.PrintWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import org.junit.Test;

import br.gov.go.agr.siav.util.DownloadManager;

/**
 * Testa o gerenciador de downloads.
 * 
 * @author thiago
 * @version 1.0
 *
 */
public class DownloadManagerTest {

    @Test
    public void test() {
        try {
            String url = "http://www.sgc.goias.gov.br/upload/arquivos/2015-04/distancia-km-rodoviaria-2015.pdf";
            DownloadManager.download(url);
            String filePath = String.format("%s%sdistancia-km-rodoviaria-2015.pdf", System.getProperty("user.home"), File.separator);
            File arquivo = new File(filePath);
            PDDocument pdf = PDDocument.load(arquivo);
            PDFTextStripper extrator = new PDFTextStripper();
            String texto = extrator.getText(pdf);
            File file = new File(filePath.replace(".pdf", ".txt"));
            PrintWriter pw = new PrintWriter(file);
            pw.write(texto);
            pw.flush();
            pw.close();
            System.out.println(texto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
