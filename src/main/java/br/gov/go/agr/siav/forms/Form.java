package br.gov.go.agr.siav.forms;

import java.util.List;

import jedi.db.models.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Formulário genérico que tira proveito dos recursos do CDI.
 * 
 * @author thiago
 * @version 1.0
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = false, chain = true)
public class Form<T extends Model> {

    private String acao;
    private T objeto;
    private List<T> objetos;
    private FormData extra; // dados adicionais

    public Form() {}
}
