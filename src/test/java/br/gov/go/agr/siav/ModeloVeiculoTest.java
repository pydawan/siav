package br.gov.go.agr.siav;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import jedi.db.engine.JediORMEngine;

import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.go.agr.siav.models.FabricanteVeiculo;
import br.gov.go.agr.siav.models.ModeloVeiculo;

/**
 * Teste unitário da classe de objetos ModeloVeiculo.
 * 
 * O teste avalia o funcionamento da persistência de objetos da classe ModeloVeiculo 
 * em um banco de dados.
 * 
 * Os comportamentos testados são: adição, edição, consulta e remoção.
 * 
 * Redmine: tarefa #103
 * 
 * @author thiago
 * @version 1.0
 */
public class ModeloVeiculoTest {

    private static FabricanteVeiculo fabricante;

    /**
     * Configura o ambiente de testes.
     * 
     * 1 - Recria todas as tabelas do banco de dados.
     * 2 - Cria um registro de fabricante de veículo.
     */
    @BeforeClass
    public static void setup() {
        JediORMEngine.reset();
        fabricante = new FabricanteVeiculo("Volkswagen");
        fabricante.save();
    }

    /**
     * Testa a adição de um registro de modelo de veículo.
     */
    @Test
    public void testeAdicao() {
        int registros = ModeloVeiculo.objects.bulkCreate(
            new ModeloVeiculo("Gol Rock in Rio", fabricante),
            new ModeloVeiculo("Gol Track", fabricante),
            new ModeloVeiculo("Gol Trendline", fabricante),
            new ModeloVeiculo("Gol Comfortline", fabricante),
            new ModeloVeiculo("Voyage Trendline", fabricante),
            new ModeloVeiculo("Voyage Comfortline", fabricante),
            new ModeloVeiculo("Voyage Highline", fabricante),
            new ModeloVeiculo("Voyage Evidence", fabricante)
        )
        .all()
        .count();
        assertNotEquals(0, registros);
    }

    /**
     * Testa a edição de um registro de modelo de veículo.
     */
    @Test
    public void testeEdicao() {
        String esperado = "Gol Special";
        ModeloVeiculo modelo = ModeloVeiculo.objects.get("nome", "Gol Rock in Rio");
        modelo.setNome(esperado);
        modelo.save();
        String obtido = modelo.getNome();
        assertEquals(esperado, obtido);
    }

    /**
     * Testa a remoção de um registro de modelo de veículo.
     */
    @Test
    public void testeRemocao() {
        int esperado = 0;
        ModeloVeiculo.objects.all().delete();
        int obtido = ModeloVeiculo.objects.all().count();
        assertEquals(esperado, obtido);
    }

    /**
     * Testa a consulta por um registro de modelo de veículo.
     */
    @Test
    public void testeConsulta() {
        assertNotNull(ModeloVeiculo.objects.all());
    }

}
