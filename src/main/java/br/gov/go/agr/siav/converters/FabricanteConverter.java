package br.gov.go.agr.siav.converters;

import static com.google.common.base.Strings.isNullOrEmpty;

import javax.enterprise.context.ApplicationScoped;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;

import br.gov.go.agr.siav.models.FabricanteVeiculo;

/**
 * Converte uma string em um fabricante de veículo.
 * 
 * @author thiago
 * @version 1.0
 */
@Convert(FabricanteVeiculo.class)
@ApplicationScoped
public class FabricanteConverter implements Converter<FabricanteVeiculo> {

    @Override
    public FabricanteVeiculo convert(String value, Class<? extends FabricanteVeiculo> type) {
        if (!isNullOrEmpty(value)) {
            int id = Integer.parseInt(value);
            return FabricanteVeiculo.objects.get("id", id);
        }
        return null;
    }

}
