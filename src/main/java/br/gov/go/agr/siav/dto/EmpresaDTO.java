package br.gov.go.agr.siav.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Getter
@ToString
public class EmpresaDTO {

    private String abertura;

    @JsonProperty("atividade_principal")
    private List<Map<String, String>> atividadePrincipal;

    @JsonProperty("atividades_secundarias")
    private List<Map<String, String>> atividadesSecundarias;

    private String bairro;

    private String cep;

    private String cnpj;

    private String complemento;

    @JsonProperty("data_situacao")
    private String dataSituacao;

    @JsonProperty("data_situacao_especial")
    private String dataSituacaoEspecial;

    private String efr;

    private String email;

    private String fantasia;

    private String logradouro;

    @JsonProperty("motivo_situacao")
    private String motivoSituacao;

    private String municipio;

    @JsonProperty("natureza_juridica")
    private String naturezaJuridica;

    private String nome;

    private String numero;

    private String situacao;

    @JsonProperty("situacao_especial")
    private String situacaoEspecial;

    private String status;

    private String telefone;

    private String tipo;

    private String uf;
}
