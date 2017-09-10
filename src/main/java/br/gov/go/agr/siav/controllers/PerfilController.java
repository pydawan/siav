package br.gov.go.agr.siav.controllers;

import static br.com.caelum.vraptor.view.Results.json;
import static br.com.caelum.vraptor.view.Results.status;

import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;

import jedi.db.models.QuerySet;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.gov.go.agr.siav.converters.PermissaoConverter;
import br.gov.go.agr.siav.enums.SituacaoCadastro;
import br.gov.go.agr.siav.forms.Form;
import br.gov.go.agr.siav.interfaces.IController;
import br.gov.go.agr.siav.models.Perfil;
import br.gov.go.agr.siav.models.Permissao;
import br.gov.go.agr.siav.models.Privilegio;

/**
 * Controlador de perfis de usuário.
 * 
 * @author thiago
 * @version 1.0
 */
@Controller
public class PerfilController implements IController<Perfil> {

    @Inject
    private Result result;

    private static Object[] situacoesCadastro = Stream.of(SituacaoCadastro.values()).map(t -> t.getValor()).toArray();

    @Override
    @Get(value = {"/admin/perfil", "/admin/perfil/"})
    public void index() {
        try {
            List<Permissao> permissoes = Permissao.objects.<Permissao> all().orderBy("id");
            result.include("permissoes", permissoes);
            result.include("situacoesCadastro", situacoesCadastro);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    @Get(value = {"/admin/perfil/listar", "/admin/perfil/listar/"})
    public void listar() {
        try {
            List<Perfil> perfis = Perfil.objects.<Perfil> all().orderBy("-id");
            result.include("perfis", perfis);
            serialize(perfis);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    @Get(value = {"/admin/perfil/novo", "/admin/perfil/novo/"})
    public void novo() {
        try {
            Perfil perfil = new Perfil();
            result.include("perfil", perfil);
            serialize(perfil);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    @Get(value = {"/admin/perfil/{id}/buscar", "/admin/perfil/{id}/buscar/"})
    public void buscar(int id) {
        try {
            Perfil perfil = Perfil.objects.get("id", id);
            perfil.getPermissoes();
            result.include("perfil", perfil);
            serialize(perfil);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    @Consumes(value = "application/json", options = WithoutRoot.class)
    @Post(value = {"/admin/perfil/salvar", "/admin/perfil/salvar/"})
    public void salvar(Form<Perfil> form) {
        try {
            String acao = form.getAcao();
            Perfil perfil = form.getObjeto();
            PermissaoConverter conversor = new PermissaoConverter();
            List<Permissao> permissoes = conversor.convert(form.getExtra().<List<String>> get("permissoes"));
            switch (acao) {
                case "adicionar":
                    this.adicionar(perfil, permissoes);
                    break;
                case "editar":
                    this.editar(perfil, permissoes);
                    break;
                case "remover":
                    this.remover(perfil);
                    break;
                default:
                    break;
            }
            serialize(perfil);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    public void serialize(Object object) {
        result.use(json()).serializeNulls().indented().withoutRoot().from(object).include("permissoes").serialize();
    }

    @Override
    public void internalServerError() {
        result.use(status()).internalServerError();
    }

    public void adicionar(Perfil perfil, List<Permissao> permissoes) {
        if (perfil.getId() == 0) {
            perfil.setPersisted(false);
            perfil.save();
            QuerySet<Privilegio> privilegios = new QuerySet<>();
            permissoes.forEach(permissao -> {
                privilegios.add(new Privilegio(perfil, permissao));
            });
            privilegios.save();
        }
    }

    public void editar(Perfil perfil, List<Permissao> permissoes) {
        if (perfil.getId() > 0) {
            perfil.setPersisted(true);
            perfil.save();
            perfil.getPrivilegioSet().delete();
            QuerySet<Privilegio> privilegios = new QuerySet<>();
            permissoes.forEach(permissao -> {
                privilegios.add(new Privilegio(perfil, permissao));
            });
            privilegios.save();
        }
    }

    public void remover(Perfil perfil) {
        if (perfil.getId() > 0) {
            perfil.setPersisted(true);
            perfil.delete();
        }
    }
}
