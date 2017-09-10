<div
    id="empresa"
    role="tabpanel"
    class="tab-pane active">
    <b:panel>
        <b:panelbody>
            <b:formgroup>
                <label>Registro (AGR):</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.empresa.registroAgr"
                                name="autorizacao.empresa.registroAgr"
                                placeholder="Número de registro na AGR"
                                value="${autorizacao.empresa.registroAgr}"
                                ng-model="autorizacao.empresa.registroAgr" />
                            <span class="input-group-addon"> <span
                                class="ionicons ion-pound"
                                data-toggle="tooltip"
                                title="Nº de registro na AGR" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>CNPJ:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.empresa.cnpj"
                                name="autorizacao.empresa.cnpj"
                                placeholder="__.___.___/____-__"
                                class="cnpj"
                                value="${usuarioSessao.empresa.cnpj}"
                                ng-model="autorizacao.empresa.cnpj" />
                            <span class="input-group-addon"> <span
                                class="ionicons ion-pound"
                                data-toggle="tooltip"
                                title="Número do CNPJ" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Razão social:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="razaosocial"
                                name="autorizacao.empresa.razaoSocial"
                                placeholder="Razão social"
                                value="${usuarioSessao.empresa.razaoSocial}"
                                ng-model="autorizacao.empresa.razaoSocial" />
                            <span class="input-group-addon"> <span
                                class="glyphicon glyphicon-edit"
                                data-toggle="tooltip"
                                title="Razão social" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Nome fantasia:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.empresa.nomeFantasia"
                                name="autorizacao.empresa.nomeFantasia"
                                placeholder="Nome fantasia"
                                value="${usuarioSessao.empresa.nomeFantasia}"
                                ng-model="autorizacao.empresa.nomeFantasia" />
                            <span class="input-group-addon"> <span
                                class="glyphicon glyphicon-edit"
                                data-toggle="tooltip"
                                title="Nome fantasia" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Endereço:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.empresa.endereco"
                                name="autorizacao.empresa.endereco"
                                placeholder="Endereço"
                                value="${usuarioSessao.empresa.endereco}"
                                ng-model="autorizacao.empresa.endereco" />
                            <span class="input-group-addon"> <span
                                class="glyphicon glyphicon-home"
                                data-toggle="tooltip"
                                title="Endereço" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Telefone:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.empresa.telefone"
                                name="autorizacao.empresa.telefone"
                                placeholder="(__) ____-____"
                                class="fone"
                                value="${usuarioSessao.empresa.telefone}"
                                ng-model="autorizacao.empresa.telefone" />
                            <span class="input-group-addon"> <span
                                class="glyphicon glyphicon-earphone"
                                data-toggle="tooltip"
                                title="Telefone" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>E-mail:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.empresa.email"
                                name="autorizacao.empresa.email"
                                placeholder="usuario@provedor.com"
                                value="${usuarioSessao.empresa.email}"
                                ng-model="autorizacao.empresa.email" />
                            <span class="input-group-addon"> <span
                                class="ionicons ion-at"
                                data-toggle="tooltip"
                                title="E-mail" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
            <b:formgroup>
                <label>Contato:</label>
                <div class="row">
                    <div class="col-sm-11">
                        <div class="input-group">
                            <b:formcontrol
                                type="text"
                                id="autorizacao.empresa.contato"
                                name="autorizacao.empresa.contato"
                                placeholder="Contato"
                                value="${autorizacao.empresa.contato}"
                                ng-model="autorizacao.empresa.contato" />
                            <span class="input-group-addon"> <span
                                class="glyphicon glyphicon-user"
                                data-toggle="tooltip"
                                title="Nome do Contato" />
                            </span>
                        </div>
                    </div>
                </div>
            </b:formgroup>
        </b:panelbody>
    </b:panel>
</div>