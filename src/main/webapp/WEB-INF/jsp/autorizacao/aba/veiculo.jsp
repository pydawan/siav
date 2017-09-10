<div
    id="veiculo"
    role="tabpanel"
    class="tab-pane">
    <b:panel>
        <b:panelbody>
            <b:formgroup>
                <label>Placa:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.veiculo.placa"
                                name="autorizacao.veiculo.placa"
                                class="placa-veiculo"
                                placeholder="ABC-1234"
                                value="${autorizacao.veiculo.placa}"
                                ng-model="autorizacao.veiculo.placa" />
                            <span class="input-group-addon"> <span
                                class="glyphicon glyphicon-credit-card"
                                data-toggle="tooltip"
                                title="Placa do Ve�culo" />
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>N�mero:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.veiculo.numero"
                                name="autorizacao.veiculo.numero"
                                class="numero-veiculo"
                                placeholder="9999-99"
                                value="${autorizacao.veiculo.numero}"
                                ng-model="autorizacao.veiculo.numero" />
                            <span class="input-group-addon"> <span
                                class="ionicons ion-pound"
                                data-toggle="tooltip"
                                title="N�mero do Ve�culo" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Modelo:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <select-modelo-veiculo></select-modelo-veiculo>
                            <span class="input-group-addon"> <span
                                class="ionicons ion-model-s"
                                data-toggle="tooltip"
                                title="Modelo do Ve�culo" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Tipo:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <select-tipo-veiculo></select-tipo-veiculo>
                            <span class="input-group-addon"> <span
                                class="ionicons ion-model-s"
                                data-toggle="tooltip"
                                title="Tipo de Ve�culo" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Fabrica��o:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.veiculo.anoFabricacao"
                                name="autorizacao.veiculo.anoFabricacao"
                                class="ano"
                                placeholder="2015"
                                value="${autorizacao.veiculo.anoFabricacao}"
                                ng-model="autorizacao.veiculo.anoFabricacao" />
                            <span class="input-group-addon"> <span
                                class="ionicons ion-pound"
                                data-toggle="tooltip"
                                title="Ano da Fabrica��o" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Capacidade de lota��o:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.veiculo.lotacao"
                                name="autorizacao.veiculo.lotacao"
                                placeholder="50 passageiros"
                                class="lotacao"
                                value="${autorizacao.veiculo.lotacao}"
                                ng-model="autorizacao.veiculo.lotacao" />
                            <span class="input-group-addon"> <span
                                class="glyphicon glyphicon-user"
                                data-toggle="tooltip"
                                title="N� de Passageiros" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>1� motorista:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.veiculo.primeiroMotorista"
                                name="autorizacao.veiculo.primeiroMotorista"
                                placeholder="Nome do primeiro motorista"
                                value="${autorizacao.veiculo.primeiroMotorista}"
                                ng-model="autorizacao.veiculo.primeiroMotorista" />
                            <span class="input-group-addon"> <span
                                class="glyphicon glyphicon-user"
                                data-toggle="tooltip"
                                title="Nome completo do 1� motorista" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>CNH do 1� motorista:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.veiculo.cnhPrimeiroMotorista"
                                name="autorizacao.veiculo.cnhPrimeiroMotorista"
                                class="cnh"
                                placeholder="N�mero da Carteira Nacional de Habilita��o do primeiro motorista"
                                value="${autorizacao.veiculo.cnhPrimeiroMotorista}"
                                ng-model="autorizacao.veiculo.cnhPrimeiroMotorista" />
                            <span class="input-group-addon"> <span
                                class="glyphicon glyphicon-edit"
                                data-toggle="tooltip"
                                title="N�mero da CNH do 1� motorista" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>2� motorista:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.veiculo.segundoMotorista"
                                name="autorizacao.veiculo.segundoMotorista"
                                placeholder="Nome do segundo motorista"
                                value="${autorizacao.veiculo.segundoMotorista}"
                                ng-model="autorizacao.veiculo.segundoMotorista" />
                            <span class="input-group-addon"> <span
                                class="glyphicon glyphicon-user"
                                data-toggle="tooltip"
                                title="Nome completo do 2� motorista" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>CNH do 2� motorista:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.veiculo.cnhSegundoMotorista"
                                name="autorizacao.veiculo.cnhSegundoMotorista"
                                class="cnh"
                                placeholder="N�mero da Carteira Nacional de Habilita��o do segundo motorista"
                                value="${autorizacao.veiculo.cnhSegundoMotorista}"
                                ng-model="autorizacao.veiculo.cnhSegundoMotorista" />
                            <span class="input-group-addon"> <span
                                class="glyphicon glyphicon-edit"
                                data-toggle="tooltip"
                                title="N�mero da CNH do 2� motorista" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
        </b:panelbody>
    </b:panel>
</div>