package br.gov.go.agr.siav.enums;

/**
 * Define os tipos de usuário do sistema tendo a AGR como referencial. Os
 * usuários podem ser internos (funcionários da AGR) ou externos (usuários dos
 * serviços da AGR).
 * 
 * @author thiago
 * @version 1.0
 *
 */
public enum TipoUsuario {

    INTERNO("Interno"), EXTERNO("Externo");

    private final String valor;

    private TipoUsuario(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
