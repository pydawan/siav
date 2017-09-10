<%@include file="../includes/cabecalho.jsp"%>
<div class="container" ng-app="AutorizacaoApp">
    <div
        id="aviso"
        class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button
                        type="button"
                        class="close"
                        data-dismiss="modal"
                        aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">ATENÇÃO</h4>
                </div>
                <div class="modal-body">
                    <div
                        id="alerta"
                        role="alert"
                        class="alert alert-warning alert-dismissible fade in">
                        <p style="font-size: 11pt; font-weight: bold;">
                            VERIFIQUE ATENTAMENTE OS DADOS INFORMADOS
                            ANTES DE ENVIÁ-LOS POIS OS MESMOS NÃO
                            PODERÃO SER ATUALIZADOS!</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button
                        type="button"
                        class="btn btn-success"
                        data-dismiss="modal">PROSSEGUIR</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
    <h1>Autorização de Viagem</h1>
    <hr />
    <div class="row">
        <div class="col-sm-12">
            <a
                href="${linkTo[AutorizacaoController].acompanhar}"
                class="btn btn-primary btn-lg pull-right">Acompanhar
                solicitações</a>
        </div>
    </div>
    <br />
    <b:panel>
        <b:panelheading>
            <h3>Solicitação de Autorização de Viagem</h3>
        </b:panelheading>
        <b:panelbody>
            <form
                id="form-autorizacao"
                action="${linkTo[AutorizacaoController].salvar}"
                method="POST">
                <b:panel>
                    <b:panelbody>
                        <b:formgroup>
                            <label>Número:</label>
                            <div class="row">
                                <div class="col-sm-11">
                                    <div class="input-group">
                                        <b:formcontrol
                                            type="text"
                                            id="autorizacao.id"
                                            name="autorizacao.id"
                                            value="${autorizacao.id != 0 ? autorizacao.id : ''}"
                                            class="readonly"
                                            readonly="readonly" />
                                        <span class="input-group-addon">
                                            <span
                                            class="ionicons ion-pound"
                                            data-toggle="tooltip"
                                            title="Número de autorização da viagem" />
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </b:formgroup>
                    </b:panelbody>
                </b:panel>
                <!-- Abas -->
                <ul
                    class="nav nav-tabs"
                    role="tablist">
                    <li
                        id="aba-empresa"
                        role="presentation"
                        class="active"><a
                        href="#empresa"
                        aria-controls="empresa"
                        role="tab"
                        data-toggle="tab">Empresa</a></li>
                    <li role="presentation"><a
                        href="#veiculo"
                        aria-controls="veiculo"
                        role="tab"
                        data-toggle="tab">Veículo</a></li>
                    <li role="presentation"><a
                        href="#viagem"
                        aria-controls="viagem"
                        role="tab"
                        data-toggle="tab">Viagem</a></li>
                </ul>
                <!-- Painéis das abas -->
                <div class="tab-content">
                    <%@include file="./aba/empresa.jsp"%>
                    <%@include file="./aba/veiculo.jsp"%>
                    <%@include file="./aba/viagem.jsp"%>
                </div>
                <b:formgroup>
                    <b:formcontrol
                        id="btn-salvar"
                        name="btn-salvar"
                        type="submit"
                        value="${acao}"
                        class="btn btn-primary"
                        style="width: 10%;" />
                    <b:formcontrol
                        id="btn-desfazer"
                        name="btn-desfazer"
                        type="reset"
                        value="${ acao eq 'Solicitar' ? 'Limpar' : 'Desfazer' }"
                        class="btn btn-default"
                        style="width: 10%;" />
                </b:formgroup>
            </form>
        </b:panelbody>
    </b:panel>
    <script type="text/javascript">
        var app = angular.module('AutorizacaoApp', []);
        app.controller('ViagemController', function($scope) {
            $scope.viagem = {
                'extensao': 0,
                'valor': 0,
                'roteiro': []
            };
            $scope.adicionarRoteiros = function() {
                var roteiros = $('#trecho option:selected').selectpicker('selectAll');
                $(roteiros).each(function(i, e) {
                    var roteiro = {
                        'id': $scope.viagem.roteiro.length,
                        'trecho': $(e).val(),
                        'extensao': parseFloat($(e).attr('data-extensao')),
                        'valor': parseFloat($(e).attr('data-valor'))
                    };
                    $scope.viagem.roteiro.push(roteiro);
                    $scope.viagem.extensao += roteiro.extensao;
                    $scope.viagem.valor += roteiro.valor;
                });
                $('#trecho').empty();
                $('#trecho').selectpicker('refresh');
                $('#trecho').selectpicker('render');
                console.log($scope.viagem);
            };
            $scope.removerRoteiro = function(index) {
                var roteiro = $scope.viagem.roteiro.splice(index, 1)[0];
                $scope.viagem.extensao -= roteiro.extensao;
                $scope.viagem.valor -= roteiro.valor;
            };
        }); 
    </script>
    <script type="text/javascript">
        // Variáveis globais.
        var roteiros_viagem = [];
        var extensao_total = 0.0;
        var valor_total = 0.0;
        $('#municipio').attr('autocomplete', 'off');
        $('#municipio').typeahead({
            source: ${municipios}
        });
    </script>
    <script type="text/javascript" src="/siav/static/js/entidades/AutorizacaoViagem.js"></script>
    <%@include file="../includes/rodape.jsp"%>