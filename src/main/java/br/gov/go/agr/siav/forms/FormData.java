package br.gov.go.agr.siav.forms;

import java.util.HashMap;

/**
 * Dados do formulário genérico.
 * 
 * @author thiago
 * @version 1.0
 */
public class FormData extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) super.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return super.put(key, value);
    }

}
