package br.gov.go.agr.siav.models;

import jedi.db.models.ForeignKeyField;
import jedi.db.models.Manager;
import jedi.db.models.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Classe que representa permissões atribuídas a um perfil ou grupo de usuários.
 * 
 * @author thiago
 * @version
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = false, chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Privilegio extends Model {

    private static final long serialVersionUID = 4021041210725776139L;

    @ForeignKeyField
    private Perfil perfil;

    @ForeignKeyField
    private Permissao permissao;

    public static Manager objects = new Manager(Privilegio.class);

}
