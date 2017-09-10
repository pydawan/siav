package br.gov.go.agr.siav.controllers;

import javax.inject.Inject;

import org.apache.shiro.subject.Subject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

import com.thedeanda.lorem.Lorem;

/**
 * <p>
 * Controlador responsável pelo mapeamento entre as requisições do usuário e os
 * recursos do sistema.
 * </p>
 * 
 * @author thiago
 * @version 1.0
 *
 */
@Controller
public class HomeController {

    @Inject
    private Result result;

    @SuppressWarnings("unused")
    @Inject
    private Subject subject;

    private String p = "<p style=\"margin-top: 25px;\">";

    @Get("/")
    public void index() {
        result.include("mensagem", "SIAV - SISTEMA DE AUTORIZAÇÃO DE VIAGENS");
        result.include("usuarioLogado", false);
    }

    @Get(value = {"/sobre", "/sobre/"})
    public void sobre() {
        result.include("texto", Lorem.getHtmlParagraphs(1, 2).replace("<p>", p));
    }

    @Get(value = {"/servicos", "/servicos/"})
    public void servicos() {
        result.include("texto", Lorem.getHtmlParagraphs(1, 2).replace("<p>", p));
    }

}
