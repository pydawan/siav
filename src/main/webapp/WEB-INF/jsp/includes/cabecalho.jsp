<%@ page
    language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib
    prefix="b"
    uri="http://bootstrapjsp.org/"%>
<%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
    prefix="shiro"
    uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- meta -->
<meta charset="UTF-8">
<meta
    name="viewport"
    content="width=device-width, initial-scale=1" />
<meta
    http-equiv="X-UA-Compatible"
    content="IE=edge">
<meta
    name="description"
    content="">
<meta
    name="author"
    content="Thiago Monteiro">
<!-- /meta -->
<!-- css -->
<link
    type="text/css"
    rel="stylesheet"
    href="/siav/webjars/bootstrap/3.3.5/dist/css/bootstrap.min.css" />
<link
    type="text/css"
    rel="stylesheet"
    href="/siav/webjars/bootstrap-form-helpers/2.3.0/dist/css/bootstrap-formhelpers.min.css" />
<link
    type="text/css"
    rel="stylesheet"
    href="/siav/webjars/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" />
<link
    type="text/css"
    rel="stylesheet"
    href="/siav/webjars/font-awesome/4.3.0-3/css/font-awesome.min.css" />
<link
    type="text/css"
    rel="stylesheet"
    href="/siav/webjars/bootstrap-select/1.6.3/dist/css/bootstrap-select.min.css" />
<link
    type="text/css"
    href="/siav/webjars/ionicons/2.0.1/css/ionicons.min.css"
    rel="stylesheet"
    media="screen" />
<link
    type="text/css"
    rel="stylesheet"
    href="/siav/webjars/jquery-ui/1.11.4/jquery-ui.min.css"
    media="screen" />
<link
    type="text/css"
    href="/siav/webjars/jquery-file-upload/9.10.1/css/jquery.fileupload.css"
    rel="stylesheet"
    media="screen" />
<link
    type="text/css"
    href="/siav/static/css/lavish-bootstrap.css"
    rel="stylesheet"
    media="screen" />
<link
    type="text/css"
    href="/siav/static/css/siav.css"
    rel="stylesheet"
    media="screen" />
<link
    type="text/css"
    href="/siav/static/css/jquery.bootstrap-touchspin.min.css"
    rel="stylesheet"
    media="screen" />
<link
    type="text/css"
    href="/siav/static/css/jquery.bdt.min.css"
    rel="stylesheet"
    media="screen" />
<link
    type="text/css"
    rel="stylesheet"
    href="/siav/webjars/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css"
    media="screen" />
<link
    type="text/css"
    rel="stylesheet"
    href="/siav/static/css/magic.min.css"
    media="screen" />
<!-- /css -->
<!-- js -->
<script src="/siav/webjars/jquery/2.1.4/dist/jquery.min.js"></script>
<script src="/siav/webjars/jquery-ui/1.11.4/jquery-ui.min.js"></script>
<script src="/siav/webjars/angularjs/1.4.7/angular.min.js"></script>
<script src="/siav/webjars/angular-i18n/1.4.7/angular-locale_pt-br.js"></script>
<script src="/siav/webjars/jquery-validation/1.14.0/dist/jquery.validate.min.js"></script>
<script src="/siav/webjars/jquery-maskedinput/1.4.1/dist/jquery.maskedinput.min.js"></script>
<script src="/siav/webjars/br-validations/0.2.4/releases/br-validations.min.js"></script>
<script src="/siav/webjars/bootstrap/3.3.5/dist/js/bootstrap.min.js"></script>
<script src="/siav/webjars/bootstrap-form-helpers/2.3.0/dist/js/bootstrap-formhelpers.min.js"></script>
<script src="/siav/webjars/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script src="/siav/webjars/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.pt-BR.min.js"></script>
<script src="/siav/webjars/bootstrap-select/1.6.3/dist/js/bootstrap-select.min.js"></script>
<script src="/siav/webjars/jquery-form/3.51/jquery.form.js"></script>
<script src="/siav/webjars/jquery-file-upload/9.10.1/js/jquery.fileupload.js"></script>
<script src="/siav/webjars/bootstrap3-typeahead/3.1.1/bootstrap3-typeahead.min.js"></script>
<script src="/siav/webjars/tablesorter/2.17.8/js/jquery.tablesorter.min.js"></script>
<script src="/siav/webjars/bootstrap-show-password/1.0.2/bootstrap-show-password.min.js"></script>
<script src="/siav/webjars/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
<script src="/siav/webjars/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect-collapsible-groups.js"></script>
<script src="/siav/static/js/pwstrength-bootstrap-1.2.7.min.js"></script>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script src="/siav/static/js/mask-money.min.js"></script>
<script src="/siav/static/js/jquery.bootstrap-touchspin.min.js"></script>
<script src="/siav/static/js/jquery.sortelements.js"></script>
<script src="/siav/static/js/jquery.bdt.min.js"></script>
<script src="/siav/static/js/angular/apps.js"></script>
<script src="/siav/static/js/angular/services.js"></script>
<script src="/siav/static/js/angular/directives.js"></script>
<script src="/siav/static/js/angular/models.js"></script>
<script src="/siav/static/js/angular/controllers.js"></script>
<script src="/siav/static/js/siav.js"></script>
<!-- /js -->
<!-- favicon -->
<link
    rel="icon shortcut"
    type="image/ico"
    href="/siav/static/imagens/favicon.ico" />
<!-- /favicon -->
<title>SIAV - Sistema de Autorização de Viagens</title>
</head>
<body>
    <!-- navbar -->
    <nav
        class="navbar navbar-inverse navbar-fixed-top"
        role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button
                    type="button"
                    class="navbar-toggle"
                    data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> <span
                        class="icon-bar"></span> <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a
                    class="navbar-brand sigla-sistema"
                    href="/siav/"
                    data-toggle="tooltip"
                    title="Sistema de Autorização de Viagens"><font style="color: white; font-weight: bold">SIAV</font> </a>
            </div>
            <div
                class="collapse navbar-collapse"
                id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a
                        href="/siav/sobre"
                        data-toggle="tooltip"
                        title="Sobre o sistema">Sobre </a></li>
                    <li><a
                        href="/siav/servicos"
                        data-toggle="tooltip"
                        title="Serviços prestados">Serviços</a></li>
                    <li><a
                        href="/siav/contato"
                        data-toggle="tooltip"
                        title="Formulário de contato">Contato</a></li>
                    <li><a
                        href="http://www.goias.gov.br/"
                        target="_blank"
                        data-toggle="tooltip"
                        title="Governo do Estado de Goiás"><img
                            src="/siav/static/imagens/bandeira_goias.png"
                            with="20"
                            height="20" /></a></li>
                </ul>
                <c:if test="${usuarioSessao.autenticado}">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown"><a
                            href="#"
                            data-toggle="dropdown"
                            class="dropdown-toggle"> <i
                                class="glyphicon glyphicon-user"></i> <strong>${usuarioSessao.usuario.login}</strong>
                                <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a
                                    href="${linkTo[AutorizacaoController].solicitar}">Solicitar
                                        Autorização</a></li>
                                <li><a
                                    href="${linkTo[AutorizacaoController].acompanhar}">Acompanhar
                                        Solicitações</a></li>
                                <li class="divider"></li>
                                <li><a
                                    href="${linkTo[LoginController].logout}"><i
                                        class="fa fa-sign-out"></i> Sair</a></li>
                            </ul></li>
                    </ul>
                </c:if>
            </div>
        </div>
    </nav>
    <!-- /navbar -->