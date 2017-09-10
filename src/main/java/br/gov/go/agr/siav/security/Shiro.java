package br.gov.go.agr.siav.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pmw.tinylog.Logger;

import br.com.caelum.vraptor.security.Permission;
import br.com.caelum.vraptor.security.User;
import br.gov.go.agr.siav.models.Perfil;
import br.gov.go.agr.siav.models.Permissao;
import br.gov.go.agr.siav.models.Usuario;

/**
 * Serviço de autenticação e autorização de usuários.
 * 
 * @author thiago
 * @version 1.0
 *
 */
public class Shiro implements Permission {

    @Override
    public User getUserByUsername(String username) {
        Usuario usuario = Usuario.objects.get("login", username);
        Logger.info(usuario);
        return new User(usuario.getLogin(), usuario.getSenha());
    }

    @Override
    public Set<String> getRolesByUser(String user) {
        Set<String> roles = new HashSet<>();
        List<Perfil> perfis = Perfil.objects.filter("login", user);
        perfis.forEach(perfil -> roles.add(perfil.getNome()));
        Logger.info(perfis);
        return roles;
    }

    @Override
    public Set<String> getPermissionsByRole(String role) {
        Set<String> permissions = new HashSet<>();
        Perfil perfil = Perfil.objects.get("nome", role);
        List<Permissao> permissoes = perfil.getPermissoes();
        permissoes.forEach(permissao -> permissions.add(permissao.getNome()));
        Logger.info(permissoes);
        return permissions;
    }
}