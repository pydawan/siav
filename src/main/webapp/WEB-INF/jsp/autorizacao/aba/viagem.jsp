<div
    id="viagem"
    role="tabpanel"
    class="tab-pane">
    <b:panel>
        <b:panelbody>
            <b:formgroup>
                <label>Tipo de viagem:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <select-tipo-viagem />
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Lotação:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                id="autorizacao.viagem.quantidadePassageiros"
                                name="autorizacao.viagem.quantidadePassageiros"
                                type="text"
                                placeholder="Nº de passageiros transportados."
                                class="lotacao"
                                value="${autorizacao.viagem.quantidadePassageiros}"
                                ng-model="autorizacao.viagem.quantidadePassageiros" />
                            <span class="input-group-addon"> <span
                                class="glyphicon glyphicon-user"
                                data-toggle="tooltip"
                                title="Número de passageiros a serem transportados." />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Saída (data / hora):</label>
                <div class="row">
                    <div class="col-sm-6">
                        <date-picker
                            id="dataSaida"
                            ng-model="viagem.dataSaida"
                            title="Data da saída" />
                    </div>
                    <div class="col-sm-5">
                        <time-picker
                            ng-model="viagem.horaSaida"
                            title="Hora da saída" />
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Retorno (data / hora):</label>
                <div class="row">
                    <div class="col-sm-6">
                        <date-picker
                            id="dataRetorno"
                            ng-model="viagem.dataRetorno"
                            title="Data do retorno" />
                    </div>
                    <div class="col-sm-5">
                        <time-picker
                            id="horaRetorno"
                            ng-model="viagem.horaRetorno"
                            title="Hora do retorno" />
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Teste:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <select-picker id="teste" />
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Município:</label>
                <div class="row">
                    <div class="col-sm-9">
                        <b:formcontrol
                            type="text"
                            id="municipio"
                            name="municipio"
                            placeholder="Munícipio com o qual serão filtrados os trechos rodoviários." />
                    </div>
                    <div class="col-sm-2">
                        <b:formcontrol
                            id="botao-pesquisar"
                            type="button"
                            class="btn btn-default btn-primary"
                            value="Pesquisar" />
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Trecho:</label>
                <div class="row">
                    <div class="col-sm-9">
                        <b:formcontrol
                            id="trecho"
                            name="trecho[]"
                            type="select"
                            multiple="multiple"
                            class="selectpicker show-tick"
                            data-actions-box="true"
                            data-select-all-text="Todos"
                            data-deselect-all-text="Nenhum"
                            data-live-search="true"
                            data-none-selected-text="Nenhum trecho selecionado"
                            data-header="- Selecione o trecho -"
                            data-none-results-text="Não foi encontrado nenhum resultado correspondente a "
                            data-count-selected-text="{0} trecho(s) selecionado(s)."
                            data-selected-text-format="count"></b:formcontrol>
                    </div>
                    <div class="col-sm-2">
                        <b:formcontrol
                            id="botao-adicionar"
                            type="button"
                            class="btn btn-default btn-primary"
                            value="Adicionar"
                            ng-click="adicionarRoteiros()" />
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label></label>
                <div class="row">
                    <div
                        id="roteiros"
                        class="col-sm-11">
                        <table
                            class="table table-hover table-condensed"
                            id="roteiros">
                            <thead>
                                <th width="6%">#</th>
                                <th>Trecho</th>
                                <th>Extensão</th>
                                <th>Valor</th>
                                <th width="12%">Ação</th>
                            </thead>
                            <tbody>
                                <tr
                                    ng-repeat="roteiro in viagem.roteiro track by $index">
                                    <td>{{ $index + 1 }}</td>
                                    <td>{{ roteiro.trecho }}</td>
                                    <td>{{ roteiro.extensao |
                                        currency : '' }} km</td>
                                    <td>{{ roteiro.valor | currency
                                        : 'R$ '}}</td>
                                    <td
                                        name="acao"
                                        align="center"><a
                                        class="action remover destaque"
                                        ng-click="removerRoteiro($index)">
                                            <i
                                            class="glyphicon glyphicon-trash"></i>
                                            Remover
                                    </a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-11">
                        <br />
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-11">
                        <h4>
                            Valor total: <strong>{{
                                viagem.valor | currency : 'R$ ' }}</strong>
                        </h4>
                        <h4>
                            Extensão total: <strong>{{
                                viagem.extensao | currency : '' }} km</strong>
                        </h4>
                    </div>
                </div>
            </b:formgroup>
        </b:panelbody>
    </b:panel>
</div>