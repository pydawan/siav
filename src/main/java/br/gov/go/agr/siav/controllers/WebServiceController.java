package br.gov.go.agr.siav.controllers;

import static br.com.caelum.vraptor.view.Results.json;
import static br.com.caelum.vraptor.view.Results.status;
import static br.com.caelum.vraptor.view.Results.xml;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.jsoup.Jsoup;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.gov.go.agr.siav.dto.EmpresaDTO;
import br.gov.go.agr.siav.dto.EnderecoDTO;
import br.gov.go.agr.siav.enums.PerfilUsuario;
import br.gov.go.agr.siav.enums.PermissaoUsuario;
import br.gov.go.agr.siav.enums.SituacaoCadastro;
import br.gov.go.agr.siav.enums.TipoAutenticacao;
import br.gov.go.agr.siav.enums.TipoUsuario;
import br.gov.go.agr.siav.enums.TipoVeiculo;
import br.gov.go.agr.siav.enums.TipoViagem;
import br.gov.go.agr.siav.models.FabricanteVeiculo;
import br.gov.go.agr.siav.models.Goias;
import br.gov.go.agr.siav.models.Itinerario;
import br.gov.go.agr.siav.models.ModeloVeiculo;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controlador de web services disponíveis para integração entre sistemas web.
 * 
 * @author thiago
 * @version 1.0
 *
 */
@Controller
public class WebServiceController {

    @Inject
    private Result result;

    /**
     * webservice que busca todas os itinerários cadastrados.
     */
    @Get(value = {"/webservice/itinerarios/{formato}", "/webservice/itinerarios/{formato}/"})
    public void itinerarios(String formato) {
        formato = formato.toLowerCase();
        List<Itinerario> list = Itinerario.objects.all();
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(list).serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(list.toArray(), "itinerarios").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }

    /**
     * webservice que busca um itinerário cadastrado pelo trecho informado.
     * 
     * @param trecho
     */
    @Get(value = {"/webservice/itinerarios/{trecho}/{formato}", "/webservice/itinerarios/{trecho}/{formato}/"})
    public void itinerarios(String trecho, String formato) {
        trecho = trecho == null ? "" : trecho;
        trecho = "trecho__istartswith=" + trecho;
        List<Itinerario> list = Itinerario.objects._filter(trecho);
        formato = formato.toLowerCase();
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(list).serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(list.toArray(), "itinerarios").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }

    /**
     * webservice que busca todos os municípios goianos.
     */
    @Get(value = {"/webservice/municipios-goianos/{formato}", "/webservice/municipios-goianos/{formato}/"})
    public void municipiosGoianos(String formato) {
        formato = formato.toLowerCase();
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(Goias.municipios).serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(Goias.municipios, "municipios-goianos").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }

    /**
     * webservice que busca os munícipios goianos pelo nome.
     * 
     * @param nome
     */
    @Get(value = {"/webservice/municipios-goianos/{nome}/{formato}", "/webservice/municipios-goianos/{nome}/{formato}/"})
    public void municipiosGoianos(String nome, String formato) {
        formato = formato.toLowerCase();
        final String prefixo = nome.toLowerCase();
        List<String> list = Goias.municipios
        .stream()
        .filter(municipio -> municipio.toLowerCase().startsWith(prefixo))
        .collect(Collectors.toList());
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(list).serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(list, "municipios-goianos").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }

    @Get(value = {"/webservice/tipos-veiculo/{formato}", "/webservice/tipos-veiculo/{formato}/"})
    public void tiposVeiculo(String formato) {
        formato = formato.toLowerCase();
        Object[] list = Stream.of(TipoVeiculo.values()).map(t -> t.getValor()).toArray();
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(list).serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(list, "tipos-veiculo").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }

    @Get(value = {"/webservice/tipos-viagem/{formato}", "/webservice/tipos-viagem/{formato}/"})
    public void tiposViagem(String formato) {
        formato = formato.toLowerCase();
        Object[] list = Stream.of(TipoViagem.values()).map(t -> t.getValor()).toArray();
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(list).serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(list, "tipos-viagem").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }

    @Get(value = {"/webservice/tipos-usuario/{formato}", "/webservice/tipos-usuario/{formato}/"})
    public void tiposUsuario(String formato) {
        formato = formato.toLowerCase();
        Object[] list = Stream.of(TipoUsuario.values()).map(t -> t.getValor()).toArray();
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(list).serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(list, "tipos-usuario").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }

