package br.gov.go.agr.siav.models;

import jedi.db.models.DateTimeField;
import jedi.db.models.FloatField;
import jedi.db.models.IntegerField;
import jedi.db.models.Manager;
import jedi.db.models.Model;
import jedi.db.models.Table;
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
@Table(name = "boletos_bancarios")
public class BoletoBancario extends Model {

    private static final long serialVersionUID = -8809956676794916770L;

    @IntegerField
    private Integer numero;

    @DateTimeField
    private DateTime dataPagamento;

    @FloatField
    private Float valor;

    public static Manager objects = new Manager(BoletoBancario.class);

    // Generated by Jedi ORM
    public AutorizacaoViagem getAutorizacaoViagem() {
        return AutorizacaoViagem.objects.get("boleto_bancario_id", this.id);
    }

}