package br.gov.go.agr.siav.controllers;

import javax.inject.Inject;

import jedi.db.exceptions.ObjectDoesNotExistException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.pmw.tinylog.Logger;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.security.AuthorizationRestrictionListener;
import br.com.caelum.vraptor.view.Results;
import br.gov.go.agr.siav.enums.TipoUsuario;
import br.gov.go.agr.siav.models.Empresa;
import br.gov.go.agr.siav.models.Usuario;
import br.gov.go.agr.siav.services.ActiveDirectoryService;
import br.gov.go.agr.siav.services.AutenticacaoService;
import br.gov.go.agr.siav.session.UsuarioSessao;


/**
 * Classe reponsável por tratar o acesso seguro do usuário a aplicação.
 * 
 * @author thiago
 * @version 1.0
 *
 */
@Controller
public class LoginController implements AuthorizationRestrictionListener {

    @Inject
    private Result result;

    @Inject
    private Subject subject;

    @Inject
    private UsuarioSessao usuarioSessao;

    @Override
    public void onAuthorizationRestriction(AuthorizationException e) {
        result.use(Results.status()).forbidden(e.toString());
    }

    /**
     * Autentica os usuários que desejam acessar as funcionalidades do sistema.
     * 
     * @param login
     * @param senha
     * @param lembrar
     */
    @Post("/login")
    public void login(String login, String senha, boolean memorizar) {
        login = login == null ? "" : login;
        senha = senha == null ? "" : senha;
        try {
            // Verifica os parâmetros passados.
            if (!login.isEmpty() && !senha.isEmpty()) {
                Usuario usuario = null;
                try {
                    // Busca o usuário no banco de dados.
                    usuario = Usuario.objects.get("login", login);
                    // Obtém o salt do usuário.
                    String salt = AutenticacaoService.obterSalt(login);
                    // Recupera o hash da senha do usuário através do salt.
                    String hash = AutenticacaoService.gerarHash(senha, salt);
                    // Define a senha segura do usuário.
                    usuario.setSenha(hash);
                } catch (ObjectDoesNotExistException e) {
                    // Busca o usuário no Active Directory.
                    usuario = ActiveDirectoryService.buscarUsuario(login, senha);
                    if (usuario != null) {
                        // Grava os dados do AD no banco de dados.
                        ActiveDirectoryService.sincronizar(usuario);
                    }
                }
                // Autenticação do usuário.
                if (usuario != null) {
                    usuarioSessao.setSubject(subject);
                    usuarioSessao.getSubject().login(new UsernamePasswordToken(usuario.getLogin(), usuario.getSenha(), memorizar));
                    Logger.info(String.format("O usuário %s foi autenticado com sucesso!", login));
                    usuarioSessao.setUsuario(usuario);
                    if (usuario.getTipo().equals(TipoUsuario.EXTERNO.getValor())) {
                        Empresa empresa = Empresa.objects.get("cnpj", usuario.getLogin());
                        usuarioSessao.setEmpresa(empresa);
                    }
                    result.redirectTo(AutorizacaoController.class).acompanhar();
                } else {
                    throw new AuthenticationException();
                }
            }
        } catch (AuthenticationException e) {
            Logger.info(String.format("Falha ao autenticar o usuário %s!", login));
            result.include("error", "Erro na autenticação");
            result.redirectTo(LoginController.class).formulario();
        }
    }

    @Get("/login")
    public void formulario() {
        
    }

    @Get("/logout")
    public void logout() {
        if (usuarioSessao != null && usuarioSessao.getSubject() != null) { 
            usuarioSessao.getSubject().logout();
        }
        result.redirectTo(LoginController.class).formulario();
    }
}
