<%@ include file="../includes/cabecalho.jsp"%>
<body>
    <div class="container">
        <h1>Autorização de Viagem</h1>
        <hr />
        <div class="row">
            <div class="col-sm-12">
                <a
                    href="/siav/autorizacao/solicitar"
                    class="btn btn-lg btn-primary pull-right">Solicitar</a>
            </div>
        </div>
        <br />
        <b:panel>
            <b:panelheading>
                <h3>Solicitações cadastradas</h3>
            </b:panelheading>
            <b:panelbody>
                <div class="table-responsive">
                    <table class="table table-hover table-condensed">
                        <thead>
                            <tr>
                                <th>Solicitação</th>
                                <th>Solicitante</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach
                                items="${autorizacoes}"
                                var="autorizacao">
                                <tr>
                                    <td>${autorizacao.numero}</td>
                                    <td>${autorizacao.empresa.razaoSocial}</td>
                                    <td><a
                                        class="acao"
                                        href="#"
                                        data-toggle="tooltip"
                                        title="Visualiza os dados do registro">
                                            VISUALIZAR </a>&nbsp; <a
                                        class="acao"
                                        href="${linkTo[AutorizacaoController].editar}?id=${autorizacao.id}"
                                        data-toggle="tooltip"
                                        title="Edita os dados do registro">
                                            EDITAR </a>&nbsp; <a
                                        class="acao"
                                        href="#"
                                        data-toggle="tooltip"
                                        title="Remove o registro">REMOVER
                                    </a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfooter> </tfooter>
                    </table>
                </div>
            </b:panelbody>
        </b:panel>
        <%@ include file="../includes/rodape.jsp"%>