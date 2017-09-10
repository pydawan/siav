<%@ include file="../includes/cabecalho.jsp"%>
<!-- page-content -->
<div class="container">
    <c:if test="${not empty tipo_alerta}">
    <div
        class="alert ${tipo_alerta} alert-dismissible"
        role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Fechar">
            <span aria-hidden="true">&times;</span>
        </button>
        <strong>${mensagem_alerta}</strong>
    </div>
    </c:if>
    <h1 style="font-size: 4em; margin-bottom: 25px;">Contato</h1>
    <form
        id="form-contato"
        action="${linkTo[ContatoController].enviar}"
        method="POST">
        <div class="form-group">
            <label for="contato.assunto">* Assunto</label> <input
                id="contato.assunto"
                name="contato.assunto"
                type="text"
                class="form-control"
                placeholder="Assunto"
                value="${contato.assunto}" />
            <span class="error">${errors.from('contato.assunto')}</span>
        </div>
        <div class="form-group">
            <label for="contato.nome">* Nome</label> <input
                id="contato.nome"
                name="contato.nome"
                type="text"
                class="form-control"
                placeholder="Nome"
                value="${contato.nome}" />
        </div>
        <div class="form-group">
            <label for="contato.email">* E-mail</label> <input
                id="contato.email"
                name="contato.email"
                type="text"
                class="form-control"
                placeholder="E-mail"
                value="${contato.email}" />
        </div>
        <div class="form-group">
            <label for="contato.mensagem">* Mensagem</label>
            <textarea
                id="contato.mensagem"
                name="contato.mensagem"
                rows="5"
                cols="20"
                class="form-control"
                placeholder="Mensagem"
                value="${contato.mensagem}"></textarea>
        </div>
        <div class="form-group">${captcha}</div>
        <div class="form-group">
            <input
                id="enviar"
                name="enviar"
                type="submit"
                class="btn btn-primary btn-lg"
                value="Enviar" />
        </div>
    </form>
    <hr />
    <script type="text/javascript">
        $(document).ready(function() {
            $("#form-contato").validate({
                debug: false,
                onfocusout: function(element) {
                    $(element).valid();
                },
                rules: {
                    'contato.assunto': {
                        required: true
                    },
                    'contato.nome': {
                        required: true
                    },
                    'contato.email': {
                        required: true,
                        email: true
                    },
                    'contato.mensagem': {
                        required: true
                    }
                },
                messages: {
                    'contato.assunto': {
                        required: 'Assunto é um campo de preenchimento obrigatório.'
                    },
                    'contato.nome': {
                        required: 'Nome é um campo de preenchimento obrigatório.'
                    },
                    'contato.email': {
                        required: 'E-mail é um campo de preenchimento obrigatório.',
                        email: 'Por favor informe um e-mail válido.'
                    },
                    'contato.mensagem': {
                        required: 'Mensagem é um campo de preechimento obrigatório.'
                    }
                }
            });
        });
    </script>
    <%@ include file="../includes/rodape.jsp"%>