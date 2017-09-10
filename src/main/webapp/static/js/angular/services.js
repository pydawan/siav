/**
 * @author thiagoamm
 * @version 1.0
 * @date 03/12/2015
 */

var app = angular.module('AutorizacaoApp');
// injeção (ingredientes)
app.factory('webService', function($http) {
    // configuração (preparo)
    var _getFabricantesVeiculo = function() {
        return $http.get('/siav/webservice/fabricante-veiculo/json');
    };
    var _getModelosVeiculo = function() {
        return $http.get('/siav/webservice/modelo-veiculo/json');
    };
    var _getTiposVeiculo = function() {
        return $http.get('/siav/webservice/tipos-veiculo/json');
    };
    var _getTiposViagem = function() {
        return $http.get('/siav/webservice/tipos-viagem/json');
    };
    // fabricação (entrega)
    return {
        getFabricantesVeiculo: _getFabricantesVeiculo,
        getModelosVeiculo: _getModelosVeiculo,
        getTiposVeiculo: _getTiposVeiculo,
        getTiposViagem: _getTiposViagem
    };
});