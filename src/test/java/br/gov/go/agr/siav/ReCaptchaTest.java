package br.gov.go.agr.siav;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 *  Caso de Teste de integração entre a biblioteca reCAPTCHA e 
 *  a linguagem Java.
 *  </p>
 * 
 * Tarefa Redmine: Teste #114
 * 
 * @author thiago
 * @version 1.0
 *
 */
public class ReCaptchaTest {

    // Chaves: pública e privada.
    private static final String publicKey = "6LdhHAkTAAAAAPNY2iYApUqRqQz4CFFT786G0W6E";
    private static final String privateKey = "6LdhHAkTAAAAAPaJlX6AFQz4HipDVOYiuXFq9Njo";

    private static String desafio = "";

    // reCAPTCHA
    private static ReCaptcha captcha = ReCaptchaFactory.newReCaptcha(publicKey, privateKey, true);

    /**
     * Testa se a resposta da requisição ao webservice reCAPTCHA não é nula.
     */
    @Test
    public void testResposta() {
        desafio = captcha.createRecaptchaHtml(null, null);
        Assert.assertNotNull(desafio);
        System.out.println(desafio);
    }
}
