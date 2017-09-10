package br.gov.go.agr.siav.interfaces;

import jedi.db.models.Model;
import br.gov.go.agr.siav.forms.Form;

/**
 * Interface que define os comportamentos padrão de um controlador.
 * 
 * @author thiago
 * @version 1.0
 */
public interface IController<T extends Model> {
    void index();
    void listar();
    void novo();
    void buscar(int id);
    void salvar(Form<T> form);
    void serialize(Object object);
    void internalServerError();
}
