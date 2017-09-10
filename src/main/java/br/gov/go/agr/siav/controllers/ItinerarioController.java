package br.gov.go.agr.siav.controllers;

import static br.com.caelum.vraptor.view.Results.json;
import static br.com.caelum.vraptor.view.Results.status;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.gov.go.agr.siav.forms.Form;
import br.gov.go.agr.siav.interfaces.IController;
import br.gov.go.agr.siav.models.Goias;
import br.gov.go.agr.siav.models.Itinerario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ItinerarioController implements IController<Itinerario> {

    @Inject
    private Result result;

    @Override
    @Get(value = {"/admin/itinerario", "/admin/itinerario/"})
    public void index() {
        try {
            Collections.sort(Goias.municipios);
            Collections.sort(Goias.rodovias);
            result.include("municipios", Goias.municipios);
            result.include("rodovias", Goias.rodovias);
            ObjectMapper mapper = new ObjectMapper();
            result.include("municipiosJSON", mapper.writeValueAsString(Goias.municipios));
            result.include("rodoviasJSON", mapper.writeValueAsString(Goias.rodovias));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Get(value = {"/admin/itinerario/listar", "/admin/itinerario/listar/"})
    public void listar() {
        try {
            List<Itinerario> itinerarios = Itinerario.objects.all();
            serialize(itinerarios);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    @Get(value = {"/admin/itinerario/novo", "/admin/itinerario/novo/"})
    public void novo() {
        try {
            Itinerario itinerario = new Itinerario();
            serialize(itinerario);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    @Get(value = {"/admin/itinerario/{id}/buscar", "/admin/itinerario/{id}/buscar/"})
    public void buscar(int id) {
        try {
            Itinerario itinerario = Itinerario.objects.get("id", id);
            serialize(itinerario);
        } catch(Exception e) {
            internalServerError();
        }
    }

    @Override
    @Post(value = {"/admin/itinerario/salvar", "/admin/itinerario/salvar/"})
    @Consumes(value = "application/json", options = WithoutRoot.class)
    public void salvar(Form<Itinerario> form) {
        String acao = form.getAcao();
        Itinerario itinerario = form.getObjeto();
        try {
            switch (acao) {
                case "adicionar":
                case "editar":
                    if (itinerario.getId() != 0) {
                        itinerario.setPersisted(true);
                    }
                    itinerario.save();
                    break;
                case "remover":
                    itinerario = Itinerario.objects.get("id", itinerario.getId());
                    itinerario.delete();
                    break;
                default:
                    break;
            }
            serialize(itinerario);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Post(value = {"/admin/itinerario/pesquisar", "/admin/itinerario/pesquisar/"})
    @Consumes(value = "application/json", options = WithoutRoot.class)
    public void pesquisar(Form<Itinerario> form) {
        String municipio = form.getExtra().get("municipio");
        municipio = municipio == null ? "" : municipio.trim().replaceAll("\"", "");;
        String sql = String.format("trecho like '%s / %%' or trecho like '%% / %s'", municipio, municipio);
        try {
            List<Itinerario> itinerarios = Itinerario.objects.where(sql);
            serialize(itinerarios);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    public void serialize(Object object) { 
        result.use(json()).serializeNulls().indented().withoutRoot().from(object).serialize();
    }

    @Override
    public void internalServerError() {
        result.use(status()).internalServerError();
    }
}
