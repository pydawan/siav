<%@ include file="../includes/cabecalho.jsp"%>
<%@ include file="../includes/modal/permissao.jsp"%>
<div class="container">
    <h1>PERMISS�O USU�RIO</h1>
    <hr />
    <div class="row">
        <div class="col-sm-12">
            <a
                id="permissao.adicionar"
                class="btn btn-default action adicionar"><i
                class="glyphicon glyphicon-file"></i> Adicionar</a>
        </div>
    </div>
    <br />
    <b:panel>
        <b:panelbody></b:panelbody>
    </b:panel>
    <script type="text/javascript">
        // Vari�veis globais. 
        var permissao = {};
        var permissoes = [];
    </script>
    <script
        type="text/javascript"
        src="/siav/static/js/entidades/Permissao.js"></script>
    <%@ include file="../includes/rodape.jsp"%>