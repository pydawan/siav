package br.gov.go.agr.siav.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import org.apache.shiro.subject.Subject;

import br.gov.go.agr.siav.models.Empresa;
import br.gov.go.agr.siav.models.Usuario;

/**
 * Classe que abstrai um usuário do sistema.
 * 
 * @author thiago
 * @version 1.0
 *
 */
@SessionScoped
@Named("usuarioSessao")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = false, chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSessao implements Serializable {

    private static final long serialVersionUID = 945310166743055869L;
    private Subject subject;
    private Usuario usuario;
    private Empresa empresa;

    public boolean isAutenticado() {
        boolean autenticado = false;
        if (this.subject != null) {
            autenticado = this.subject.isAuthenticated();
        }
        return autenticado;
    }
}
