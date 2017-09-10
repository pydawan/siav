package br.gov.go.agr.siav;

import java.io.File;

import jedi.db.engine.JediORMEngine;
import jedi.db.models.QuerySet;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.gov.go.agr.siav.enums.PerfilUsuario;
import br.gov.go.agr.siav.enums.PermissaoUsuario;
import br.gov.go.agr.siav.enums.SituacaoCadastro;
import br.gov.go.agr.siav.enums.TipoAutenticacao;
import br.gov.go.agr.siav.enums.TipoUsuario;
import br.gov.go.agr.siav.models.Empresa;
import br.gov.go.agr.siav.models.Perfil;
import br.gov.go.agr.siav.models.Permissao;
import br.gov.go.agr.siav.models.Usuario;
import br.gov.go.agr.siav.services.AutenticacaoService;

/**
 * Teste Unitário para rotinas de banco de dados.
 * 
 * @author thiago
 * @version 1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseTest {

    private static Perfil perfil;
    private static Permissao permissao;
    private static QuerySet<Perfil> perfis;
    private static QuerySet<Permissao> permissoes;
    private static String caminho;
    private static File script;

    @BeforeClass
    public static void setup() {
        JediORMEngine.CODE_GENERATION = false;
        JediORMEngine.FOREIGN_KEY_CHECKS = false;
        JediORMEngine.reset();
        caminho = String.format("%s/%s", System.getProperty("user.dir"), "src/main/webapp/WEB-INF/sql/empresas.sql");
        caminho = caminho.replaceAll("/", File.separator);
        script = new File(caminho);
        JediORMEngine.execute(script);
    }

    // testPermissoes
    @Test
    public void testA() { 
        permissoes = new QuerySet<Permissao>();
        for (PermissaoUsuario p : PermissaoUsuario.values()) {
            permissao = new Permissao(p.getValor(), p.getDescricao(), SituacaoCadastro.ATIVO.getValor());
            permissoes.add(permissao);
        }
        permissoes.save();
        Assert.assertTrue(permissoes.isPersited());
    }

    // testPerfis
    @Test
    public void testB() {
        perfis = new QuerySet<Perfil>();
        permissoes = Permissao.objects.all();
        for (PerfilUsuario p : PerfilUsuario.values()) {
            perfil = new Perfil(p.getValor(), p.getDescricao(), SituacaoCadastro.ATIVO.getValor(), permissoes);
            perfis.add(perfil);
        }
        perfis.save();
        Assert.assertTrue(perfis.isPersited());
    }

    // testUsuarios
    @Test
    public void testC() {
        Empresa.objects.<Empresa> all().forEach(empresa -> {
            Usuario usuario = new Usuario();
            usuario.setLogin(empresa.getCnpj());
            AutenticacaoService.gerarSenha(usuario, "agr");
            usuario.setNome("");
            usuario.setEmail("");
            usuario.setTipo(TipoUsuario.EXTERNO.getValor());
            usuario.setAutenticacao(TipoAutenticacao.BANCO_DE_DADOS.getValor());
            usuario.setSituacao(SituacaoCadastro.ATIVO.getValor());
            usuario.setPerfil(Perfil.objects.get("nome", "Padrão"));
            usuario.save();
        });
    }

}
