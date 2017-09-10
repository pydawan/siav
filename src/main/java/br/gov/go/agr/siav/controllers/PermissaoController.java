package br.gov.go.agr.siav.controllers;

import static br.com.caelum.vraptor.view.Results.json;
import static br.com.caelum.vraptor.view.Results.status;

import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.gov.go.agr.siav.enums.SituacaoCadastro;
import br.gov.go.agr.siav.forms.Form;
import br.gov.go.agr.siav.interfaces.IController;
import br.gov.go.agr.siav.models.Permissao;

/**
 * Controlador de permissões de usuário.
 * 
 * @author thiago
 * @version 1.0
 */
@Controller
public class PermissaoController implements IController<Permissao> {

    @Inject
    private Result result;

    private static Object[] situacoesCadastro = Stream.of(SituacaoCadastro.values()).map(t -> t.getValor()).toArray();

    @Override
    @Get(value = {"/admin/permissao", "/admin/permissao/"})
    public void index() {
        try {
            result.include("situacoesCadastro", situacoesCadastro);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    @Get(value = {"/admin/permissao/listar", "/admin/permissao/listar/"})
    public void listar() {
        try {
            List<Permissao> permissoes = Permissao.objects.<Permissao> all().orderBy("-id");
            result.include("permissoes", permissoes);
            serialize(permissoes);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    @Get(value = {"/admin/permissao/novo", "/admin/permissao/novo/"})
    public void novo() {
        try {
            Permissao permissao = new Permissao();
            result.include("permissao", permissao);
            serialize(permissao);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    @Get(value = {"/admin/permissao/{id}/buscar", "/admin/permissao/{id}/buscar/"})
    public void buscar(int id) {
        try {
            Permissao permissao = Permissao.objects.get("id", id);
            result.include("permissao", permissao);
            serialize(permissao);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    @Consumes(value = "application/json", options = WithoutRoot.class)
    @Post(value = {"/admin/permissao/salvar", "/admin/permissao/salvar/"})
    public void salvar(Form<Permissao> form) {
        try {
            String acao = form.getAcao();
            Permissao permissao = form.getObjeto();
            switch (acao) {
                case "adicionar":
                    this.adicionar(permissao);
                    break;
                case "editar":
                    this.editar(permissao);
                    break;
                case "remover":
                    this.remover(permissao);
                    break;
                default:
                    break;
            }
            serialize(permissao);
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

    public void adicionar(Permissao permissao) {
        if (permissao.getId() == 0) {
            permissao.setPersisted(false);
            permissao.save();
        }
    }

    public void editar(Permissao permissao) {
        if (permissao.getId() > 0) {
            permissao.setPersisted(true);
            permissao.save();
        }
    }

    public void remover(Permissao permissao) {
        if (permissao.getId() > 0) {
            permissao.setPersisted(true);
            permissao.delete();
        }
    }

}
