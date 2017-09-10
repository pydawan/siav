package br.gov.go.agr.siav.services;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.pmw.tinylog.Logger;

import br.gov.go.agr.siav.models.Usuario;

/**
 * <p>
 *  Classe responsável pelo fornecimento de serviços relacionados a 
 *  segurança da aplicação.
 *  </p>
 * 
 * @author thiago
 * @version 1.0
 */
public abstract class AutenticacaoService {

    // Cria gerador de números aleatórios.
    private static RandomNumberGenerator rng = new SecureRandomNumberGenerator();

    /**
     * Método responsável pela geração de salt usado na geração de senha segura.
     * @return um salt que irá ajudar na composição de um hash
     */
    public static Object gerarSalt() {
        return rng.nextBytes();
    }

    /**
     * Método responsável pela geração de hash em base 64 a partir de um texto.
     * @return um hash de um texto.
     */
    public static String gerarHash(String texto, String salt) {
        texto = texto == null ? "" : texto;
        salt = salt == null ? "" : salt;
        return new Sha256Hash(texto, salt, 1024).toBase64();
    }

    public static void gerarSenha(Usuario usuario, String texto) {
        texto = texto == null ? "" : texto;
        if (usuario == null && texto.isEmpty()) {
            return;
        } else {
            // Definindo o novo estado dos dados do usuário.
            usuario.setSalt(gerarSalt().toString());
            usuario.setSenha(gerarHash(texto, usuario.getSalt()));
            Logger.info("Senha gerada para o usuário: " + usuario.getLogin());
        }
    }

    public static void gerarSenha(Usuario usuario) {
        gerarSenha(usuario, usuario.getSenha());
    }

    public static String obterSalt(String login) {
        String salt = "";
        login = login == null ? "" : login;
        if (!login.isEmpty()) {
            salt = Usuario.objects.<Usuario>get("login", login).getSalt();
        }
        return salt;
    }

}
