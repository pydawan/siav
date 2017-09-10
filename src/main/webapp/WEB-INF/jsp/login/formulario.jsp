
<%@ include file="../includes/cabecalho.jsp"%>
<!-- container -->
<div class="container login">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12">
                            <a
                                href="#"
                                class="active"
                                id="login-form-link"><h4>
                                    <span
                                        style="color: #346C98; font-weight: 900;">SIAV</span>
                                    - SISTEMA DE AUTORIZAÇÃO DE VIAGENS
                                </h4></a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form
                                id="login-form"
                                action="${linkto[LoginController].formulario}"
                                method="post"
                                role="form"
                                style="display: block;">
                                <div class="form-group">
                                    <input
                                        type="text"
                                        name="login"
                                        id="login"
                                        tabindex="1"
                                        class="form-control"
                                        placeholder="Login"
                                        value="">
                                </div>
                                <div class="form-group">
                                    <input
                                        type="password"
                                        name="senha"
                                        id="senha"
                                        tabindex="2"
                                        class="form-control"
                                        placeholder="Senha">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div
                                            class="col-sm-6 col-sm-offset-3">
                                            <input
                                                type="submit"
                                                name="login-submit"
                                                id="login-submit"
                                                tabindex="4"
                                                class="form-control btn btn-login"
                                                value="Entrar">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../includes/rodape.jsp"%>