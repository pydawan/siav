package br.gov.go.agr.siav.converters;

import static com.google.common.base.Strings.isNullOrEmpty;

import javax.enterprise.context.ApplicationScoped;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import br.gov.go.agr.siav.models.Perfil;

/**
 * Converte uma string em um perfil de usuário.
 * 
 * @author thiago
 * @version 1.0
 *
 */
@Convert(Perfil.class)
@ApplicationScoped
public class PerfilConverter implements Converter<Perfil> {

    @Override
    public Perfil convert(String value, Class<? extends Perfil> type) {
        if (!isNullOrEmpty(value)) {
            int id = Integer.parseInt(value);
            return Perfil.objects.get("id", id);
        }
        return new Perfil();
    }

}
