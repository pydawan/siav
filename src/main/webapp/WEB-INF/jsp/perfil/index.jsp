<%@ include file="../includes/cabecalho.jsp"%>
<%@ include file="../includes/modal/perfil.jsp"%>
<div class="container">
    <h1>PERFIL USUÁRIO</h1>
    <hr />
    <div class="row">
        <div class="col-sm-12">
            <a
                id="perfil.adicionar"
                class="btn btn-default action adicionar"><i
                class="glyphicon glyphicon-file"></i> Adicionar</a>
        </div>
    </div>
    <br />
    <b:panel>
        <b:panelbody></b:panelbody>
    </b:panel>
    <script type="text/javascript">
        // Variáveis globais. 
        var perfil = {};
        var perfis = [];
    </script>
    <script
        type="text/javascript"
        src="/siav/static/js/entidades/Perfil.js"></script>
    <%@ include file="../includes/rodape.jsp"%>