/**
 * @author thiagoamm
 * @version 1.0
 * @date 27/11/2015
 */

function Fabricante() {
    this.id = 0;
    this.nome = '';
}

function Modelo() {
    this.id = 0;
    this.nome = '';
    this.fabricante = new Fabricante();
}

function Veiculo() {
    this.id = 0;
    this.placa = '';
    this.numero = '';
    this.tipo = '';
    this.anoFabricacao = '';
    this.lotacao = '',
    this.primeiroMotorista = '';
    this.cnhPrimeiroMotorista = '';
    this.segundoMotorista = '';
    this.cnhSegundoMotorista = '';
    this.modelo = new Modelo();
}

function Empresa() {
    this.id = 0;
    this.registroAgr = '';
    this.cnpj = '';
    this.razaoSocial = '';
    this.endereco = '';
    this.telefone = '';
    this.email = '';
    this.contato = '';
}

function Itinerario() {
    this.id = 0;
    this.origem = '';
    this.destino = '';
    this.trecho = '';
    this.extensao = 0;
    this.rodovia = '';
    this.precoIdaOnibus = 0;
    this.precoIdaVoltaOnibus = 0;
    this.precoIdaMicroOnibus = 0;
    this.precoIdaVoltaMicroOnibus = 0;
}

function Roteiro() {
    this.id = 0;
    this.itinerario = 0;
    this.viagem = 0;
}

function Viagem() {
    this.id = 0;
    this.quantidadePassageiros = 0;
    this.dataSaida = '';
    this.dataRetorno = '';
    this.localSaida = '';
    this.tipo = '';
    this.valor = 0;
    this.extensao = 0;
    this.lotacao = 0;
    this.roteiro = [];
}

function AutorizacaoViagem() {
    this.id = 0;
    this.empresa = new Empresa();
    this.veiculo = new Veiculo();
    this.viagem = new Viagem();
}