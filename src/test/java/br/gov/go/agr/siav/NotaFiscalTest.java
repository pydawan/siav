package br.gov.go.agr.siav;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import jedi.db.engine.JediORMEngine;
import jedi.types.DateTime;

import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.go.agr.siav.models.NotaFiscal;

/**
 * Teste unitário da classe de objetos NotaFiscal.
 * 
 * O teste avalia o funcionamento da persistência de objetos da classe
 * NotaFiscal em um banco de dados. Os comportamentos testados são: adição,
 * edição, consulta e remoção.
 * 
 * Redmine: tarefa #85
 * 
 * @author thiago
 * @version 1.0
 */
public class NotaFiscalTest {

    /**
     * Prepara o ambiente de testes recriando todas as tabelas do banco de dados
     * antes de iniciar os testes.
     */
    @BeforeClass
    public static void setup() {
        JediORMEngine.reset();
    }

    /**
     * Testa a consulta por um registro de nota fiscal.
     */
    @Test
    public void testConsulta() {
        int registrosEsperados = 0;
        int registrosObtidos = NotaFiscal.objects.all().count();
        assertEquals(registrosEsperados, registrosObtidos);
    }

    /**
     * Testa a adição de um registro de nota fiscal.
     */
    @Test
    public void testAdicao() {
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setValor(new BigDecimal(150.00));
        notaFiscal.setSerie("A");
        notaFiscal.setNumero(963);
        notaFiscal.setDataEmissao(new DateTime(2014, 9, 25));
        notaFiscal.save();
        assertTrue(notaFiscal.isPersisted());
    }

    /**
     * Testa a edição de um registro de nota fiscal.
     */
    @Test
    public void testEdicao() {
        String serieEsperada = "U";
        NotaFiscal notaFiscal = NotaFiscal.objects.get("numero", 963);
        notaFiscal.setSerie(serieEsperada);
        notaFiscal.save();
        String serieObtida = notaFiscal.getSerie();
        assertEquals(serieEsperada, serieObtida);
    }

    /**
     * Testa a remoção de um registro de nota fiscal.
     */
    @Test
    public void testRemocao() {
        int registrosEsperados = 0;
        NotaFiscal.objects.all().delete();
        int registrosObtidos = NotaFiscal.objects.count();
        assertEquals(registrosEsperados, registrosObtidos);
    }
}
