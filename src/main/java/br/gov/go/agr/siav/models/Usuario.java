package br.gov.go.agr.siav.models;

import jedi.db.models.CharField;
import jedi.db.models.DateTimeField;
import jedi.db.models.EmailField;
import jedi.db.models.ForeignKeyField;
import jedi.db.models.Manager;
import jedi.db.models.Model;
import jedi.types.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = false, chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends Model {

    private static final long serialVersionUID = -8928458809871762656L;

    @CharField(max_length = 255)
    private String nome;

    @CharField(max_length = 30, unique = true)
    private String login;

    @CharField(max_length = 128)
    private String senha;

    @CharField(max_length = 128, required = false)
    private String salt;

    @EmailField
    private String email;

    @CharField(max_length = 7, default_value = "Externo")
    private String tipo;

    @CharField(max_length = 16, default_value = "Banco de Dados")
    private String autenticacao;

    @CharField(max_length = 7, default_value = "Ativo")
    private String situacao;

    @ForeignKeyField
    private Perfil perfil;

    @DateTimeField(auto_now_add = true, auto_now = true)
    private DateTime dataCriacao;

    @DateTimeField(auto_now = true, required = false)
    private DateTime dataEdicao;

    public static Manager objects = new Manager(Usuario.class);
}
