package br.gov.go.agr.siav;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import org.pmw.tinylog.Logger;

import br.gov.go.agr.siav.dto.EmpresaDTO;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WebServiceTest {

    /**
     * Testa a integração com o webservice do receitanet.
     * 
     * @author thiago
     * @version 1.0
     */
    @Test
    public void test() {
        Client client = Client.create();
        WebResource resource = client.resource("http://receitaws.com.br/v1/cnpj/11042649000272");
        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != 200) {
            Logger.error(" : HTTP error code : " + response.getStatus());
        }
        String json = response.getEntity(String.class);
        Logger.info(json);
        ObjectMapper mapper = new ObjectMapper();
        try {
            EmpresaDTO dto= mapper.readValue(json, EmpresaDTO.class);
            Logger.info(dto);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
