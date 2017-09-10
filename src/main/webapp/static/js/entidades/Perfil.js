/**
 * @autor thiago.monteiro
 * @version 1.0
 * @date 29/10/2015
 */

// Bloco executado após o carregamento total da página. 
$(document).ready(function() {
    /*****************
     * Controladores *
     *****************/
    // controlador da tabela.
    var tabela = {
        elemento: $('table#perfis'),
        dados: [],
        container: $('div.panel-body'),
        cabecalho: function() {
            var html = '';
            html += '<thead>';
            html += '    <tr>';
            html += '        <th>#</th>';
            html += '        <th>Nome</th>';
            html += '        <th>Descrição</th>';
            html += '        <th>Situação</th>';
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
                perfil = e;
                html += tabela.linha(perfil);
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
            html += '<table class="table table-hover table-condensed" id="perfis">';
            html += tabela.cabecalho();
            html += tabela.corpo();
            html += tabela.rodape();
            html += '</table>';
            return html;
        },
        colunas: function(perfil) {
            var colunas = ''; 
            colunas += '<td name="id">' + perfil.id + '</td>';
            colunas += '<td name="nome">' + perfil.nome + '</td>';
            colunas += '<td name="descricao">' + perfil.descricao + '</td>';
            colunas += '<td name="situacao">' + perfil.situacao + '</td>';
            var permissoes = [];
            $(perfil.permissoes).each(function(i, e) {
                permissoes.push('' + e.id);
            });
            permissoes = JSON.stringify(permissoes);
            colunas += '<td name="permissoes" style="display: none">{0}</td>'.format(permissoes);
            colunas += '<td name="acao" colspan="2" align="center">'; 
            colunas +=     '<a class="action editar destaque"><i class="glyphicon glyphicon-pencil"></i> Editar</a>&nbsp;&nbsp;&nbsp;&nbsp;';
            colunas +=     '<a class="action remover destaque"><i class="glyphicon glyphicon-trash"></i> Remover</a>';
            colunas += '</td>';
            return colunas;
        },
        linha: function(perfil) {
            var linha = '<tr id="linha-' + perfil.id + '" role="row">' + tabela.colunas(perfil) + '</tr>';
            return linha;
        },
        criar: function(colecao) {
            if (arguments.length == 1) {
                var objeto = arguments[0];
                if (objeto instanceof Array) {
                    tabela.dados = $(objeto);
                    tabela.container.html(tabela.html());
                    tabela.elemento = $('table#perfis');
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
                source: perfis
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
        adicionar: function(perfil) {
            var linha = tabela.linha(perfil);
            tabela.dados.push(perfil);
            tabela.elemento.append(linha);
        },
        editar: function(perfil) {
            var $linha = $('#linha-' + perfil.id);
            var colunas = this.colunas(perfil);
            $linha.html(colunas);
        },
        remover: function(perfil) {
            tabela.elemento.find('tr#linha-' + perfil.id).remove();
            perfil.id = perfil.id * -1;
            perfil = {};
        },
        listar: function() {
            console.log('Listando permissões de usuário');
        },
        atualizar: function() {
            $.ajax({
                type: 'GET',
                url: '/siav/admin/perfil/listar/',
                cache: false,
                success: function(data, status, xhr) {
                    tabela.recriar(data);
                }
            });
        },
        salvar: function(perfil) {
            var id = $('[id="perfil.id"]').val();
            id = parseInt(id);
            if (id == 0) {
                tabela.adicionar(perfil);
            } else if (id > 0) {
                tabela.editar(perfil);
            } else {
                tabela.remover(perfil);
            }
            tabela.atualizar();
        },
        limpar: function() {
            console.log('Limpando a tabela de permissões de usuário.');
        }
    };
    // controlador de permissões.
    var permissoes = {
        container: function() {
            return $('[id="perfil.id"]');
        },
        selecionadas: function() {
            return $('option:selected', $campo_permissoes);
        },
        hiddens: function() {
            return $('input[name^="extra.permissoes"]', $formulario_edicao);
        },
        hidden: function(permissao) {
            var seletor = 'input[name^="extra.permissoes"][value="{0}"]';
            seletor = seletor.format($(permissao).val());
            return $(seletor, $formulario_edicao);
        },
        _html: function(i, e) {
            var _html = '<input type="hidden" name="extra.permissoes[{0}]" value="{1}" />';
            _html = _html.format(i, e);
            return _html;
        },
        html: function(permissao) {
            return permissoes._html($(permissao).index(), $(permissao).val());
        },
        atualizar: function() {
            permissoes.hiddens().remove();
            permissoes.selecionadas().each(function(i, e) {
                permissoes.container().after(permissoes._html(i, $(e).val()));
            });
        },
        adicionar: function(permissao) {
            if ($(permissao).val() == undefined) {
                permissoes.remover();
                $('option[value!=undefined]', $campo_permissoes).each(function(i, e) {
                    permissoes.container().after(permissoes.html(e));
                });
            } else {
                permissoes.atualizar();
            }
        },
        remover: function(permissao) {
            // todas permissões.
            if ($(permissao).val() == undefined) {
                permissoes.hiddens().remove();
            } else {
                permissoes.atualizar();
            }
        },
        buscar: function(permissao) { 
            permissao = $formulario_edicao.find(permissoes.hidden(permissao));
            return permissao;
        }
    };
    /*******************
     * Elementos da UI *
     *******************/
    // Botões
    var $botao_adicionar = $('a[id="perfil.adicionar"]');
    var $botoes_editar = $('a.action.editar');
    var $botoes_remover = $('a.action.remover');
    var $botoes_limpar = $(':reset');
    // Janelas modais
    var $janela_edicao = $('.perfil.modal.edicao');
    var $janela_remocao = $('.perfil.modal.remocao');
    // Formulários
    var $formulario_edicao = $('form[id="perfil.formulario.edicao"]');
    var $formulario_remocao = $('form[id="perfil.formulario.remocao"]');
    // Caixas de seleção
    var $campo_nome = $('[id="perfil.nome"]');
    var $campo_situacao = $('[id="perfil.situacao"]');
    var $campo_permissoes = $('[id="perfil.permissoes"]');
    /************************************
     * Configuração dos elementos da UI *
     ************************************/
    // Caixas de seleção multipla
     $campo_permissoes.multiselect({
         buttonWidth: '100%',
         maxHeight: 200,
         filterPlaceholder: 'Pesquisar',
         enableFiltering: true,
         enableCaseInsensitiveFiltering: false,
         includeFilterClearBtn: false,
         includeSelectAllOption: true,
         selectAllJustVisible: false,
         selectAllText: 'Todas',
         allSelectedText: 'Todas as permissões',
         nonSelectedText: 'Nenhuma permissão',
         selectAllNumber: true,
         selectedClass: null,
         buttonClass: 'form-control',
         enableClickableOptGroups: false,
         onChange: function(element, checked) {
             // permissão marcada
             if (checked == true) {
                 permissoes.adicionar(element);
             } else { // permissão desmarcada
                 permissoes.remover(element);
             }
             $campo_permissoes.multiselect('rebuild');
         },
         onSelectAll: function() {
             
         }
     });
    // Formulário - validação
    $formulario_edicao.validate({
        debug: false,
        onkeyup: false,
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            'objeto.nome': {
                required: true,
                maxlength: 255
            },
            'objeto.descricao': {
                required: false,
                maxlength: 255
            }
        }
    });
    // Formulário - submissão
    $formulario_edicao.submit(function() {
        var dados = $(this).serializeObject();
        dados['objetos'] = [];
        dados['extra'] = {
            'permissoes': []
        };
        $.map(permissoes.hiddens(), function(hidden) {
            dados['extra']['permissoes'].push('' + hidden.value);
        });
        dados.extra.permissoes = dados.extra.permissoes.sort();
        dados = JSON.stringify(dados);
        $(this).validate();
        if ($(this).valid()) {
            $.ajax({
                type: 'POST',
                url: '/siav/admin/perfil/salvar',
                dataType: 'json',
                contentType: 'application/json',
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
        dados['objetos'] = [];
        dados['extra'] = {
            'permissoes': []
        };
        dados = JSON.stringify(dados);
        $.ajax({
            type: 'POST',
            url: '/siav/admin/perfil/salvar',
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
    // Formulário - reconfiguração
    $formulario_edicao.reconfigurar = function() {
        $(this).validate().resetForm();
        $(this).find('.has-error').removeClass("has-error");
        $(this).find('.has-success').removeClass("has-success");
        $(this).find('.form-control-feedback').remove();
    }
    // Botão adicionar - clique
    $botao_adicionar.click(function() {
       perfil = {
           id: 0,
           nome: '',
           descricao: '',
           situacao: 'Ativo',
           permissoes: []
       };
       $('[name="acao"]', $formulario_edicao).attr('value', 'adicionar');
       preencher_formulario($formulario_edicao, perfil, 'objeto.');
       $formulario_edicao.reconfigurar();
       $campo_permissoes.multiselect('deselectAll', false);
       $campo_permissoes.multiselect('updateButtonText');
       $janela_edicao.modal('show');
    });
    $botoes_limpar.click(function() {
        perfil = {
            nome: '',
            descricao: '',
            situacao: 'Ativo',
            permissoes: []
        };
        preencher_formulario($formulario_edicao, perfil, 'objeto.');
        permissoes.remover();
        $formulario_edicao.reconfigurar();
        $campo_permissoes.multiselect('deselectAll', false);
        $campo_permissoes.multiselect('updateButtonText');
        $('.selectpicker').selectpicker('refresh');
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
                        perfil[atributo] = valor;
                    }
                });
                $('[name="acao"]', $formulario_edicao).attr('value', 'editar');
                preencher_formulario($formulario_edicao, perfil, 'objeto.');
                $formulario_edicao.reconfigurar();
                perfil.permissoes = $.parseJSON(perfil.permissoes);
                var $permissoes = $('input[name^="extra.permissoes"]', $formulario_edicao);
                $permissoes.remove();
                $(perfil.permissoes).each(function(i, e) {
                    $campo_permissoes.multiselect('select', e, true);
                });
                $campo_permissoes.multiselect('rebuild');
                switch(perfil['situacao']) {
                    case 'Ativo':
                        $campo_situacao.selectpicker('val', 'Ativo');
                        break;
                    case 'Inativo':
                        $campo_situacao.selectpicker('val', 'Inativo');
                        break;
                }
                $campo_situacao.selectpicker('refresh');
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
                perfil = {
                    id: $coluna.text()
                }
                $('[name="acao"]', $formulario_remocao).attr('value', 'remover');
                preencher_formulario($formulario_remocao, perfil, 'objeto.');
                $janela_remocao.modal('show');
            });
        });
    };
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
        url: '/siav/admin/perfil/listar',
        cache: false,
        success: function(data, status, xhr) {
            tabela.recriar(data);
        }
    });
});