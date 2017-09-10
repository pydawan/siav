package br.gov.go.agr.siav;

import static org.junit.Assert.assertEquals;

import jedi.db.engine.JediORMEngine;

import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.go.agr.siav.models.FabricanteVeiculo;


/**
 * Teste unitário da classe de objetos FabricanteVeiculo.
 * 
 * O teste avalia o funcionamento da persistência de objetos da classe FabricanteVeiculo 
 * em um banco de dados. Os comportamentos testados são: adição, edição, consulta e remoção.
 * 
 * Redmine: tarefa #102
 * 
 * @author thiago
 * @version 1.0
 */
public class FabricanteVeiculoTest {

    /**
     * Configura o ambiente de testes recriando todas as tabelas e adicionando alguns
     * fabricantes de veículo.
     */
    @BeforeClass
    public static void setup() {
        JediORMEngine.reset();
        FabricanteVeiculo
        .objects
        .bulkCreate(
            new FabricanteVeiculo("Volkswagen"), 
            new FabricanteVeiculo("Fiat"), 
            new FabricanteVeiculo("Pejô"), 
            new FabricanteVeiculo("Ford"), 
            new FabricanteVeiculo("Chevrolet"), 
            new FabricanteVeiculo("Honda"),
            new FabricanteVeiculo("Scania"), 
            new FabricanteVeiculo("Kia")
        )
        .all()
        .orderBy("-id")
        .print();
    }

    /**
     * Testa a adição de um registro de fabricante de veículo.
     */
    @Test
    public void testAdd() {
        FabricanteVeiculo chrysler = new FabricanteVeiculo("Chrysler");
        chrysler.save();
        assertEquals(chrysler, FabricanteVeiculo.objects.get("nome", "Chrysler"));
    }

    /**
     * Testa a consulta de um registro de fabricante de veículo.
     */
    @Test
    public void testGet() {
        // Esperado.
        FabricanteVeiculo expected = new FabricanteVeiculo("Mitsubishi");
        expected.save();
        // Obtido.
        FabricanteVeiculo actual = FabricanteVeiculo.objects.get("nome", "Mitsubishi");
        assertEquals(expected, actual);
    }

    /**
     * Testa a edição de um registro de fabricante de veículo.
     */
    @Test
    public void testEdit() {
        String expected = "Peogeot";
        FabricanteVeiculo.objects.<FabricanteVeiculo>get("nome", "Pejô").setNome(expected).save();
        String actual = FabricanteVeiculo.objects.<FabricanteVeiculo>get("nome", expected).getNome();
        assertEquals(expected, actual);
    }

    /**
     * Testa a remoção de um registro de fabricante de veículo.
     */
    @Test
    public void testRemove() {
        int expected = FabricanteVeiculo.objects.all().count() - 1;
        FabricanteVeiculo.objects.get("nome", "Peogeot").delete();
        int actual = FabricanteVeiculo.objects.all().count();
        assertEquals(expected, actual);
    }

}
