/**
 * @autor thiago.monteiro
 * @version 1.0
 * @date 09/11/2015
 */
$(document).ready(function() {
    var tabela = {
        elemento: $('table#itinerarios'),
        dados: [],
        container: $('div.panel-body'),
        cabecalho: function() {
            var html = '';
            html += '<thead>';
            html += '    <tr>';
            html += '        <th width="5%">#</th>';
            html += '        <th width="18%">Trecho</th>';
            html += '        <th width="8%">Extensão</th>';
            html += '        <th width="10%">Ônibus (I)</th>';
            html += '        <th width="10%">Ônibus (I/V)</th>';
            html += '        <th width="12%">Micro-ônibus (I)</th>';
            html += '        <th width="12%">Micro-ônibus (I/V)</th>';
            html += '        <th width="17%">Ação</th>';
            html += '    </tr>';
            html += '</thead>';
            return html;
        },
        corpo: function() {
            var perfil = null;
            var html = '';
            html += '<tbody>';
            tabela.dados.each(function(i, e) {
                itinerario = e;
                html += tabela.linha(itinerario);
            });
            html += '</tbody>';
            return html;
        },
        rodape: function() {
            var html = '';
            return html;
        },
        html: function() {
            var html = '';
            html += '<table class="table table-hover table-condensed" id="itinerarios">';
            html += tabela.cabecalho();
            html += tabela.corpo();
            html += tabela.rodape();
            html += '</table>';
            return html;
        },
        colunas: function(itinerario) {
            var colunas = ''; 
            colunas += '<td name="id">' + itinerario.id + '</td>';
            colunas += '<td name="origem" style="display: none">' + itinerario.origem + '</td>';
            colunas += '<td name="destino" style="display: none">' + itinerario.destino + '</td>';
            colunas += '<td name="trecho">' + itinerario.trecho + '</td>';
            colunas += '<td name="extensao">' + new Number(itinerario.extensao).money() + ' km </td>';
            colunas += '<td name="precoIdaOnibus"> R$ ' + new Number(itinerario.precoIdaOnibus).money() + '</td>';
            colunas += '<td name="precoIdaVoltaOnibus"> R$ ' + new Number(itinerario.precoIdaVoltaOnibus).money() + '</td>';
            colunas += '<td name="precoIdaMicroOnibus"> R$ ' + new Number(itinerario.precoIdaMicroOnibus).money() + '</td>';
            colunas += '<td name="precoIdaVoltaMicroOnibus"> R$ ' + new Number(itinerario.precoIdaVoltaMicroOnibus).money() + '</td>';
            colunas += '<td name="acao" colspan="2" align="center">'; 
            colunas +=     '<a class="action editar destaque"><i class="glyphicon glyphicon-pencil"></i> Editar</a>&nbsp;&nbsp;&nbsp;&nbsp;';
            colunas +=     '<a class="action remover destaque"><i class="glyphicon glyphicon-trash"></i> Remover</a>';
            colunas += '</td>';
            return colunas;
        },
        linha: function(itinerario) {
            var linha = '<tr id="linha-' + itinerario.id + '" role="row">' + tabela.colunas(itinerario) + '</tr>';
            return linha;
        },
        criar: function(colecao) {
            if (arguments.length == 1) {
                var objeto = arguments[0];
                if (objeto instanceof Array) {
                    tabela.dados = $(objeto);
                    tabela.container.html(tabela.html());
                    tabela.elemento = $('table#itinerarios');
                }
            }
            tabela.paginacao();
            tabela.pesquisa();
            tabela.ordenacao();
            tabela.configurar_botoes_editar();
            tabela.configurar_botoes_remover();
        },
        destruir: function() {
            // Remove eventos associados evitando conflitos na paginação.
            $('body').off('click', '.pagination li');
            tabela.elemento.remove();
        },
        recriar: function(colecao) {
            tabela.destruir();
            tabela.criar(colecao);
        },
        paginacao: function() {
            tabela.elemento.bdt({
                pageRowCount: 10,
                arrowDown: 'fa-angle-down',
                arrowUp: 'fa-angle-up',
                responsive: true
            });
            $('#page-rows-form label').text('Registros por página:');
        },
        pesquisa: function() {
            $('#search').typeahead({
                source: itinerarios
            });
            $('#search').attr('placeholder', 'Pesquisar');
            $('#search').parent().attr('class', 'form-group has-feedback');
            $('#search').after('<i class="form-control-feedback glyphicon glyphicon-search"></i>');
            $('#search').attr('autocomplete', 'off');
        },
        ordenacao: function() {
            tabela.elemento.tablesorter({
                emptyTo: 'none',
                theme : 'default',
                headerTemplate : '{content}{icon}',
                widgetOptions : {
                  columns : ['primary', 'secondary', 'tertiary']
                }
            });
        },
        adicionar: function(itinerario) {
            var linha = tabela.linha(itinerario);
            tabela.dados.push(itinerario);
            tabela.elemento.append(linha);
        },
        editar: function(itinerario) {
            var $linha = $('#linha-' + itinerario.id);
            var colunas = this.colunas(itinerario);
            $linha.html(colunas);
        },
        remover: function(itinerario) {
            tabela.elemento.find('tr#linha-' + itinerario.id).remove();
            itinerario.id = itinerario.id * -1;
            itinerario = {};
        },
        listar: function() {
            console.log('Listando distâncias de usuário');
        },
        atualizar: function() {
            $.ajax({
                type: 'GET',
                url: '/siav/admin/itinerario/listar/',
                cache: false,
                success: function(data, status, xhr) {
                    tabela.recriar(data);
                }
            });
        },
        salvar: function(itinerario) {
            var id = $('[id="itinerario.id"]').val();
            id = parseInt(id);
            if (id == 0) {
                tabela.adicionar(itinerario);
            } else if (id > 0) {
                tabela.editar(itinerario);
            } else {
                tabela.remover(itinerario);
            }
            tabela.atualizar();
        },
        limpar: function() {
            
        }
    };
    /*******************
     * Elementos da UI *
     *******************/
    // Botões
    var $botao_adicionar = $('a[id="itinerario.adicionar"]');
    var $botoes_editar = $('a.action.editar');
    var $botoes_remover = $('a.action.remover');
    var $botoes_limpar = $(':reset');
    // Janelas modais
    var $janela_edicao = $('.itinerario.modal.edicao');
    var $janela_remocao = $('.itinerario.modal.remocao');
    // Formulários
    var $formulario_edicao = $('form[id="itinerario.formulario.edicao"]');
    var $formulario_remocao = $('form[id="itinerario.formulario.remocao"]');
    // Campos de texto
    var $campo_origem = $('[id="itinerario.origem"]');
    var $campo_destino = $('[id="itinerario.destino"]');
    var $campo_trecho = $('[id="itinerario.trecho"]');
    var $campo_extensao = $('[id="itinerario.extensao"]');
    var $campo_rodovia = $('[id="itinerario.rodovia"]');
    var $campo_preco_ida_onibus = $('[id="itinerario.precoIdaOnibus"]');
    var $campo_preco_ida_volta_onibus = $('[id="itinerario.precoIdaVoltaOnibus"]');
    var $campo_preco_ida_microonibus = $('[id="itinerario.precoIdaMicroOnibus"]');
    var $campo_preco_ida_volta_microonibus = $('[id="itinerario.precoIdaVoltaMicroOnibus"]');
    /************************************
     * Configuração dos elementos da UI *
     ************************************/
    // Formulário - validação
    $formulario_edicao.validate({
        debug: false,
        onkeyup: false,
        onfocusout: function(element) {
            $(element).valid();
        },
        rules: {
            'objeto.origem': {
                required: true,
                maxlength: 127
            },
            'objeto.destino': {
                required: true,
                maxlength: 127
            },
            'objeto.extensao': {
                extensao: true
            },
            'objeto.rodovia': {
                required: true
            },
            'objeto.precoIdaOnibus': {
                monetario: true
            },
            'objeto.precoIdaVoltaOnibus': {
                monetario: true
            },
            'objeto.precoIdaMicroOnibus': {
                monetario: true
            },
            'objeto.precoIdaVoltaOnibus': {
                monetario: true
            }
        }
    });
    // Desabilita o recurso de auto-completar.
    $campo_origem.attr('autocomplete', 'off');
    $campo_destino.attr('autocomplete', 'off');
    $campo_rodovia.attr('autocomplete', 'off');
    // Habilitação do auto completar aprimorado.
    $campo_origem.typeahead({
        source: municipios
    });
    $campo_destino.typeahead({
        source: municipios
    });
    $campo_rodovia.typeahead({
        source: rodovias
    });
    // Botão adicionar - clique
    $botao_adicionar.click(function() {
        itinerario = {
            id: 0,
            origem: '',
            destino: '',
            trecho: '',
            extensao: '0,00 km',
            rodovia: '',
            precoIdaOnibus: 'R$ 0,00',
            precoIdaVoltaOnibus: 'R$ 0,00',
            precoIdaMicroOnibus: 'R$ 0,00',
            precoIdaVoltaMicroOnibus: 'R$ 0,00'
        };
       $('[name="acao"]', $formulario_edicao).attr('value', 'adicionar');
       preencher_formulario($formulario_edicao, itinerario, 'objeto.');
       $formulario_edicao.reconfigurar();
       $janela_edicao.modal('show');
    });
    $botoes_limpar.click(function() {
        itinerario = {
            id: 0,
            origem: '',
            destino: '',
            trecho: '',
            extensao: '0,00 km',
            rodovia: '',
            precoIdaOnibus: 'R$ 0,00',
            precoIdaVoltaOnibus: 'R$ 0,00',
            precoIdaMicroOnibus: 'R$ 0,00',
            precoIdaVoltaMicroOnibus: 'R$ 0,00'
        };
        $('[name="acao"]', $formulario_edicao).attr('value', 'limpar');
        preencher_formulario($formulario_edicao, itinerario, 'objeto.');
        $('.selectpicker').selectpicker('refresh');
        $formulario_edicao.reconfigurar();
    });
    // Botões editar - clique
    tabela.configurar_botoes_editar = function() {
        var $botoes_editar = $('a.action.editar');
        $botoes_editar.each(function(i, e) {
            var $botao_editar = $(e);
            $botao_editar.click(function() {
                var $linha = $(this).closest('tr');
                var $colunas = $linha.find('td');
                $colunas.each(function(i, e) {
                    var $coluna = $(e);
                    var atributo = $coluna.attr('name');
                    if (atributo != 'acao') {
                        var valor = $coluna.text().trim();
                        itinerario[atributo] = valor;
                    }
                });
                $('[name="acao"]', $formulario_edicao).attr('value', 'editar');
                preencher_formulario($formulario_edicao, itinerario, 'objeto.');
                $janela_edicao.modal('show');
            });
        });
    };
    // Botões remover - clique
    tabela.configurar_botoes_remover = function() {
        var $botoes_remover = $('a.action.remover'); 
        $botoes_remover.each(function(i, e) {
            var $botao_remover = $(e);
            $botao_remover.click(function() {
                var $linha = $(this).closest('tr');
                var $coluna = $linha.find('td:eq(0)');
                itinerario = {
                    id: $coluna.text()
                }
                $('[name="acao"]', $formulario_remocao).attr('value', 'remover');
                preencher_formulario($formulario_remocao, itinerario, 'objeto.');
                $janela_remocao.modal('show');
            });
        });
    };
    // Formulário - reconfiguração
    $formulario_edicao.reconfigurar = function() {
        $(this).validate().resetForm();
        $(this).find('.has-error').removeClass("has-error");
        $(this).find('.has-success').removeClass("has-success");
        $(this).find('.form-control-feedback').remove();
    };
    // Formulário edição - submissão
    $formulario_edicao.submit(function() {
        var dados = $(this).serializeObject();
        dados['objeto']['trecho'] = dados['objeto']['origem'] + ' / ' + dados['objeto']['destino'];
        dados['objeto']['extensao'] = dados['objeto']['extensao']
        .replace(' km', '')
        .replace(/\./g, '')
        .replace(',', '.');
        $([
           'precoIdaOnibus', 
           'precoIdaVoltaOnibus', 
           'precoIdaMicroOnibus', 
           'precoIdaVoltaMicroOnibus'
       ]).each(function(i, e) {
            dados['objeto'][e] = dados['objeto'][e]
            .replace('R$ ', '')
            .replace(/\./g, '')
            .replace(',', '.');
        });
        dados = JSON.stringify(dados);
        $(this).validate();
        if ($(this).valid()) {
            $.ajax({
                type: 'POST',
                url: '/siav/admin/itinerario/salvar',
                contentType: 'application/json',
                dataType: 'json',
                data: dados,
                cache: false,
                success: function(data, status, xhr) {
                    tabela.salvar(data);
                }
            });
            $janela_edicao.modal('hide');
        }
        return false;
    });
    // Formulário remoção - submissão
    $formulario_remocao.submit(function() {
        var dados = $(this).serializeObject();
        dados = JSON.stringify(dados);
        $.ajax({
            type: 'POST',
            url: '/siav/admin/itinerario/salvar',
            dataType: 'json',
            contentType: 'application/json',
            data: dados,
            cache: false,
            success: function(data, status, xhr) {
                tabela.salvar(data);
            }
        });
        $janela_remocao.modal('hide');
        return false;
    });
    // Fechamento da modal de edição.
    $janela_edicao.on('hidden.bs.modal', function() {
        $botoes_limpar.click();
    });
    /*****************************
     * Blocos de conteúdo (divs) *
     *****************************/
    var div_carregando = '';
    div_carregando += '<div id="carregando" style="margin-top: 35px;">';
    div_carregando += '    <center>';
    div_carregando += '        <i class="fa fa-cog fa-spin fa-5x"></i>';
    div_carregando += '    </center>';
    div_carregando += '    <h6 align="center">CARREGANDO...</h6>';
    div_carregando += '    <br/>';
    div_carregando += '</div>';
    /*********************
     * Requisições  AJAX *
     *********************/
    $(document).ajaxStart(function() {
        $('div.panel-body').html(div_carregando);
        $('div#carregando').show();
     }).ajaxStop(function() {
         $('div#carregando').remove();
     });
    $.ajax({
        type: 'GET',
        url: '/siav/admin/itinerario/listar',
        cache: false,
        success: function(data, status, xhr) {
            tabela.recriar(data);
        }
    });
});