package br.gov.go.agr.siav;

import jedi.db.engine.JediORMEngine;
import jedi.db.models.QuerySet;

import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.go.agr.siav.enums.PerfilUsuario;
import br.gov.go.agr.siav.enums.PermissaoUsuario;
import br.gov.go.agr.siav.enums.SituacaoCadastro;
import br.gov.go.agr.siav.enums.TipoAutenticacao;
import br.gov.go.agr.siav.enums.TipoUsuario;
import br.gov.go.agr.siav.models.Empresa;
import br.gov.go.agr.siav.models.Perfil;
import br.gov.go.agr.siav.models.Permissao;
import br.gov.go.agr.siav.models.Usuario;
import br.gov.go.agr.siav.services.ActiveDirectoryService;
import br.gov.go.agr.siav.services.AutenticacaoService;

/**
 * Testa os dados e comportamentos de usuário.
 * 
 * @author thiago
 * @version 1.0
 */
public class UsuarioTest {

    private static Perfil perfil;
    private static QuerySet<Permissao> permissoes;

    /**
     * Configuração que atencipa a execução de cada teste.
     */
    @BeforeClass
    public static void setup() {
        JediORMEngine.reset();
        permissoes = new QuerySet<Permissao>();
        for (PermissaoUsuario p : PermissaoUsuario.values()) {
            permissoes.add(
                new Permissao(
                    p.getValor(), 
                    p.getDescricao(), 
                    SituacaoCadastro.ATIVO.getValor()
                )
            );
        }
        permissoes.save();
        perfil = new Perfil(
            PerfilUsuario.PADRAO.getValor(), 
            PerfilUsuario.PADRAO.getDescricao(),
            SituacaoCadastro.ATIVO.getValor(),
            permissoes
        );
    }

    /**
     * Testa a criação de usuários externos (dados de autenticação persistidos no banco de dados relacional).
     */
    @Test
    public void test1() {
        Empresa.objects.<Empresa> all().forEach(empresa -> {
            Usuario usuario = new Usuario();
            usuario.setLogin(empresa.getCnpj());
            AutenticacaoService.gerarSenha(usuario, "agr");
            usuario.setNome("");
            usuario.setEmail("");
            usuario.setTipo(TipoUsuario.EXTERNO.getValor());
            usuario.setAutenticacao(TipoAutenticacao.BANCO_DE_DADOS.getValor());
            usuario.setSituacao(SituacaoCadastro.ATIVO.getValor());
            usuario.setPerfil(perfil);
            usuario.save();
        });
    }

    /**
     * Testa a criação de usuários internos (dados de autenticação gerenciados pelo Active Directory).
     */
    @Test
    public void test2() {
        Usuario usuario = ActiveDirectoryService.buscarUsuario("thiago-amm", "senha");
        usuario
        .setNome("Thiago Alexandre Martins Monteiro")
        .setEmail("thiago.amm.agr@gmail.com")
        .setTipo(TipoUsuario.INTERNO.getValor())
        .setAutenticacao(TipoAutenticacao.ACTIVE_DIRECTORY.getValor())
        .setSituacao(SituacaoCadastro.ATIVO.getValor())
        .setPerfil(perfil);
        AutenticacaoService.gerarSenha(usuario, usuario.getSenha());
        usuario.save();
    }
}
