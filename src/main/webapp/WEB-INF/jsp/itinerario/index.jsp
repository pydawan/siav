<%@ include file="../includes/cabecalho.jsp"%>
<%@ include file="../includes/modal/itinerario.jsp"%>
<div class="container">
    <h1>ITINERÁRIO</h1>
    <hr />
    <div class="row">
        <div class="col-sm-12">
            <a
                id="itinerario.adicionar"
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
        var itinerario = {};
        var itinerarios = [];
        var municipios = ${municipiosJSON};
        var rodovias = ${rodoviasJSON};
    </script>
    <script
        type="text/javascript"
        src="/siav/static/js/entidades/Itinerario.js"></script>
    <%@ include file="../includes/rodape.jsp"%>