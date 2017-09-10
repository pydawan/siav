package br.gov.go.agr.siav.enums;

/**
 * Define os perfils de usuário do sistema.
 * 
 * @author thiago
 * @version 1.0
 *
 */
public enum PerfilUsuario {

    ADMIN("Admin", "Administrador do sistema."), 
    OPERADOR("Operador", "Operador do sistema."), 
    PADRAO("Padrão", "Usuário padrão do sistema.");

    private final String valor;

    private final String descricao;

    private PerfilUsuario(final String valor, final String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
