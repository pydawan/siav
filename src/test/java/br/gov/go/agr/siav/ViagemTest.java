package br.gov.go.agr.siav;

import static org.junit.Assert.*;
import jedi.db.engine.JediORMEngine;
import jedi.types.DateTime;

import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.go.agr.siav.models.Viagem;

/**
 * Teste unitário da classe de objetos Viagem.
 * 
 * O teste avalia o funcionamento da persistência de objetos da classe Viagem em um banco de dados.
 * Os comportamentos testados são: adição, edição, consulta e remoção.
 * 
 * Redmine: tarefa #80
 * 
 * @author thiago
 * @version 1.0
 */
public class ViagemTest {

    /**
     * Recria todas as tabelas do banco de dados.
     */
    @BeforeClass
    public static void setup() {
        JediORMEngine.reset();
    }

    /** 
     * Testa a consulta de um registro de viagem.
     */
    @Test
    public void testConsulta() {
        int registrosEsperados = 0;
        int registrosObtidos = Viagem.objects.count();
        assertEquals(registrosEsperados, registrosObtidos);
    }

    /**
     * Testa a adição de um registro de viagem.
     */
    @Test
    public void testAdicao() {
        Viagem viagem = new Viagem();
        viagem.setQuantidadePassageiros(45);
        viagem.setDataSaida(new DateTime(2014, 9, 25, 17, 40));
        viagem.setDataRetorno(new DateTime(2014, 9, 25, 22, 30));
        viagem.setExtensao(176.00f);
        viagem.setTipo("IDA");
        viagem.setLocalSaida("Niquelândia");
        viagem.save();
        assertTrue(viagem.isPersisted());
    }

    /**
     * Testa a edição de um registro de viagem.
     */
    @Test
    public void testEdicao() {
        String tipoEsperado = "IDA E VOLTA";
        Viagem viagem = Viagem.objects.get("tipo", "IDA");
        viagem.setTipo(tipoEsperado);
        viagem.save();
        String tipoObtido = viagem.getTipo();
        assertEquals(tipoEsperado, tipoObtido);
    }

    /**
     * Testa a remoção de um registro de viagem.
     */
    @Test
    public void testRemocao() {
        int registrosEsperados = 0;
        Viagem.objects.all().delete();
        int registrosObtidos = Viagem.objects.count();
        assertEquals(registrosEsperados, registrosObtidos);
    }
}
