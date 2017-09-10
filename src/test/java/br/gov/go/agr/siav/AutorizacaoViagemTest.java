package br.gov.go.agr.siav;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import jedi.db.engine.JediORMEngine;

import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.go.agr.siav.models.AutorizacaoViagem;
import br.gov.go.agr.siav.models.Empresa;

/**
 * Teste unitário da classe de objetos AutorizacaoViagem.
 * 
 * O teste avalia o funcionamento da persistência de objetos da classe AutorizacaoViagem 
 * em um banco de dados. Os comportamentos testados são: adição, edição, consulta e remoção.
 * 
 * Redmine: tarefa #89
 * 
 * @author thiago
 * @version 1.0
 */
public class AutorizacaoViagemTest {

    /**
     * Prepara o ambiente de testes recriando todas as tabelas do banco de dados
     * antes de iniciar os testes.
     */
    @BeforeClass
    public static void setup() {
        JediORMEngine.reset();
    }

    /**
     * Testa a consulta por um registro de autorização de viagem.
     */
    @Test
    public void testConsulta() {
        int registrosEsperados = 0;
        int registrosObtidos = AutorizacaoViagem.objects.count();
        assertEquals(registrosEsperados, registrosObtidos);
    }

    /**
     * Testa a adição de um registro de nota fiscal.
     */
    @Test
    public void testAdicao() {
        // Empresa
        Empresa empresa = new Empresa();
        empresa.setRegistroAgr(String.format("%4d", 1533));
        empresa.setCnpj("06.310.414/0001-01");
        empresa.setRazaoSocial("SANTA CLARA TRANSPORTES E TURISMO LTDA");
        empresa.setNomeFantasia("SANTA CLARA TRANSPORTES");
        empresa.setContato("THIAGO ALEXANDRE MARTINS MONTEIRO");
        empresa.setEmail("thiago.amm.agr@gmail.com");
        empresa.setTelefone("062 82143005");
        empresa.setEndereco("AV. JUSCELINO KUBISTCHEK Nº 06 QD. 11 LT. 01 SANTA EFIGENIA");
//        empresa.save();
        /*
        // Veículo
        ModeloVeiculo modelo = new ModeloVeiculo("Gol Rock in Rio", new FabricanteVeiculo("Volkswagen"));
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca("III-6873");
        veiculo.setNumero("1532-03");
        veiculo.setTipo("Onibus");
        veiculo.setAnoFabricacao("1990");
        veiculo.setLotacao(45);
        veiculo.setCarroceria("SCANIA");
        veiculo.setMotorista1("VINICIOS VIEIRA");
        veiculo.setProntuario1("3829124905");
        veiculo.setModelo(modelo);
        veiculo.save();
        // Roteiro de Viagem
        RoteiroViagem roteiroViagem = new RoteiroViagem();
        roteiroViagem.setOrigem("Uruaçu");
        roteiroViagem.setDestino("Niquelândia");
        roteiroViagem.setDistancia(88.00f);
        roteiroViagem.save();
        // Viagem
        Viagem viagem = new Viagem();
        viagem.setQuantidadePassageiros(45);
        viagem.setDataSaida(new DateTime(2014, 9, 25, 17, 40));
        viagem.setDataRetorno(new DateTime(2014, 9, 25, 22, 30));
        viagem.setExtensao(176.00f);
        viagem.setTipo("IDA");
        viagem.setLocalSaida("Niquelândia");
        viagem.save();
        // Nota Fiscal
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setValor(new BigDecimal(150.00));
        notaFiscal.setSerie("A");
        notaFiscal.setNumero(963);
        notaFiscal.setDataEmissao(new DateTime(2014, 9, 25));
        notaFiscal.save();
        // Boleto Bancário
        BoletoBancario boletoBancario = new BoletoBancario();
        boletoBancario.setNumero(426800139);
        boletoBancario.setDataPagamento(new DateTime(2014, 9, 25));
        boletoBancario.setValor(33.59f);
        boletoBancario.save();
        */
        AutorizacaoViagem autorizacaoViagem = new AutorizacaoViagem();
//        autorizacaoViagem.setNumero(10298901);
        autorizacaoViagem.setEmpresa(empresa);
        /*
        autorizacaoViagem.setVeiculo(veiculo);
        autorizacaoViagem.setRoteiroViagem(roteiroViagem);
        autorizacaoViagem.setViagem(viagem);
        autorizacaoViagem.setNotaFiscal(notaFiscal);
        autorizacaoViagem.setBoletoBancario(boletoBancario);
        */
        autorizacaoViagem.save();
        assertTrue(autorizacaoViagem.isPersisted());
        autorizacaoViagem.print("json");
    }

    /**
     * Testa a edição de um registro de autorização de viagem.
     */
    @Test
    public void testEdicao() {
        int idEsperado = 2;
        AutorizacaoViagem autorizacaoViagem = AutorizacaoViagem.objects.get("id", 1);
        autorizacaoViagem.setId(2);
        autorizacaoViagem.save();
        int idObtido = autorizacaoViagem.getId();
        assertEquals(idEsperado, idObtido);
    }

    /**
     * Testa a remoção de um registro de autorização de viagem.
     */
    @Test
    public void testRemocao() {
        int registrosEsperados = 0;
        AutorizacaoViagem.objects.all().delete();
        int registrosObtidos = AutorizacaoViagem.objects.count();
        assertEquals(registrosEsperados, registrosObtidos);
    }
}
