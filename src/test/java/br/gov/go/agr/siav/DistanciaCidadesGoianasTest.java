package br.gov.go.agr.siav;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;


/**
 * @author thiago
 * @version 1.0
 */
public class DistanciaCidadesGoianasTest {

    @Test
    public void test() {
        try {
            System.out.printf("\t\t\t\tDISTÂNCIA ENTRE AS CIDADES GOIANAS\n\n");
            Document document = Jsoup.connect("http://www.geografos.com.br/distancia-entre-cidades-goias/").get();
            Element menu = document.getElementById("menu-navegacao-abaixo");
            menu.select("a").forEach(a -> {
//                String texto = a.text().trim();
                String href = a.attr("href");
                try {
                    Document document1 = Jsoup.connect(href).get();
                    Element menu1 = document1.getElementById("menu-navegacao-abaixo");
                    menu1.select("a").forEach(a1 -> {
//                        String texto1 = a1.text().trim();
                        String href1 = a1.attr("href");
//                        System.out.printf("URL: %s\n", href1);
                        try {
                            Document pagina = Jsoup.connect(href1).get();
                            pagina.select("font[color=#FF0000]").forEach(elemento -> {
                                String trecho = href1.substring(href1.lastIndexOf("/") + 1);
                                trecho = trecho.replace(".php", "");
                                trecho = trecho.replace("-", " ");
                                trecho = trecho.replace(" e ", " / ");
                                trecho = trecho.toUpperCase();
//                                url = WordUtils.capitalize(url);
                                String distancia = elemento.text().toUpperCase();
                                System.out.printf("TRECHO: %s\n", trecho);
                                System.out.println(distancia);
                                distancia = distancia.replace("DISTÂNCIA: ", "");
                                System.out.println();
                                System.out.println();
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
