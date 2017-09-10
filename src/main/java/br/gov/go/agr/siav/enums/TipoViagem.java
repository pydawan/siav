package br.gov.go.agr.siav.enums;

/**
 * Define os tipos de viagem.
 * 
 * @author thiago
 * @version 1.0
 */
public enum TipoViagem {

    IDA("Ida"), IDA_E_VOLTA("Ida e Volta");

    private final String valor;

    private TipoViagem(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
