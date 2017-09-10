package br.gov.go.agr.siav.enums;

/**
 * Define o tipo de veículo cadastrado. 1 - Micro-ônibus, veículo que dispõe de
 * no máximo 20 lugares. 2 - Ônibus, veículo que dispõe de mais de 20 lugares.
 * 
 * @author thiago
 * @version 1.0
 */
public enum TipoVeiculo {

    MICROONIBUS("Micro-ônibus"), ONIBUS("Ônibus");

    private final String valor;

    private TipoVeiculo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
