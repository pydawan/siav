package br.gov.go.agr.siav.services;

import java.io.IOException;

import org.apache.directory.api.ldap.model.cursor.CursorException;
import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.BindRequest;
import org.apache.directory.api.ldap.model.message.BindRequestImpl;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.pmw.tinylog.Logger;

import br.gov.go.agr.siav.enums.SituacaoCadastro;
import br.gov.go.agr.siav.enums.TipoAutenticacao;
import br.gov.go.agr.siav.enums.TipoUsuario;
import br.gov.go.agr.siav.models.Perfil;
import br.gov.go.agr.siav.models.Usuario;

public abstract class ActiveDirectoryService {

    private static final String servidor = "10.243.1.5";
    private static final int porta = 389;

    /**
     * Busca um usuário do Active Directory através de seu nome de usuário e sua senha.
     * 
     * @param nomeUsuario
     * @param senha
     * @return
     */
    public static Usuario buscarUsuario(String nomeUsuario, String senha) {
        Usuario usuario = null;
        nomeUsuario = nomeUsuario == null ? "" : nomeUsuario;
        senha = senha == null ? "" : senha;
        if (!nomeUsuario.isEmpty() && !senha.isEmpty()) {
            try {
                LdapConnection connection = new LdapNetworkConnection(servidor, porta);
                connection.setTimeOut(10000);
                BindRequest request = new BindRequestImpl();
                request.setSimple(true);
                request.setName(String.format("%s@agr.go", nomeUsuario));
                request.setCredentials(senha);
                connection.bind(request);
                String filtro = String.format("(&(objectClass=user)(sAMAccountName=%s))", nomeUsuario);
                EntryCursor cursor = connection.search("OU=agr,DC=agr,DC=go", filtro, SearchScope.SUBTREE, "*");
                while (cursor.next()) {
                    Entry entry = cursor.get();
                    usuario = new Usuario();
                    usuario.setLogin(entry.get("sAMAccountName").get().toString());
                    usuario.setSenha(senha);
                    usuario.setNome(entry.get("cn").get().toString());
                    usuario.setEmail(String.format("%s.gov.br", entry.get("userPrincipalName").get().toString()));
                    Logger.info(entry);
                }
                connection.unBind();
                connection.close();
            } catch (LdapException e) {
                e.printStackTrace();
            } catch (CursorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }

    /**
     * Sincroniza os dados de um usuário local com os dados do usuário 
     * correspondente no Active Directory.
     * 
     * @param usuario
     */
    public static void sincronizar(Usuario usuario) {
        // Verifica se o usuário existe.
        if (usuario != null) {
            // Consulta pelos dados do usuário no Active Directory.
            Usuario dados = ActiveDirectoryService.buscarUsuario(usuario.getLogin(), usuario.getSenha());
            // Se os dados existem
            if (dados != null) {
                // Sincroniza.
                usuario.setNome(dados.getNome());
                usuario.setEmail(dados.getEmail());
                usuario.setLogin(dados.getLogin());
                usuario.setSenha(dados.getSenha());
                AutenticacaoService.gerarSenha(usuario);
                usuario.setPerfil(Perfil.objects.get("nome", "Padrão"));
                usuario.setTipo(TipoUsuario.INTERNO.getValor());
                usuario.setAutenticacao(TipoAutenticacao.ACTIVE_DIRECTORY.getValor());
                usuario.setSituacao(SituacaoCadastro.ATIVO.getValor());
                usuario.save();
            } else {
                Logger.warn(String.format("Os dados do usuário %s não foram encontrados no Active Directory!", usuario.getLogin()));
            }
        } else {
            Logger.warn("O usuário passado como parâmetro é nulo!");
        }
    }
}
