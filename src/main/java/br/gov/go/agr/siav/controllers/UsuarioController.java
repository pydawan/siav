package br.gov.go.agr.siav.controllers;

import static br.com.caelum.vraptor.view.Results.json;
import static br.com.caelum.vraptor.view.Results.status;
import static com.google.common.base.Strings.isNullOrEmpty;

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
import br.gov.go.agr.siav.enums.TipoAutenticacao;
import br.gov.go.agr.siav.enums.TipoUsuario;
import br.gov.go.agr.siav.forms.Form;
import br.gov.go.agr.siav.interfaces.IController;
import br.gov.go.agr.siav.models.Perfil;
import br.gov.go.agr.siav.models.Usuario;
import br.gov.go.agr.siav.services.AutenticacaoService;

/**
 * Controlador de usuários.
 * 
 * @author thiago
 * @version 1.0
 */
@Controller
public class UsuarioController implements IController<Usuario> {

    private static Object[] tiposUsuario = Stream.of(TipoUsuario.values()).map(t -> t.getValor()).toArray();
    private static Object[] situacoesCadastro = Stream.of(SituacaoCadastro.values()).map(t -> t.getValor()).toArray();

    @Inject
    private Result result;

    @Override
    @Get(value = {"/admin/usuario", "/admin/usuario/"})
    public void index() {
        try {
            result.include("tiposUsuario", tiposUsuario);
            result.include("perfisUsuario", Perfil.objects.all());
            result.include("situacoesCadastro", situacoesCadastro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Get(value = {"/admin/usuario/listar", "/admin/usuario/listar/"})
    public void listar() {
        List<Usuario> usuarios = Usuario.objects.<Usuario>all().orderBy("-id");
        result.include("usuarios", usuarios);
        serialize(usuarios);
    }

    @Override
    @Get(value = {"/admin/usuario/novo", "/admin/usuario/novo/"})
    public void novo() {
        Usuario usuario = new Usuario();
        result.include("usuario", usuario);
        serialize(usuario);
    }

    @Override
    @Get(value = {"/admin/usuario/{id}/buscar", "/admin/usuario/{id}/buscar/"})
    public void buscar(int id) {
        try {
            Usuario usuario = Usuario.objects.get("id", id);
            result.include("usuario", usuario);
            serialize(usuario);
        } catch (Exception e) {
            internalServerError();
        }
    }

    @Override
    @Consumes(value = "application/json", options = WithoutRoot.class)
    @Post(value = {"/admin/usuario/salvar", "/admin/usuario/salvar/"})
    public void salvar(Form<Usuario> form) {
        try {
            String acao = form.getAcao();
            Usuario usuario = form.getObjeto();
            switch (acao) {
                case "adicionar":
                case "editar":
                    Perfil perfil = Perfil.objects.get("id", usuario.getPerfil().getId());
                    usuario.setPerfil(perfil);
                    if (!isNullOrEmpty(usuario.getSenha())) {
                        String salt = AutenticacaoService.gerarSalt().toString();
                        usuario.setSalt(salt);
                        String hash = AutenticacaoService.gerarHash(usuario.getSenha(), usuario.getSalt());
                        usuario.setSenha(hash);
                    }
                    switch (usuario.getTipo().toLowerCase()) {
                        case "interno":
                            usuario.setAutenticacao(TipoAutenticacao.ACTIVE_DIRECTORY.getValor());
                            break;
                        case "externo":
                            usuario.setAutenticacao(TipoAutenticacao.BANCO_DE_DADOS.getValor());
                            break;
                    }
                    // Adição.
                    if (usuario.getId() == 0) {
                        usuario.setPersisted(false);
                    } else { // Edição.
                        usuario.setPersisted(true);
                    }
                    usuario.save();
                    break;
                case "remover":
                    usuario = Usuario.objects.get("id", usuario.getId());
                    usuario.delete();
                    break;
                default:
                    break;
            }
            serialize(usuario);
        } catch (Exception e) {
            internalServerError();
        } 
    }

    @Override
    public void serialize(Object object) {
        result
        .use(json())
        .serializeNulls()
        .indented()
        .withoutRoot()
        .from(object)
        .exclude("salt", "senha")
        .include("perfil")
        .serialize();
    }

    @Override
    public void internalServerError() {
        result.use(status()).internalServerError();
    }

}
