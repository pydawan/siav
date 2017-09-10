package br.gov.go.agr.siav.util;

/**
 * Classe responsável pela configuração do proxy de rede.
 * 
 * @author thiago
 *
 */
public final class Proxy {

    public static void configure() {
        System.out.println("Configurando o proxy");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // http
        System.getProperties().put("http.proxyHost", "10.243.1.3");
        System.getProperties().put("http.proxyPort", "2303");
        // https
        System.getProperties().put("https.proxyHost", "10.243.1.3");
        System.getProperties().put("https.proxyPort", "2303");
    }
}