    @Get(value = {"/webservice/tipos-autenticacao/{formato}", "/webservice/tipos-autenticacao/{formato}/"})
    public void tiposAutenticacao(String formato) {
        formato = formato.toLowerCase();
        Object[] list = Stream.of(TipoAutenticacao.values()).map(t -> t.getValor()).toArray();
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(list).serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(list, "tipos-autenticacao").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }

    @Get(value = {"/webservice/situacoes-cadastro/{formato}", "/webservice/situacoes-cadastro/{formato}/"})
    public void situacoesCadastro(String formato) {
        formato = formato.toLowerCase();
        Object[] list = Stream.of(SituacaoCadastro.values()).map(t -> t.getValor()).toArray();
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(list).serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(list, "situacoes-cadastro").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }

    @Get(value = {"/webservice/perfis-usuario/{formato}", "/webservice/perfis-usuario/{formato}/"})
    public void perfisUsuario(String formato) {
        formato = formato.toLowerCase();
        Object[] list = Stream.of(PerfilUsuario.values()).map(t -> t.getValor()).toArray();
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(list).serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(list, "perfis-usuario").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }

    @Get(value = {"/webservice/permissoes-usuario/{formato}", "/webservice/permissoes-usuario/{formato}/"})
    public void permissoesUsuario(String formato) {
        formato = formato.toLowerCase();
        Object[] list = Stream.of(PermissaoUsuario.values()).map(t -> t.getValor()).toArray();
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(list).serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(list, "permissoes-usuario").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }

    @Get(value = {"/webservice/cep/{cep}/{formato}", "/webservice/cep/{cep}/{formato}/"})
    public void consultarCep(String cep, String formato) {
        formato = formato.toLowerCase();
        String url = String.format("http://viacep.com.br/ws/%s/json/", cep, formato);
        try {
            String resposta = Jsoup
            .connect(url)
            .ignoreContentType(true)
            .execute()
            .body();
            ObjectMapper mapper = new ObjectMapper();
            EnderecoDTO dto = mapper.readValue(resposta, EnderecoDTO.class);
            switch (formato) {
                case "json":
                    result.use(json()).indented().withoutRoot().from(dto).serialize();
                    break;
                case "xml":
                    result.use(xml()).indented().from(dto, "endereco").serialize();
                    break;
                default:
                    result.use(status()).notFound();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Get(value = {"/webservice/cnpj/{cnpj}/{formato}", "/webservice/cnpj/{cnpj}/{formato}/"})
    public void buscarEmpresa(String cnpj, String formato) {
        formato = formato.toLowerCase();
        String url = String.format("http://receitaws.com.br/v1/cnpj/%s", cnpj);
        try {
            String resposta = Jsoup
            .connect(url)
            .ignoreContentType(true)
            .execute()
            .body();
            ObjectMapper mapper = new ObjectMapper();
            EmpresaDTO dto = mapper.readValue(resposta, EmpresaDTO.class);
            switch (formato) {
                case "json":
                    result.use(json()).indented().withoutRoot().from(dto).serialize();
                    break;
                case "xml":
                    result.use(xml()).indented().from(dto, "empresa").serialize();
                    break;
                default:
                    result.use(status()).notFound();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Get(value = {"/webservice/fabricante-veiculo/{formato}", "/webservice/fabricante-veiculo/{formato}/"})
    public void fabricanteVeiculo(String formato) {
        formato = formato.toLowerCase();
        List<FabricanteVeiculo> list = FabricanteVeiculo.objects.all();
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(list).serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(list, "fabricantes-veiculo").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }

    @Get(value = {"/webservice/modelo-veiculo/{formato}", "/webservice/modelo-veiculo/{formato}/"})
    public void modeloVeiculo(String formato) {
        formato = formato.toLowerCase();
        List<ModeloVeiculo> list = ModeloVeiculo.objects.all();
        switch (formato) {
            case "json":
                result.use(json()).indented().withoutRoot().from(list).include("fabricante").serialize();
                break;
            case "xml":
                result.use(xml()).indented().from(list, "modelos-veiculo").include("fabricante").serialize();
                break;
            default:
                result.use(status()).notFound();
        }
    }
}
