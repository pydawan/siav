package br.gov.go.agr.siav.controllers;

import static br.com.caelum.vraptor.view.Results.json;
import static br.com.caelum.vraptor.view.Results.status;

import java.util.List;

import javax.inject.Inject;

import org.pmw.tinylog.Logger;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.gov.go.agr.siav.enums.TipoVeiculo;
import br.gov.go.agr.siav.enums.TipoViagem;
import br.gov.go.agr.siav.models.AutorizacaoViagem;
import br.gov.go.agr.siav.models.FabricanteVeiculo;
import br.gov.go.agr.siav.models.Goias;
import br.gov.go.agr.siav.models.Itinerario;
import br.gov.go.agr.siav.models.ModeloVeiculo;
import br.gov.go.agr.siav.session.UsuarioSessao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controlador de autorizações de viagem.
 * 
 * @author thiago
 * @version 1.0
 */
@Controller
public class AutorizacaoController {

    @Inject
    private Result result;

    @Inject
    private UsuarioSessao usuarioSessao;

    public void formulario() {
        if (!usuarioSessao.isAutenticado()) {
            result.redirectTo(LoginController.class).formulario();
        }
    }

    @Get(value = {"/autorizacao/acompanhar", "/autorizacao/acompanhar/"})
    public void acompanhar() {
        if (usuarioSessao.isAutenticado()) {
            result.include("autorizacoes", AutorizacaoViagem.objects.all());
        } else {
            result.redirectTo(LoginController.class).formulario();
        }
    }

    @Get(value = {"/autorizacao/solicitar", "/autorizacao/solicitar/"})
    public void solicitar() {
        if (usuarioSessao.isAutenticado()) {
            result.include("acao", "Solicitar");
            result.include("tiposVeiculo", TipoVeiculo.values());
            result.include("tiposViagem", TipoViagem.values());
            result.include("autorizacao", new AutorizacaoViagem());
            result.include("fabricantes", FabricanteVeiculo.objects.all().orderBy("nome"));
            result.include("modelos", ModeloVeiculo.objects.all().orderBy("nome"));
            try {
                ObjectMapper mapper = new ObjectMapper();
                List<Itinerario> itinerarios = Itinerario.objects.<Itinerario>all().orderBy("trecho");
                result.include("itinerarios", mapper.writeValueAsString(itinerarios));
                result.include("municipios", mapper.writeValueAsString(Goias.municipios));
                
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            result.of(this).formulario();
        } else {
            result.redirectTo(LoginController.class).formulario();
        }
    }

    @Get(value = {"/autorizacao/editar/{id}", "/autorizacao/editar/{id}/"})
    public void editar(int id) {
        result.include("acao", "Editar");
        result.include("tiposVeiculo", TipoVeiculo.values());
        result.include("tiposViagem", TipoViagem.values());
        result.include("autorizacao", AutorizacaoViagem.objects.get("id", id));
        result.of(this).formulario();
    }

    @Get(value = {"/autorizacao/remover/{id}", "/autorizacao/remover/{id}/"})
    public void remover(int id) {
        result.include("tiposVeiculo", TipoVeiculo.values());
        result.include("tiposViagem", TipoViagem.values());
        AutorizacaoViagem.objects.get("id", id).delete();
        result.of(this).formulario();
    }

    @Get(value = {"/autorizacao/visualizar/{id}", "/autorizacao/visualizar/{id}/"})
    public void visualizar(int id) {
        result.include("tiposVeiculo", TipoVeiculo.values());
        result.include("tiposViagem", TipoViagem.values());
        result.include("autorizacao", AutorizacaoViagem.objects.get("id", id));
        result.of(this).formulario();
    }

    @Post(value = {"/autorizacao/salvar", "/autorizacao/salvar/"})
    public void salvar(AutorizacaoViagem autorizacao) {
        if (autorizacao != null) {
            Logger.info("Salvando a autorização de viagem.");
            autorizacao.save();
        }
        result.redirectTo(AutorizacaoController.class).acompanhar();
    }

    public void serialize(Object object) { 
        result.use(json()).serializeNulls().indented().withoutRoot().from(object).serialize();
    }

    public void internalServerError() {
        result.use(status()).internalServerError();
    }

}
