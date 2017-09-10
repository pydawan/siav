/**
 * @author thiagoamm
 * @version 1.0
 * @date 02/12/2015
 */

var app = angular.module('AutorizacaoApp');

app.directive('selectFabricanteVeiculo', function() {
    return {
        restrict: 'E',
        templateUrl: '/siav/static/js/angular/partials/selectFabricanteVeiculo.html',
        replace: true,
        link: function(scope, element, attrs) {
            element.bind('change', function() {
                element.selectpicker('refresh');
            });
        }
    };
});

app.directive('selectModeloVeiculo', function() {
    return {
        restrict: 'E',
        templateUrl: '/siav/static/js/angular/partials/selectModeloVeiculo.html',
        replace: true,
        link: function(scope, element, attrs) {
            element.bind('change', function() {
                element.selectpicker('refresh');
            });
        }
    };
});

app.directive('selectTipoVeiculo', function() {
    return {
        restrict: 'E',
        templateUrl: '/siav/static/js/angular/partials/selectTipoVeiculo.html',
        replace: true,
        link: function(scope, element, attrs) {
            element.bind('change', function() {
                element.selectpicker('refresh');
            });
        }
    };
});

app.directive('selectTipoViagem', function() {
    return {
        restrict: 'E',
        templateUrl: '/siav/static/js/angular/partials/selectTipoViagem.html',
        replace: true,
        link: function(scope, element, attrs) {
            var $grupo_data_saida = angular.element('#grupo-datahora-saida');
            var $grupo_data_retorno = angular.element('#grupo-datahora-retorno');
            $grupo_data_retorno.hide();
            element.bind('change', function() {
                var tipoViagem = element.selectpicker('val');
                switch (tipoViagem) {
                    case 'Ida':
                        $grupo_data_saida.show();
                        $grupo_data_retorno.hide();
                        break;
                    case 'Ida e Volta':
                        $grupo_data_saida.show();
                        $grupo_data_retorno.show();
                        break;
                    default:
                        $grupo_data_saida.hide();
                        $grupo_data_retorno.hide();
                        break;
                }
                element.selectpicker('refresh');
            });
        }
    };
});

app.directive('datePicker', function() {
    return {
        restrict: 'E',
        templateUrl: '/siav/static/js/angular/partials/datePicker.html',
        replace: true,
        require: '?ngModel',
        link: function(scope, element, attrs, ctrl) {
            element.datepicker({
                language : 'pt-BR',
                format : 'dd/mm/yyyy',
                showOnFocus : false,
                todayHighlight : true
            });
            element.tooltip({
                'placement' : 'bottom',
                'trigger' : 'hover',
                'delay' : {
                    'show' : 100,
                    'hide' : 1
                }
            });
        }
    };
});

app.directive('timePicker', function() {
    return {
        restrict: 'E',
        templateUrl: '/siav/static/js/angular/partials/timePicker.html',
        replace: true,
        require: '?ngModel',
        link: function(scope, element, attrs, ctrl) {
            element.bfhtimepicker({
                align: 'right'
            });
            element.tooltip({
                'placement' : 'bottom',
                'trigger' : 'hover',
                'delay' : {
                    'show' : 100,
                    'hide' : 1
                }
            });
            element.on('change.bfhtimepicker', function() {
                console.log(element.val());
            });
        }
    };
});

app.directive('selectPicker', function() {
    return {
        restrict: 'E',
        templateUrl: '/siav/static/js/angular/partials/selectPicker.html',
        replace: true,
        require: '?ngModel',
        link: function(scope, element, attrs, ctrl) {
            element.selectpicker();
        }
    };
});