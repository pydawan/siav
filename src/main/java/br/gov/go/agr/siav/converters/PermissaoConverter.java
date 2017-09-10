package br.gov.go.agr.siav.converters;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;
import br.gov.go.agr.siav.models.Permissao;

/**
 * Converte uma string em uma permissão de usuário.
 * 
 * @author thiago
 * @version 1.0
 *
 */
@Convert(Permissao.class)
@ApplicationScoped
public class PermissaoConverter implements Converter<Permissao> {

    @Override
    public Permissao convert(String value, Class<? extends Permissao> type) {
        if (!isNullOrEmpty(value)) {
            int id = Integer.parseInt(value);
            return Permissao.objects.get("id", id);
        }
        return new Permissao();
    }

    public Permissao convert(Map<String, String> value) {
        Permissao permissao = new Permissao();
        if (value != null && !value.isEmpty()) {
            permissao = this.convert(value.get("id"), Permissao.class);
        }
        return permissao;
    }

    public List<Permissao> convert(List<String> values) {
        List<Permissao> permissoes = new ArrayList<>();
        if (values != null && !values.isEmpty()) {
            for (String value : values) {
                permissoes.add(this.convert(value, Permissao.class));
            }
        }
        return permissoes;
    }

}
