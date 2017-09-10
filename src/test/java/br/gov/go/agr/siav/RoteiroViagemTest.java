package br.gov.go.agr.siav;

import jedi.db.engine.JediORMEngine;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.go.agr.siav.models.RoteiroViagem;

/**
 * Teste unitário da classe de objetos RoteiroViagem.
 * 
 * O teste avalia o funcionamento da persistência de objetos da classe RoteiroViagem em um banco de dados.
 * Os comportamentos testados são: adição, edição, consulta e remoção.
 * 
 * Redmine: tarefa #79
 * 
 * @author thiago
 * @version 1.0
 */
public class RoteiroViagemTest {

    /**
     * Recria todas as tabelas do banco de dados.
     */
    @BeforeClass
    public static void setup() {
        JediORMEngine.reset();
    }

    /**
     * Testa a consulta de um registro de roteiro de viagem.
     */
    @Test
    public void testConsulta() {
        int registrosEsperados = 0;
        int registrosObtidos = RoteiroViagem.objects.all().count();
        assertEquals(registrosEsperados, registrosObtidos);
    }

    /**
     * Testa a adição de um registro de roteiro de viagem.
     */
    @Test
    public void testAdicao() {
        RoteiroViagem roteiroViagem = new RoteiroViagem();
        roteiroViagem.setOrigem("Uruaçu");
        roteiroViagem.setDestino("Niquelândia");
        roteiroViagem.setDistancia(88.00f);
        roteiroViagem.save();
        assertTrue(roteiroViagem.isPersisted());
    }

    /**
     * Testa a edição de um registro de roteiro de viagem.
     */
    @Test
    public void testEdicao() {
        String origemEsperada = "Niquelândia";
        RoteiroViagem roteiroViagem = RoteiroViagem.objects.get("origem", "Uruaçu");
        roteiroViagem.setOrigem(origemEsperada);
        roteiroViagem.save();
        String origemObtida = roteiroViagem.getOrigem();
        assertEquals(origemEsperada, origemObtida);
    }

    /**
     * Testa a remoção de um registro de roteiro de viagem.
     */
    @Test
    public void testRemocao() {
        int registrosEsperados = 0;
        int registrosObtidos = RoteiroViagem.objects.all().delete().count();
        assertEquals(registrosEsperados, registrosObtidos);
    }
}
