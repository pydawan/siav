package br.gov.go.agr.siav.models;

import jedi.db.models.CharField;
import jedi.db.models.EmailField;
import jedi.db.models.Manager;
import jedi.db.models.Model;
import jedi.db.models.TextField;

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
public class Contato extends Model {

    private static final long serialVersionUID = -4027626037109204776L;

    @CharField(max_length = 255, required = false)
    private String assunto;

    @CharField(max_length = 255, required = false)
    private String nome;

    @EmailField(required = false)
    private String email;

    @TextField(required = false)
    private String mensagem;

    public static Manager objects = new Manager(Contato.class);
}
