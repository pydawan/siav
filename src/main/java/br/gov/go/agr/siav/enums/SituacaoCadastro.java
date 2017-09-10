package br.gov.go.agr.siav.enums;

/**
 * Enumeração que define os possíveis valores para situação de um cadastro no sistema.
 * 
 * @author thiago
 * @version 1.0
 */
public enum SituacaoCadastro {

    ATIVO("Ativo", "Cadastro ativo"), 
    INATIVO("Inativo", "Cadastro inativo");

    private final String valor;
    private final String descricao;

    private SituacaoCadastro(final String valor, final String rotulo) {
        this.valor = valor;
        this.descricao = rotulo;
    }

    public String getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}