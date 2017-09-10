<div
    id="usuario.modal.edicao"
    class="usuario modal edicao fade"
    tabindex="-1"
    role="dialog"
    aria-labelledby="usuario.modal.edicao"
    aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-hidden="true">&times;</button>
                <h3 class="modal-title">USUÁRIO</h3>
            </div>
            <div class="modal-body">
                <form
                    id="usuario.formulario.edicao"
                    class="usuario formulario edicao">
                    <b:formcontrol
                        type="hidden"
                        name="acao"
                        value="" />
                    <b:formcontrol
                        type="hidden"
                        id="usuario.id"
                        name="objeto.id"
                        value=""/>
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="usuario.nome">Nome:</label>
                                <b:formcontrol
                                    type="text"
                                    id="usuario.nome"
                                    name="objeto.nome"
                                    value="" />
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="usuario.login">Login:</label>
                                <b:formcontrol
                                    type="text"
                                    id="usuario.login"
                                    name="objeto.login"
                                    value="" />
                            </div>
                        </div>
                    </b:formgroup>
                    <div class="alterar senha"></div>
                    <div class="campos senha">
                    <b:formgroup id="senha-container">
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="usuario.senha">Senha:</label>
                                <b:formcontrol
                                    type="password"
                                    id="usuario.senha"
                                    name="objeto.senha"/>
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup id="confirmacao-senha-container">
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="senha.confirmacao">Senha
                                    (confirmaçao):</label>
                                <b:formcontrol
                                    type="password"
                                    id="senha.confirmacao"
                                    name="senha.confirmacao"
                                    value="" />
                            </div>
                        </div>
                    </b:formgroup>
                    </div>
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="usuario.email">E-mail:</label>
                                <b:formcontrol
                                    type="email"
                                    id="usuario.email"
                                    name="objeto.email"
                                    value="" />
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="usuario.tipo">Tipo:</label>
                                <b:formcontrol
                                    type="select"
                                    id="usuario.tipo"
                                    name="objeto.tipo"
                                    class="selectpicker show-tick"
                                    data-header="- Selecione um tipo de usuário -">
                                    <c:forEach
                                        var="tipo"
                                        items="${tiposUsuario}">
                                        <option value="${tipo}">${tipo}</option>
                                    </c:forEach>
                                </b:formcontrol>
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="usuario.perfil">Perfil:</label>
                                <b:formcontrol
                                    type="select"
                                    id="usuario.perfil"
                                    name="objeto.perfil.id"
                                    class="selectpicker show-tick"
                                    data-header="- Selecione um perfil de usuário - ">
                                    <c:forEach
                                        var="perfil"
                                        items="${perfisUsuario}">
                                        <option value="${perfil.id}">${perfil.nome}</option>
                                    </c:forEach>
                                </b:formcontrol>
                            </div>
                        </div>
                    </b:formgroup>
                    <b:formgroup>
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="usuario.situacao">Situação:</label>
                                <b:formcontrol
                                    type="select"
                                    id="usuario.situacao"
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
                    <div class="modal-footer">
                        <button
                            type="submit"
                            id="usuario.salvar"
                            name="usuario.salvar"
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
    id="usuario.modal.remocao"
    class="usuario modal remocao fade"
    tabindex="-1"
    role="dialog"
    aria-labelledby="usuario.modal.remocao"
    aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-hidden="true">&times;</button>
                <h4 class="modal-title">USUÁRIO</h4>
            </div>
            <form
                id="usuario.formulario.remocao"
                class="usuario formulario remocao">
                <b:formcontrol
                    type="hidden"
                    name="acao"
                    value="" />
                <input
                    type="hidden"
                    name="objeto.id" />
                <div class="modal-body">
                    <h5>Confirma a remoção do usuário?</h5>
                </div>
                <div class="modal-footer">
                    <button
                        type="submit"
                        id="usuario.remover"
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
