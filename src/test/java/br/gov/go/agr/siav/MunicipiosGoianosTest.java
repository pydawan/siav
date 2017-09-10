package br.gov.go.agr.siav;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.junit.Test;

import org.pmw.tinylog.Logger;

/**
 * Testa a extração dos nomes dos municípios goianos no site do 
 * IBGE (Instituto Brasileiro de Geografia e Estatística).
 * 
 * @author thiago
 * @version 1.0
 */
public class MunicipiosGoianosTest {

    @Test
    public void test() {
        try {
            Document document = Jsoup.connect("http://cidades.ibge.gov.br/xtras/uf.php?coduf=52").get();
            Element municipiosGoianos = document.getElementById("lista_municipios");
            municipiosGoianos.select("a").forEach(link -> {
                String municipioGoiano = link.text().trim();
                Logger.info(municipioGoiano);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
