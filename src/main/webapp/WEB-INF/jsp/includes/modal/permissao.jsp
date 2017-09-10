<div
    id="permissao.modal.edicao"
    class="permissao modal edicao fade"
    tabindex="-1"
    role="dialog"
    aria-labelledby="permissao.modal.edicao"
    aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-hidden="true">&times;</button>
                <h3 class="modal-title">PERMISSÃO USUÁRIO</h3>
            </div>
            <div class="modal-body">
                <form
                    id="permissao.formulario.edicao"
                    class="permissao formulario edicao">
                    <b:formcontrol
                        type="hidden"
                        name="acao"
                        value="" />
                    <b:formcontrol
                        type="hidden"
                        id="permissao.id"
                        name="objeto.id"
                        value="" />
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="permissao.nome">Nome:</label>
                                <b:formcontrol
                                    type="text"
                                    id="permissao.nome"
                                    name="objeto.nome"
                                    value="" />
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="permissao.descricao">Descrição:</label>
                                <b:formcontrol
                                    type="textarea"
                                    id="permissao.descricao"
                                    name="objeto.descricao"
                                    rows="5" />
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="permissao.situacao">Situação:</label>
                                <b:formcontrol
                                    type="select"
                                    id="permissao.situacao"
                                    name="objeto.situacao"
                                    class="selectpicker show-tick"
                                    data-header="- Selecione uma situação cadastral - ">
                                    <c:forEach
                                        var="situacao"
                                        items="${situacoesCadastro}">
                                        <option value="${situacao}">${situacao}</option>
                                    </c:forEach>
                                </b:formcontrol>
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <br />
                                <br />
                                <br />
                                <br />
                            </div>
                        </div>
                    </b:formgroup>
                    <div class="modal-footer">
                        <button
                            type="submit"
                            id="permissao.salvar"
                            name="permissao.salvar"
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
    id="permissao.modal.remocao"
    class="permissao modal remocao fade"
    tabindex="-1"
    role="dialog"
    aria-labelledby="permissao.modal.remocao"
    aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-hidden="true">&times;</button>
                <h4 class="modal-title">PERFIL USUÁRIO</h4>
            </div>
            <form
                id="permissao.formulario.remocao"
                class="permissao formulario remocao">
                <b:formcontrol
                    type="hidden"
                    name="acao"
                    value="" />
                <b:formcontrol
                    type="hidden"
                    id="permissao.id"
                    name="objeto.id" />
                <div class="modal-body">
                    <h5>Confirma a remoção da permissão de usuário?</h5>
                </div>
                <div class="modal-footer">
                    <button
                        type="submit"
                        id="permissao.remover"
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
