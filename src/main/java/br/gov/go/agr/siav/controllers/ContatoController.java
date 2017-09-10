package br.gov.go.agr.siav.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.pmw.tinylog.Logger;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.gov.go.agr.siav.models.Contato;
import br.gov.go.agr.siav.template.TemplateEngine;
import br.gov.go.agr.siav.util.Proxy;

/**
 * <p>
 * Controlador responsável pelo mapeamento entre requisições de usuários e
 * recursos do sistema.
 * </p>
 * 
 * Redmine: tarefa #110
 * 
 * @author thiago
 * @version 1.0
 *
 */
@Controller
public class ContatoController {

    @Inject
    private Result result;

    @Inject
    private HttpServletRequest request;

    // Chaves privada e pública.
    private static final String privateKey = "6LdhHAkTAAAAAPaJlX6AFQz4HipDVOYiuXFq9Njo";

    private static final String publicKey = "6LdhHAkTAAAAAPNY2iYApUqRqQz4CFFT786G0W6E";

    /**
     * <p>
     * Carrega o formulário de contato com um desafio CAPTCHA.
     * </p>
     * 
     * Redmine: tarefa #114
     */
    @Get(value = {"/contato", "/contato/"})
    public void formulario() {
        // reCAPTCHA.
        ReCaptcha reCaptcha = ReCaptchaFactory.newReCaptcha(publicKey, privateKey, true);
        // CAPTCHA - Teste de Turing para diferenciar um humano de uma máquina.
        String captcha = reCaptcha.createRecaptchaHtml(null, null);
        result.include("captcha", captcha);
    }

    /**
     * <p>
     * Recebe os dados do formulário de contato e envia um email. Se a resposta
     * ao desafio CAPTCHA for correta prossegue com o envio senão mostra uma
     * mensagem de erro.
     * </p>
     * 
     * Redmine: tarefa #112
     * 
     * @param contato
     * @param recaptcha_challenge_field
     * @param recaptcha_response_field
     */
    @Path(value = {"/contato/enviar", "/contato/enviar/"})
    public void enviar(Contato contato, String recaptcha_challenge_field, String recaptcha_response_field) {
        if (contato != null) {
            Logger.info(contato);
            try {
                Proxy.configure();
                String remoteAddr = request.getRemoteAddr();
                ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
                reCaptcha.setPrivateKey(privateKey);
                String challenge = request.getParameter("recaptcha_challenge_field");
                String uresponse = request.getParameter("recaptcha_response_field");
                ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
                if (reCaptchaResponse.isValid()) {
                    Logger.info("A resposta informada está CORRETA!");
                    HtmlEmail email = new HtmlEmail();
                    email.setHostName("smtp.gmail.com");
                    email.setSmtpPort(587);
                    email.setStartTLSEnabled(true);
                    email.setAuthenticator(new DefaultAuthenticator("desenvolvimento.agr.go@gmail.com", "desagr2015"));
                    email.setFrom("desenvolvimento.agr.go@gmail.com", "SIAV - Sistema de Autorização de Viagens");
                    List<InternetAddress> cc = new ArrayList<>();
                    InternetAddress cc1 = new InternetAddress(contato.getEmail());
                    cc.add(cc1);
                    email.setCc(cc);
                    email.setSubject(contato.getAssunto());
                    Map<String, Object> contexto = new HashMap<>();
                    contexto.put("mensagem", contato.getMensagem());
                    email.setMsg(TemplateEngine.render(contexto, "email.html"));
                    email.addTo("thiago.amm.agr@gmail.com");
                    email.send();
                    result.include("tipo_alerta", "alert-success");
                    result.include("mensagem_alerta", "Mensagem enviada com sucesso!");
                } else {
                    Logger.info("A resposta informada está INCORRETA!");
                    result.include("tipo_alerta", "alert-danger");
                    result.include("mensagem_alerta", "Não foi possível enviar a mensagem!");
                }
            } catch (EmailException | AddressException e) {
                e.printStackTrace();
            }
        }
        result.redirectTo(ContatoController.class).formulario();
    }
}
