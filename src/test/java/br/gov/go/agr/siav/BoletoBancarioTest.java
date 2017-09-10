package br.gov.go.agr.siav;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jedi.db.engine.JediORMEngine;
import jedi.types.DateTime;

import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.go.agr.siav.models.BoletoBancario;

/**
 * Teste unitário da classe de objetos BoletoBancario.
 * 
 * O teste avalia o funcionamento da persistência de objetos da classe BoletoBancario 
 * em um banco de dados. Os comportamentos testados são: adição, edição, consulta e remoção.
 * 
 * Redmine: tarefa #81
 * 
 * @author thiago
 * @version 1.0
 */
public class BoletoBancarioTest {

    /**
     * Recria todas as tabelas do banco de dados.
     */
    @BeforeClass
    public static void setup() {
        JediORMEngine.reset();
    }

    /**
     * Testa a consulta de um registro de boleto bancário.
     */
    @Test
    public void testConsulta() {
        int registrosEsperados = 0;
        int registrosObtidos = BoletoBancario.objects.all().count();
        assertEquals(registrosEsperados, registrosObtidos);
    }

    /**
     * Testa a adição de um registro de boleto bancário.
     */
    @Test
    public void testAdicao() {
        BoletoBancario boletoBancario = new BoletoBancario();
        boletoBancario.setNumero(426800139);
        boletoBancario.setDataPagamento(new DateTime(2014, 9, 25));
        boletoBancario.setValor(33.59f);
        boletoBancario.save();
        assertTrue(boletoBancario.isPersisted());
    }

    /** 
     * Testa a edição de um registro de boleto bancário.
     */
    @Test
    public void testEdicao() {
        float valorEsperado = 33.79f;
        BoletoBancario boletoBancario = BoletoBancario.objects.get("valor", 33.59f);
        boletoBancario.setValor(valorEsperado);
        float valorObtido = boletoBancario.getValor();
        assertEquals(valorEsperado, valorObtido, 0);
    }

    /**
     * Testa a remoção de um registro de boleto bancário.
     */
    @Test
    public void testRemocao() {
        int registrosEsperados = 0;
        BoletoBancario.objects.all().delete();
        int registrosObtidos = BoletoBancario.objects.count();
        assertEquals(registrosEsperados, registrosObtidos);
    }
}
