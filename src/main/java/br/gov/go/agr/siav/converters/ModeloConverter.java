package br.gov.go.agr.siav.converters;

import static com.google.common.base.Strings.isNullOrEmpty;

import javax.enterprise.context.ApplicationScoped;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;

import br.gov.go.agr.siav.models.ModeloVeiculo;

/**
 * Converte uma string em um modelo de veículo.
 * 
 * @author thiago
 * @version 1.0
 *
 */
@Convert(ModeloVeiculo.class)
@ApplicationScoped
public class ModeloConverter implements Converter<ModeloVeiculo> {

    @Override
    public ModeloVeiculo convert(String value, Class<? extends ModeloVeiculo> type) {
        if (!isNullOrEmpty(value)) {
            int id = Integer.parseInt(value);
            return ModeloVeiculo.objects.get("id", id);
        }
        return null;
    }

}
