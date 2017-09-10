package br.gov.go.agr.siav;

import jedi.db.engine.JediORMEngine;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.go.agr.siav.models.FabricanteVeiculo;
import br.gov.go.agr.siav.models.ModeloVeiculo;
import br.gov.go.agr.siav.models.Veiculo;


/**
 * Teste unitário da classe de objetos Veiculo.
 * 
 * O teste avalia o funcionamento da persistência de objetos da classe Veículo em um banco de dados.
 * Os comportamentos testados são: adição, edição, consulta e remoção.
 * 
 * Redmine: Tarefa #64
 * 
 * @author thiago
 * @version 1.0
 */
public class VeiculoTest {

    @BeforeClass
    public static void setup() {
        JediORMEngine.reset();
    }

    /**
     * Testa a adição de um registro de veículo no banco de dados.
     */
    @Test
    public void testeAdicao() {
        FabricanteVeiculo fabricante = FabricanteVeiculo.objects.first();
        ModeloVeiculo modelo = new ModeloVeiculo("Gol Rock in Rio", fabricante);
        modelo.save();
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca("III-6873");
        veiculo.setNumero("1532-03");
        veiculo.setTipo("Onibus");
        veiculo.setAnoFabricacao("1990");
        veiculo.setLotacao(45);
        veiculo.setPrimeiroMotorista("VINICIOS VIEIRA");
        veiculo.setCnhPrimeiroMotorista("3829124905");
        veiculo.setModelo(modelo);
        veiculo.save();
        int registrosEsperados = 1;
        int registrosObtidos = Veiculo.objects.all().count();
        assertEquals(registrosEsperados, registrosObtidos);
    }

    /**
     * Testa se algum registro de veículo foi gravado no banco de dados.
     */
    @Test
    public void testeConsulta() {
        assertNotEquals(0, Veiculo.objects.all().count()); 
    }

    /**
     * Testa a edição de um registro de veículo no banco de dados.
     */
    @Test
    public void testeEdicao() {
        String placaEsperada = "ABC-1234";
        Veiculo veiculo = Veiculo.objects.get("placa", "III-6873");
        veiculo.setPlaca(placaEsperada);
        veiculo.save();
        String placaObtida = veiculo.getPlaca();
        assertEquals(placaEsperada, placaObtida);
    }

    /**
     * Testa a remoção de um registro de veículo no banco de dados.
     */
    @Test
    public void testRemocao() {
        int registrosEsperados = 0;
        Veiculo.objects.all().delete();
        int registrosObtidos = Veiculo.objects.all().count();
        assertEquals(registrosEsperados, registrosObtidos);
    }

}
