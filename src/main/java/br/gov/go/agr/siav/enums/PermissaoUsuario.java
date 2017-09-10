package br.gov.go.agr.siav.enums;

/**
 * Define todas as permissões dos usuários do sistema.
 * 
 * @author thiago
 * @version 1.0
 *
 */
public enum PermissaoUsuario {

    SOLICITAR_AUTORIZACAO("autorizacao:solicitar", "Permite que o usuário solicite autorizações de viagem."),
    ACOMPANHAR_AUTORIZACAO("autorizacao:acompanhar", "Permite que o usuário acompanhe as autorizações de viagem solicitadas."),
    EMITIR_AUTORIZACAO("autorizacao:emitir", "Permite que o usuário emita autorizações de viagem."),
    AUTENTICAR_AUTORIZACAO("autorizacao:autenticar", "Permite que o usuário autentique autorizações de viagem.");

    private final String valor;
    private final String descricao;

    private PermissaoUsuario(final String valor, final String descricao) {
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
