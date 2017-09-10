/**
 * @author thiagoamm
 * @version 1.0
 * @date 27/11/2015
 */

var app = angular.module('AutorizacaoApp');

app.controller('AutorizacaoViagemController', function($scope, webService) {
    // Variáveis.
    $scope.autorizacao = new AutorizacaoViagem();
    $scope.autorizacao.id = 10298902;
    $scope.viagem = {
        'extensao': 0,
        'valor': 0,
        'roteiro': []
    }; 
    $scope.dataSaida = '';
    $scope.dataRetorno = '';
    $scope.horaSaida = '';
    $scope.horaRetorno = '';
    // Obtendo os fabricantes de veículo.
    webService.getFabricantesVeiculo()
    .success(function(data) { 
        // Seleciona o primeiro elemento.
        $scope.autorizacao.veiculo.modelo.fabricante = data[0];
        $scope.fabricantesVeiculo = data;
    });
    // Obtendo os modelos de veículo.
    webService.getModelosVeiculo()
    .success(function(data) {
        $scope.autorizacao.veiculo.modelo = data[0];
        $scope.modelosVeiculo = data;
    });
    // Obtendo os tipos de veículo.
    webService.getTiposVeiculo()
    .success(function(data) {
        $scope.autorizacao.veiculo.tipo = data[0];
        $scope.tiposVeiculo = data;
    });
    // Obtendo os tipos de viagem.
    webService.getTiposViagem()
    .success(function(data) {
        $scope.autorizacao.viagem.tipo = data[0];
        $scope.tiposViagem = data;
    });
    // Métodos.
    $scope.adicionarRoteiros = function() {
        var tipo_veiculo = $('[id="autorizacao.veiculo.tipo"]').val();
        var tipo_viagem = $('[id="autorizacao.viagem.tipo"]').val();
        var roteiros = $('#trecho option:selected').selectpicker('selectAll');
        $(roteiros).each(function(i, e) {
            console.log(e);
            var roteiro = {
                'id': $scope.viagem.roteiro.length,
                'trecho': $(e).val(),
                'extensao': parseFloat($(e).attr('data-extensao')),
                'valor': parseFloat($(e).attr('data-valor'))
            };
            $scope.viagem.roteiro.push(roteiro);
            $scope.viagem.extensao += roteiro.extensao;
            $scope.viagem.valor += roteiro.valor;
        });
        $('#trecho').empty();
        $('#trecho').selectpicker('refresh');
        $('#trecho').selectpicker('render');
        console.log($scope.viagem);
    };
    $scope.removerRoteiro = function(index) {
        var roteiro = $scope.viagem.roteiro.splice(index, 1)[0];
        $scope.viagem.extensao -= roteiro.extensao;
        $scope.viagem.valor -= roteiro.valor;
    };
});