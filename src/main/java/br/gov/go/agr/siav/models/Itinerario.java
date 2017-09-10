package br.gov.go.agr.siav.models;

import java.math.BigDecimal;

import jedi.db.models.CharField;
import jedi.db.models.DecimalField;
import jedi.db.models.Manager;
import jedi.db.models.Model;
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
public class Itinerario extends Model {

    private static final long serialVersionUID = 566128561030327653L;

    @CharField(max_length = 127)
    private String origem;

    @CharField(max_length = 127)
    private String destino;

    @CharField(max_length = 255, unique = true)
    private String trecho;

    @DecimalField(scale = 8, precision = 2)
    private BigDecimal extensao;

    @CharField(max_length = 255, default_value = "")
    private String rodovia;

    @DecimalField(scale = 8, precision = 2)
    private BigDecimal precoIdaOnibus; // R$

    @DecimalField(scale = 8, precision = 2)
    private BigDecimal precoIdaVoltaOnibus;

    @DecimalField(scale = 8, precision = 2)
    private BigDecimal precoIdaMicroOnibus;

    @DecimalField(scale = 8, precision = 2)
    private BigDecimal precoIdaVoltaMicroOnibus;

    public static Manager objects = new Manager(Itinerario.class);
}
