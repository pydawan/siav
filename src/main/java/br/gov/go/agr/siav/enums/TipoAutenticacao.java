package br.gov.go.agr.siav.enums;

/**
 * Define o tipo de autenticação do usuário no sistema. 1 - Autenticação no
 * Active Directory para usuários internos (funcionários da AGR). 2 -
 * Autenticação no Banco de Dados para usuários externos (cidadão que usufrui
 * dos serviços da AGR).
 * 
 * @author thiago
 * @version 1.0
 */
public enum TipoAutenticacao {

    ACTIVE_DIRECTORY("Active Directory"), BANCO_DE_DADOS("Banco de Dados");

    private final String valor;

    private TipoAutenticacao(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
