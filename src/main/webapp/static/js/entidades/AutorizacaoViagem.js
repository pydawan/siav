/**
 * @author thiago.monteiro
 * @version 1.0
 * @date 17/11/2015
 */

$(document).ready(function() {
    $('#aviso').modal({
        keyboard : false,
        backdrop : 'static'
    });
    var $campo_tipo_viagem = $('[id="autorizacao.viagem.tipo"]');
    var $campo_data_saida = $('[id="autorizacao.viagem.dataSaida"]');
    var $campo_data_retorno = $('[id="autorizacao.viagem.dataRetorno"]');
    var $grupo_data_saida = $('#grupo-data-saida');
    var $grupo_data_retorno = $('#grupo-data-retorno');
    $grupo_data_saida.hide();
    $grupo_data_retorno.hide();
    $campo_tipo_viagem.change(function() {
        switch ($(this).val()) {
            case 'Ida':
                $grupo_data_saida.show();
                $grupo_data_retorno.hide();
                break;
            case 'Ida e Volta':
                $grupo_data_saida.show();
                $grupo_data_retorno.show();
                break;
            default:
                $grupo_data_saida.hide();
                $grupo_data_retorno.hide();
                break;
        }
    });
    $('#botao-pesquisar').click(function() {
        var url = '/siav/admin/itinerario/pesquisar';
        var municipio = $('#municipio').val();
        var filtro = $('#filtro').val(); 
        if (municipio == '') {
            alert('ATENÇÃO: Essa pesquisa ira retornar todos os registros existentes e por isso pode causar lentidão no sistema.');
        }
        var dados = {
            'extra': {
                'municipio': municipio,
                'filtro': filtro
            }
        }; 
        $.ajax({
            type: 'POST',
            url: url,
            contentType: 'application/json;charset=utf-8', // envio
            dataType: 'json', // retorno
            data: JSON.stringify(dados),
            success: function(data, status, xhr) {
                $('#trecho').empty();
                $('#trecho').selectpicker('refresh');
                if (data == null || data == [] || data == {} || data == '') {
                    alert('Nenhum registro encontrado.');
                }
                $(data).each(function(i, itinerario) {
                    var $option = $(new Option(itinerario.trecho, itinerario.trecho));
                    $option.attr('data-extensao', itinerario.extensao.money());
                    $option.attr('data-valor', itinerario.precoIdaOnibus.money());
                    $('#trecho').append($option);
                });
                $('#trecho').selectpicker('refresh');
                $('#trecho').selectpicker('render');
            }
        });
        $('#botao-adicionar').click(function() {
            var $trechos_selecionados = $('#trecho option:selected').selectpicker('selectAll');
            var html = '';
            var linha = '';
            var trecho = '';
            var extensao = '';
            var valor = '';
            var valor_total = 0;
            var extensao_total = 0;
            $('#roteiro-viagem tbody').empty();
            $trechos_selecionados.each(function(i, e) {
                linha = '';
                linha += '<tr>';
                linha += '    <td>{0}</td>';
                linha += '    <td>{1} km</td>';
                linha += '    <td>R$ {2}</td>';
                linha += '    <td align="center">';
                linha += '        <a class="action remover destaque"><i class="glyphicon glyphicon-trash"></i> Remover</a>';
                linha += '    </td>';
                linha += '</tr>';
                trecho = $(e).val();
                extensao = $(e).attr('data-extensao');
                valor = $(e).attr('data-valor');
                html += linha.format(trecho, extensao, valor);
                valor = valor.replace('.', '');
                valor = valor.replace(',', '.');
                valor = parseFloat(valor);
                valor_total += valor;
                extensao = extensao.replace('.', '');
                extensao = extensao.replace(',', '.');
                extensao = parseFloat(extensao);
                extensao_total += extensao;
            });
            $('#roteiro-viagem tbody').append(html);
            $('#valor_total').text('R$ ' + valor_total.money());
            $('#extensao_total').text(extensao_total.money() + ' km');
            var $botoes_remover = $('.action.remover', '#roteiro-viagem');
            var $botao = null;
            var $td = null;
            var $tr = null;
            $botoes_remover.each(function(i, e) {
                $botao = $(e);
                $botao.click(function() {
                    $td = $botao.parent();
                    $tr = $td.parent();
                    $tr.empty();
                });
            });
        });
    });
});