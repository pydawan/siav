/**
 * @autor thiago.monteiro
 * @version 1.0
 * @date 19/10/2015
 */

// Bloco executado após o carregamento total da página. 
$(document).ready(function() {
    var tabela = {
        elemento: $('table#permissoes'),
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
                permissao = e;
                html += tabela.linha(permissao);
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
            html += '<table class="table table-hover table-condensed" id="permissoes">';
            html += tabela.cabecalho();
            html += tabela.corpo();
            html += tabela.rodape();
            html += '</table>';
            return html;
        },
        colunas: function(permissao) {
            var colunas = ''; 
            colunas += '<td name="id">' + permissao.id + '</td>';
            colunas += '<td name="nome">' + permissao.nome + '</td>';
            colunas += '<td name="descricao">' + permissao.descricao + '</td>';
            colunas += '<td name="situacao">' + permissao.situacao + '</td>';
            colunas += '<td name="acao" colspan="2" align="center">'; 
            colunas +=     '<a class="action editar destaque"><i class="glyphicon glyphicon-pencil"></i> Editar</a>&nbsp;&nbsp;&nbsp;&nbsp;';
            colunas +=     '<a class="action remover destaque"><i class="glyphicon glyphicon-trash"></i> Remover</a>';
            colunas += '</td>';
            return colunas;
        },
        linha: function(permissao) {
            var linha = '<tr id="linha-' + permissao.id + '" role="row">' + tabela.colunas(permissao) + '</tr>';
            return linha;
        },
        criar: function(colecao) {
            if (arguments.length == 1) {
                var objeto = arguments[0];
                if (objeto instanceof Array) {
                    tabela.dados = $(objeto);
                    tabela.container.html(tabela.html());
                    tabela.elemento = $('table#permissoes');
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
                source: permissoes
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
        adicionar: function(permissao) {
            var linha = tabela.linha(permissao);
            tabela.dados.push(permissao);
            tabela.elemento.append(linha);
        },
        editar: function(permissao) {
            var $linha = $('#linha-' + permissao.id);
            var colunas = this.colunas(permissao);
            $linha.html(colunas);
        },
        remover: function(permissao) {
            tabela.elemento.find('tr#linha-' + permissao.id).remove();
            permissao.id = permissao.id * -1;
            permissao = {};
        },
        listar: function() {
            console.log('Listando permissões de usuário');
        },
        atualizar: function() {
            $.ajax({
                type: 'GET',
                url: '/siav/admin/permissao/listar/',
                cache: false,
                success: function(data, status, xhr) {
                    tabela.recriar(data);
                }
            });
        },
        salvar: function(permissao) {
            var id = $('[id="permissao.id"]').val();
            id = parseInt(id);
            if (id == 0) {
                tabela.adicionar(permissao);
            } else if (id > 0) {
                tabela.editar(permissao);
            } else {
                tabela.remover(permissao);
            }
            tabela.atualizar();
        },
        limpar: function() {
            console.log('Limpando a tabela de permissões de usuário.');
        }
    };
    /*******************
     * Elementos da UI *
     *******************/
    // Botões
    var $botao_adicionar = $('a[id="permissao.adicionar"]');
    var $botoes_editar = $('a.action.editar');
    var $botoes_remover = $('a.action.remover');
    var $botoes_limpar = $(':reset');
    // Janelas modais
    var $janela_edicao = $('.permissao.modal.edicao');
    var $janela_remocao = $('.permissao.modal.remocao');
    // Formulários
    var $formulario_edicao = $('form[id="permissao.formulario.edicao"]');
    var $formulario_remocao = $('form[id="permissao.formulario.remocao"]');
    // Caixas de seleção
    var $campo_nome = $('[id="permissao.nome"]');
    var $campo_situacao = $('[id="permissao.situacao"]');
    /************************************
     * Configuração dos elementos da UI *
     ************************************/
    // Formulário - validação
    $formulario_edicao.validate({
        debug: false,
        onkeyup: false,
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            'permissao.nome': {
                required: true,
                maxlength: 255
            },
            'permissao.descricao': {
                required: false,
                maxlength: 255
            }
        },
        messages: {
            'permissao.nome': {
                remote: 'Permissão já cadastrada!'
            }
        }
    });
    // Formulário edição - submissão
    $formulario_edicao.submit(function() {
        var dados = $(this).serializeObject();
        dados = JSON.stringify(dados);
        $(this).validate();
        if ($(this).valid()) {
            $.ajax({
                type: 'POST',
                url: '/siav/admin/permissao/salvar',
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
            url: '/siav/admin/permissao/salvar',
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
       permissao = {
           id: 0,
           nome: '',
           descricao: '',
           situacao: 'Ativo'
       };
       $('[name="acao"]', $formulario_edicao).attr('value', 'adicionar');
       preencher_formulario($formulario_edicao, permissao, 'objeto.');
       $formulario_edicao.reconfigurar();
       $janela_edicao.modal('show');
    });
    $botoes_limpar.click(function() {
        permissao = {
            nome: '',
            descricao: '',
            situacao: 'Ativo'
        };
        preencher_formulario($formulario_edicao, permissao, 'objeto.');
        $formulario_edicao.reconfigurar();
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
                        permissao[atributo] = valor;
                    }
                });
                $('[name="acao"]', $formulario_edicao).attr('value', 'editar');
                preencher_formulario($formulario_edicao, permissao, 'objeto.');
                $formulario_edicao.reconfigurar();
                switch(permissao['situacao']) {
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
                permissao = {
                    id: $coluna.text()
                }
                $('[name="acao"]', $formulario_remocao).attr('value', 'remover');
                preencher_formulario($formulario_remocao, permissao, 'objeto.');
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
//    div_carregando += '        <img src="/siav/static/imagens/ajax-loader.gif" />';
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
        url: '/siav/admin/permissao/listar',
        cache: false,
        success: function(data, status, xhr) {
            tabela.recriar(data);
        }
    });
});