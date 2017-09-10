package br.gov.go.agr.siav;

import static org.junit.Assert.assertEquals;
import jedi.db.engine.JediORMEngine;

import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.go.agr.siav.models.Empresa;

/**
 * Teste unitário da classe de objetos Empresa.
 * 
 * O teste avalia o funcionamento da persistência de objetos da classe Empresa em um banco de dados.
 * Os comportamentos testados são: adição, edição, consulta e remoção.
 * 
 * Redmine: Tarefa #78
 * 
 * @author thiago
 * @version 1.0
 */
public class EmpresaTest {

    @BeforeClass
    public static void setup() {
        JediORMEngine.reset();
    }

    /**
     * Testa a consulta por um registro de empresa.
     */
    @Test
    public void testConsulta() {
        int registrosEsperados = 0;
        int registrosObtidos = Empresa.objects.all().count();
        assertEquals(registrosEsperados, registrosObtidos);
    }

    /**
     * Testa a adição de um registro de empresa.
     */
    @Test
    public void testAdicao() {
        Empresa empresa = new Empresa();
        empresa.setRegistroAgr(String.format("%4d", 1532));
        empresa.setCnpj("06.310.414/0001-01");
        empresa.setRazaoSocial("SANTA CLARA TRANSPORTES E TURISMO LTDA");
        empresa.setNomeFantasia("SANTA CLARA TRANSPORTES");
        empresa.setContato("THIAGO ALEXANDRE MARTINS MONTEIRO");
        empresa.setEmail("thiago.amm.agr@gmail.com");
        empresa.setTelefone("061 82143005");
        empresa.setEndereco("AV. JUSCELINO KUBISTCHEK Nº 06 QD. 11 LT. 01 SANTA EFIGENIA (62) 3092-3047");
        empresa.save();
    }

    /**
     * Testa a edição de um registro de empresa.
     */
    @Test
    public void testEdicao() {
        String telefoneEsperado = "062 82143005";
        Empresa empresa = Empresa.objects.get("telefone", "061 82143005");
        empresa.setTelefone(telefoneEsperado);
        empresa.save();
        String telefoneObtido = empresa.getTelefone();
        assertEquals(telefoneEsperado, telefoneObtido);
    }

    /**
     * Testa a remoção de um registro de empresa.
     */
    @Test
    public void testRemocao() {
        int registrosEsperados = 0;
        Empresa.objects.all().delete();
        int registrosObtidos = Empresa.objects.all().count();
        assertEquals(registrosEsperados, registrosObtidos);
    }
}
