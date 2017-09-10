<div
    id="itinerario.modal.edicao"
    class="itinerario modal edicao fade"
    tabindex="-1"
    role="dialog"
    aria-labelledby="itinerario.modal.edicao"
    aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-hidden="true">&times;</button>
                <h3 class="modal-title">ITINERÁRIO</h3>
            </div>
            <div class="modal-body">
                <form
                    id="itinerario.formulario.edicao"
                    class="itinerario formulario edicao">
                    <b:formcontrol
                        type="hidden"
                        name="acao"
                        value="" />
                    <b:formcontrol
                        type="hidden"
                        id="itinerario.id"
                        name="objeto.id"
                        value="" />
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="itinerario.origem">Origem:</label>
                                <b:formcontrol
                                    type="select"
                                    id="itinerario.origem"
                                    name="objeto.origem"
                                    class="selectpicker show-tick"
                                    data-live-search="true"
                                    data-header="- Selecione a origem do itinerário -"
                                    data-none-results-text="Não foi encontrado nenhum resultado correspondente a ">
                                <c:forEach var="municipio" items="${municipios}">
                                    <option value="${municipio}">${municipio}</option>
                                </c:forEach>
                                </b:formcontrol>
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="itinerario.destino">Destino:</label>
                                <b:formcontrol
                                    type="select"
                                    id="itinerario.destino"
                                    name="objeto.destino"
                                    class="selectpicker show-tick"
                                    data-live-search="true"
                                    data-header="- Selecione o destino do itinerário -"
                                    data-none-results-text="Não foi encontrado nenhum resultado correspondente a ">
                                    <c:forEach var="municipio" items="${municipios}">
                                        <option value="${municipio}">${municipio}</option>
                                    </c:forEach>
                                </b:formcontrol>
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="itinerario.extensao">Extensão:</label>
                                <b:formcontrol
                                    type="text"
                                    id="itinerario.extensao"
                                    name="objeto.extensao"
                                    class="extensao"
                                    placeholder=""
                                    maxlength="11"
                                    value="" />
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="itinerario.rodovia">Rodovia:</label>
                                <b:formcontrol
                                    type="select"
                                    id="itinerario.rodovia"
                                    name="objeto.rodovia"
                                    class="selectpicker show-tick"
                                    data-live-search="true"
                                    data-header="- Selecione a rodovia do itinerário -"
                                    data-none-results-text="Não foi encontrado nenhum resultado correspondente a ">
                                <c:forEach var="rodovia" items="${rodovias}">
                                    <option value="${rodovia}">${rodovia}</option>
                                </c:forEach>
                                </b:formcontrol>
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup>
                        <label>Ônibus (Preço):</label>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <span class="input-group-addon">Ida
                                        (<b>I</b>):</span>
                                    <b:formcontrol
                                        id="itinerario.precoIdaOnibus"
                                        name="objeto.precoIdaOnibus"
                                        class="monetario"
                                        placeholder=""
                                        value="" />
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <span class="input-group-addon">Ida/Volta
                                        (<b>I/V</b>):</span>
                                    <b:formcontrol
                                        id="itinerario.precoIdaVoltaOnibus"
                                        name="objeto.precoIdaVoltaOnibus"
                                        class="monetario"
                                        placeholder=""
                                        value="" />
                                </div>
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup>
                        <label>Micro-ônibus (Preço):</label>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <div class="input-group-addon">Ida
                                        (<b>I</b>):</div>
                                    <b:formcontrol
                                        id="itinerario.precoIdaMicroOnibus"
                                        name="objeto.precoIdaMicroOnibus"
                                        class="monetario"
                                        placeholder=""
                                        value="" />
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <div class="input-group-addon">Ida/Volta
                                        (<b>I/V</b>):</div>
                                    <b:formcontrol
                                        id="itinerario.precoIdaVoltaMicroOnibus"
                                        name="objeto.precoIdaVoltaMicroOnibus"
                                        class="monetario"
                                        placeholder=""
                                        value="" />
                                </div>
                            </div>
                        </div>
                    </b:formgroup>
                    <div class="modal-footer">
                        <button
                            type="submit"
                            id="itinerario.salvar"
                            class="btn btn-primary action">
                            Salvar <span
                                class="glyphicon glyphicon-floppy-save"></span>
                        </button>
                        <button
                            type="reset"
                            class="btn btn-default">
                            Limpar <span
                                class="glyphicon glyphicon-erase"></span>
                        </button>
                        <button
                            type="button"
                            class="btn btn-default"
                            data-dismiss="modal">
                            Cancelar <span
                                class="glyphicon glyphicon-floppy-remove"></span>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div
    id="itinerario.modal.remocao"
    class="itinerario modal remocao fade"
    tabindex="-1"
    role="dialog"
    aria-labelledby="itinerario.modal.remocao"
    aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-hidden="true">&times;</button>
                <h4 class="modal-title">ITINERÁRIO</h4>
            </div>
            <form
                id="itinerario.formulario.remocao"
                class="itinerario formulario remocao">
                <b:formcontrol
                    type="hidden"
                    name="acao"
                    value="" />
                <input
                    type="hidden"
                    name="objeto.id" />
                <div class="modal-body">
                    <h5>Confirma a remoção do itinerário?</h5>
                </div>
                <div class="modal-footer">
                    <button
                        type="submit"
                        id="itinerario.remover"
                        class="btn btn-danger">
                        Sim <span
                            class="glyphicon glyphicon-floppy-disk"></span>
                    </button>
                    <button
                        type="button"
                        class="btn btn-default"
                        data-dismiss="modal">
                        Não <span
                            class="glyphicon glyphicon-floppy-remove"></span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
