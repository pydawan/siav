/**
 * @autor thiago.monteiro
 * @version 1.0
 * @date 03/11/2015
 */

// Bloco executado após o carregamento total da página. 
$(document).ready(function() {
    var tabela = {
        elemento: $('table#usuarios'),
        dados: [],
        container: $('div.panel-body'),
        cabecalho: function() {
            var html = '';
            html += '<thead>';
            html += '    <tr>';
            html += '        <th>#</th>';
            html += '        <th>Nome</th>';
            html += '        <th>Login</th>';
            html += '        <th>E-mail</th>';
            html += '        <th>Tipo</th>';
            html += '        <th>Situação</th>';
            html += '        <th width="17%">Ação</th>';
            html += '    </tr>';
            html += '</thead>';
            return html;
        },
        corpo: function() {
            var usuario = null;
            var html = '';
            html += '<tbody>';
            tabela.dados.each(function(i, e) {
                usuario = e;
                html += tabela.linha(usuario);
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
            html += '<table class="table table-hover table-condensed" id="usuarios">';
            html += tabela.cabecalho();
            html += tabela.corpo();
            html += tabela.rodape();
            html += '</table>';
            return html;
        },
        colunas: function(usuario) {
            var colunas = ''; 
            colunas += '<td name="id">' + usuario.id + '</td>';
            colunas += '<td name="nome">' + usuario.nome + '</td>';
            colunas += '<td name="login">' + usuario.login + '</td>';
            colunas += '<td name="email">' + usuario.email + '</td>';
            colunas += '<td name="tipo">' + usuario.tipo + '</td>';
            colunas += '<td name="situacao">' + usuario.situacao + '</td>';
            colunas += '<td name="perfil" style="display: none;">' + usuario.perfil.nome + '</td>';
            colunas += '<td name="acao" colspan="2" align="center">'; 
            colunas += '<a class="action editar destaque"><i class="glyphicon glyphicon-pencil"></i> Editar</a>&nbsp;&nbsp;&nbsp;&nbsp;';
            colunas += '<a class="action remover destaque"><i class="glyphicon glyphicon-trash"></i> Remover</a></td>';
            return colunas;
        },
        linha: function(usuario) {
            var linha = '<tr id="linha-' + usuario.id + '" role="row">' + tabela.colunas(usuario) + '</tr>';
            return linha;
        },
        criar: function(colecao) {
            if (arguments.length == 1) {
                var objeto = arguments[0];
                if (objeto instanceof Array) {
                    tabela.dados = $(objeto);
                    tabela.container.html(tabela.html());
                    tabela.elemento = $('table#usuarios');
                }
            }
            tabela.paginacao();
            tabela.pesquisa();
            tabela.ordenacao();
            tabela.configurar_botoes_editar();
            tabela.configurar_botoes_remover();
        },
        destruir: function() {
            $('body').off('click', '.pagination li'); // desassocia eventos.
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
                source: usuarios
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
        adicionar: function(usuario) {
            var linha = tabela.linha(usuario);
            tabela.dados.push(usuario);
            tabela.elemento.append(linha);
        },
        editar: function(usuario) {
            var $linha = $('#linha-' + usuario.id);
            var colunas = this.colunas(usuario);
            $linha.html(colunas);
        },
        remover: function(usuario) {
            tabela.elemento.find('tr#linha-' + usuario.id).remove();
            usuario.id = usuario.id * -1;
            usuario = {};
        },
        listar: function() {
            
        },
        atualizar: function() {
            $.ajax({
                type: 'GET',
                url: '/siav/admin/usuario/listar/',
                cache: false,
                success: function(data, status, xhr) {
                    tabela.recriar(data);
                }
            });
        },
        salvar: function(usuario) {
            var id = $('[id="usuario.id"]').val();
            id = parseInt(id);
            if (id == 0) {
                tabela.adicionar(usuario);
            } else if (id > 0) {
                tabela.editar(usuario);
            } else {
                tabela.remover(usuario);
            }
            tabela.atualizar();
        },
        limpar: function() {
            
        }
    };
    var acao = 'adicionar';
    var perfis_usuario = [];
    // Botões
    var $botao_adicionar = $('a[id="usuario.adicionar"]');
    var $botoes_editar = $('a.action.editar');
    var $botoes_remover = $('a.action.remover');
    var $botoes_limpar = $(':reset');
    // Janelas modais
    var $janela_edicao = $('.usuario.modal.edicao');
    var $janela_remocao = $('.usuario.modal.remocao');
    // Formulários
    var $formulario_edicao = $('form[id="usuario.formulario.edicao"]');
    var $formulario_remocao = $('form[id="usuario.formulario.remocao"]');
    // Campos de formulário
    var $campo_tipo = $('[id="usuario.tipo"]');
    var $campo_perfil = $('[id="usuario.perfil"]');
    var $campo_situacao = $('[id="usuario.situacao"]');
    var $campos_senha = $(':password');
    var $campo_senha = $('[id="usuario.senha"]');
    var $campo_confirmacao_senha = $('[id="senha.confirmacao"]');
    // Blocos de conteúdo (divs)
    var $div_alterar_senha = $('div.alterar.senha');
    var $div_campos_senha = $('div.campos.senha');
    /************************************
     * Definição dos blocos de conteúdo *
     ************************************/
    var campo_alterar_senha = '';
    campo_alterar_senha += '<b:formgroup>\n';
    campo_alterar_senha += '    <div class="row">\n';
    campo_alterar_senha += '        <div class="col-sm-12">\n';
    campo_alterar_senha += '            <div class="checkbox">\n';
    campo_alterar_senha += '                <label>\n';
    campo_alterar_senha += '                    <input\n';
    campo_alterar_senha += '                        id="alterar.senha"\n';
    campo_alterar_senha += '                        type="checkbox"\n';
    campo_alterar_senha += '                        style="position: absolute; margin-top: 0"/>\n';
    campo_alterar_senha += '                        &nbsp;Desejo alterar minha senha.\n';
    campo_alterar_senha += '                </label>\n';
    campo_alterar_senha += '            </div>\n';
    campo_alterar_senha += '        </div>\n';
    campo_alterar_senha += '    </div>\n';
    campo_alterar_senha += '</b:formgroup>\n';
    // Formulário - auto-completar
    $('input', $formulario_edicao).attr('autocomplete', 'off');
    // Formulário - validação
    $formulario_edicao.validate({
        debug: false,
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            'objeto.nome': {
                required: true,
                maxlength: 255
            },
            'objeto.login': {
                required: true,
                maxlength: 30
            },
            'objeto.email': {
                required: true,
                email: true
            }
        }
    });
    // Formulário - reconfiguração
    $formulario_edicao.reconfigurar = function() {
        $(this).validate().resetForm();
        $(this).find('.has-error').removeClass("has-error");
        $(this).find('.has-success').removeClass("has-success");
        $(this).find('.form-control-feedback').remove();
    };
    // Botão adicionar.
    $botao_adicionar.click(function() {
        usuario = {
            id: 0,
            nome: '',
            login: '',
            senha: '',
            email: '',
            tipo: 'Interno',
            perfil: 'Admin',
            situacao: 'Ativo'
        };
        $div_alterar_senha.html('');
        $div_campos_senha.show();
        $campos_senha.pwstrength('forceUpdate');
        $campo_senha.rules('add', {
            required: true,
            equalTo: '[name="senha.confirmacao"]',
            minlength: 8
        });
        $campo_confirmacao_senha.rules('add', {
            required: true,
            equalTo: '[name="objeto.senha"]',
            minlength: 8
        });
        $('[name="acao"]', $formulario_edicao).attr('value', 'adicionar');
        preencher_formulario($formulario_edicao, usuario, 'objeto.');
        $formulario_edicao.reconfigurar();
        $janela_edicao.modal('show');
    });
    // Botão limpar.
    $botoes_limpar.click(function() {
        usuario = {
            nome: '',
            login: '',
            senha: '',
            email: '',
            tipo: 'Interno',
            perfil: 'Admin',
            situacao: 'Ativo'
        };
        $campo_perfil.empty();
        $(perfis_usuario).each(function(i, e) {
            $campo_perfil.append(new Option(e, e));
        });
        $('.selectpicker').selectpicker('refresh');
        $campos_senha.pwstrength('forceUpdate');
        preencher_formulario($formulario_edicao, usuario, 'objeto.');
        $campo_confirmacao_senha.attr('value', '');
        $formulario_edicao.reconfigurar();
    });
    // Botões editar.
    tabela.configurar_botoes_editar = function() {
        var $botoes_editar = $('a.action.editar');
        $botoes_editar.each(function(i, e) {
            var $botao_editar = $(e);
            $botao_editar.click(function() {
                acao = 'editar';
                var $linha = $(this).closest('tr');
                var $colunas = $linha.find('td');
                $colunas.each(function(i, e) {
                    var $coluna = $(e);
                    var atributo = $coluna.attr('name');
                    if (atributo != 'acao') {
                        var valor = $coluna.text().trim();
                        usuario[atributo] = valor;
                    }
                });
                $('[name="acao"]', $formulario_edicao).attr('value', 'editar');
                preencher_formulario($formulario_edicao, usuario, 'objeto.');
                $formulario_edicao.reconfigurar();
                switch(usuario['situacao']) {
                    case 'Ativo':
                        $campo_situacao.selectpicker('val', 'Ativo');
                        break;
                    case 'Inativo':
                        $campo_situacao.selectpicker('val', 'Inativo');
                        break;
                }
                $campo_situacao.selectpicker('refresh');
                $div_alterar_senha.html(campo_alterar_senha);
                $campo_alterar_senha = $('[id="alterar.senha"]');
                $campos_senha.pwstrength('forceUpdate');
                // Esconde campos de senha.
                $div_campos_senha.hide();
                $campo_alterar_senha.click(function() {
                    $div_campos_senha.toggle('slow', function() {
                        if ($(this).is(':visible')) {
                            $campo_senha.rules('add', {
                                required: true,
                                equalTo: '[name="senha.confirmacao"]',
                                minlength: 8
                            });
                            $campo_confirmacao_senha.rules('add', {
                                required: true,
                                equalTo: '[name="objeto.senha"]',
                                minlength: 8
                            });
                        } else {
                            $campo_senha.rules('add', {
                                required: false
                            });
                            $campo_confirmacao_senha.rules('add', {
                                required: false
                            });
                        }
                    });
                });
                $janela_edicao.modal('show');
            });
        });
    };
    // Botões remover.
    tabela.configurar_botoes_remover = function() {
        var $botoes_remover = $('a.action.remover'); 
        $botoes_remover.each(function(i, e) {
            var $botao_remover = $(e);
            $botao_remover.click(function() {
                acao = 'remover';
                var $linha = $(this).closest('tr');
                var $coluna = $linha.find('td:eq(0)');
                usuario = {
                    id: $coluna.text()
                }
                $('[name="acao"]', $formulario_remocao).attr('value', 'remover');
                preencher_formulario($formulario_remocao, usuario, 'objeto.');
                $janela_remocao.modal('show');
            });
        });
    };
    // Campos de senha.
    var options = {
        common: {
            debug: false,
            usernameField: $('input[id="usuario.nomeUsuario"]'),
            minChar: 8
        },
        ui: {
            verdicts: [
               "<span class='fa fa-exclamation-triangle'></span> Fraquíssima",
               "<span class='fa fa-exclamation-triangle'></span> Fraca",
               "Regular",
               "<span class='fa fa-thumbs-up'></span> Forte",
               "<span class='fa fa-thumbs-up'></span> Fortíssima"
           ],
           showProgressBar: true,
           showVerdictsInsideProgressBar: true,
           showPopover: false,
           showErrors: true,
           errorMessages: {
               wordLength: 'A senha informada é muito curta',
               wordNotEmail: 'O e-mail não pode ser usado como senha',
               wordSimilarToUsername: 'A senha não pode ser igual ao nome de usuário',
               wordTwoCharacterClasses: 'A senha informada deve conter classes de caracteres diferentes',
               wordRepetitions: 'A senha informada contém muitas repetições',
               wordSequences: 'A senha informada contém sequências'
           },
           scores: [0, 25, 50, 75, 100]
        }
    };
    options.ui.container = '#senha-container';
    $campo_senha.pwstrength(options);
    options.ui.container = '#confirmacao-senha-container';
    $campo_confirmacao_senha.pwstrength(options);
    var $barras_de_progresso = $('.progress');
    $barras_de_progresso.css('margin-top', 10).css('height', 30).css('font-weight', 'normal');
    var $veredito_campos_senha = $('.password-verdict');
    $veredito_campos_senha.css('line-height', 2).css('vertical-align', 'middle').css('font-size', 14);
    $campos_senha.keypress(function() {
        $(this).pwstrength('forceUpdate');
    });
    $campos_senha.password({
        message: 'Clique aqui para exibir/ocultar a senha'
    });
    $('i.icon-eye-open.glyphicon.glyphicon-eye-open').each(function(i, e) {
        $(e).parent().attr('data-toggle', 'tooltip');
    });
    // Ativa as tooltips
    var $dicas_de_campos = $('[data-toggle="tooltip"]');
    $dicas_de_campos.tooltip({
        'placement' : 'bottom',
        'trigger': 'hover',
        'delay': {
            'show': 100,
            'hide': 1
        }
    });
    // Tipos de usuario.
    $campo_tipo.change(function() {
        var $valor = $(this).val();
        usuario['tipo'] = $valor;
        switch ($valor) {
            case 'Externo':
                $campo_perfil.empty();
                $campo_perfil.append(new Option("Padrão", "3"));
                break;
            case 'Interno':
                $campo_perfil.empty();
                $(perfis_usuario).each(function(i, e) {
                    $campo_perfil.append(new Option(e, i + 1));
                });
                break;
        }
        $campo_perfil.selectpicker('refresh');
    });
    // Perfis de usuário.
    $campo_perfil.change(function() {
        var $valor = $(this).val();
        usuario['perfil'] = $valor;
    });
    // Formulário edição - submissão.
    $formulario_edicao.submit(function() {
        var dados = $(this).serializeObject();
        delete dados['senha'];
        dados['objetos'] = [];
        dados['extra'] = {};
        dados = JSON.stringify(dados);
        $(this).validate();
        if ($(this).valid()) {
            $.ajax({
                type: 'POST',
                url: '/siav/admin/usuario/salvar',
                contentType: 'application/json',
                dataType: 'json',
                data: dados,
                cache: false,
                success: function(data, status, xhr) {
                    tabela.salvar(data);
                    $janela_edicao.modal('hide');
                }
            });
        }
        return false;
    });
    // Formulário remoção - submissão.
    $formulario_remocao.submit(function() {
        var dados = $(this).serializeObject();
        dados['objetos'] = [];
        dados['extra'] = {};
        dados = JSON.stringify(dados);
        $.ajax({
            type: 'POST',
            url: '/siav/admin/usuario/salvar',
            contentType: 'application/json',
            dataType: 'json',
            data: dados,
            cache: false,
            success: function(data, status, xhr) {
                tabela.salvar(data);
                $janela_remocao.modal('hide');
            }
        });
        return false;
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
        url: '/siav/admin/usuario/listar',
        cache: false,
        success: function(data, status, xhr) {
            tabela.recriar(data);
        }
    });
    /***************
     * Webservices *
     ***************/
    $.ajax({
        type: 'GET',
        url: '/siav/webservice/perfis-usuario/json',
        cache: false,
        success: function(data, status, xhr) {
            perfis_usuario = data;
        }
    });
});