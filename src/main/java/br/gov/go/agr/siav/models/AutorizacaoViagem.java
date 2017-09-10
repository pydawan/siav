package br.gov.go.agr.siav.models;

import jedi.db.models.ForeignKeyField;
import jedi.db.models.Manager;
import jedi.db.models.Model;
import jedi.db.models.OneToOneField;
import jedi.db.models.Table;

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
@Table(name = "autorizacoes_viagem")
public class AutorizacaoViagem extends Model {

    private static final long serialVersionUID = -428230474483949132L;

    @ForeignKeyField
    private Empresa empresa;

    @ForeignKeyField 
    private Veiculo veiculo;

    @OneToOneField 
    private Viagem viagem;

    @ForeignKeyField 
    private RoteiroViagem roteiro;

    /* 
     * @OneToOneField private NotaFiscal notaFiscal;
     * 
     * @OneToOneField private BoletoBancario boletoBancario;
     */

    public static Manager objects = new Manager(AutorizacaoViagem.class);
}
