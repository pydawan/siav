<div
    id="visualizar"
    role="tabpanel"
    class="tab-pane">
    <b:panel>
        <b:panelbody>
            <b:formgroup>
                <label></label>
                <div class="row">
                    <div class="col-sm-10 col-sm-offset-1">
<!--
                        <iframesrc="http://www.sgc.goias.gov.br/upload/arquivos/2012-07/modelo-de-lista-de-passageiros.pdf"width="100%"height="768px"></iframe>
                    </div>
-->
                        <h2 class="text-right">{{ autorizacao.id }}</h2>
                        <br/>
                        <h1 class="text-center">Licença de Viagem</h1>
                        <h4 class="text-center">Viagem sem Caráter de Linha</h4>
                        <table id="autorizacao" class="table table-bordered" width="100%" height="100%">
                            <thead>
                                <tr>
                                    <th colspan="3">
                                        <p class="celula titulo">IDENTIFICAÇÃO DA EMPRESA</p>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <p class="celula">
                                            Registro na AGR<br />
                                            {{ autorizacao.empresa.registroAgr }}
                                        </p>
                                    </td>
                                    <td>
                                        <p class="celula">
                                            CNPJ<br />
                                            {{ autorizacao.empresa.cnpj }}
                                        </p>
                                    </td>
                                    <td>
                                        <p class="celula">
                                            Razão Social<br/>
                                            {{ autorizacao.empresa.razaoSocial | uppercase }}
                                        </p>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <p class="celula">
                                            Endereço<br />
                                            {{ autorizacao.empresa.endereco | uppercase }}
                                        </p>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <table id="identificacao-veiculo" class="table table-bordered" width="100%" height="100%">
                                            <thead>
                                                <tr>
                                                    <th colspan="8">
                                                        <p class="celula titulo">IDENTIFICAÇÃO DO VEÍCULO</p>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td width="10%">
                                                        <p class="celula">
                                                            Placa<br />
                                                            {{ autorizacao.veiculo.placa | uppercase }}
                                                        </p>
                                                    </td>
                                                    <td width="10%">
                                                        <p class="celula">
                                                            Nº do Veículo<br />
                                                            {{ autorizacao.veiculo.numero | uppercase }}
                                                        </p>
                                                    </td>
                                                    <td width="15%">
                                                        <p class="celula">
                                                            Tipo do Veículo<br />
                                                            {{ autorizacao.veiculo.tipo | uppercase }}
                                                        </p>
                                                    </td>
                                                    <td width="10%">
                                                        <p class="celula">
                                                            Ano Fabricação<br />
                                                            {{ autorizacao.veiculo.anoFabricacao }}
                                                        </p>
                                                    </td>
                                                    <td colspan="2" width="5%">
                                                        <p class="celula">
                                                            Lotação<br />
                                                            {{ autorizacao.veiculo.lotacao }}
                                                        </p>
                                                    </td>
                                                    <td width="25%">
                                                        <p class="celula">
                                                            Marca / Modelo<br />
                                                            {{ (autorizacao.veiculo.modelo.fabricante.nome + 
                                                            ' / ' + autorizacao.veiculo.modelo.nome) | uppercase }}
                                                        </p>
                                                    </td>
                                                    <td width="25%">
                                                        <p class="celula">
                                                            Carroceria<br />
                                                            {{ autorizacao.veiculo.modelo.fabricante.nome | uppercase }}
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3" width="35%">
                                                        <p class="celula">
                                                            1º Motorista<br />
                                                            {{ autorizacao.veiculo.primeiroMotorista | uppercase }}
                                                        </p>
                                                    </td>
                                                    <td width="15%">
                                                        <p class="celula">
                                                            Prontuário<br />
                                                            {{ autorizacao.veiculo.cnhPrimeiroMotorista | uppercase }}
                                                        </p>
                                                    </td>
                                                    <td colspan="3" width="35%">
                                                        <p class="celula">
                                                            2º Motorista<br/>
                                                            {{ autorizacao.veiculo.segundoMotorista | uppercase }}
                                                        </p>
                                                    </td>
                                                    <td width="15%">
                                                        <p class="celula">
                                                            Prontuário<br/>
                                                            {{ autorizacao.veiculo.cnhSegundoMotorista | uppercase }}
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="4">
                                                        <table id="roteiro-viagem" class="table table-bordered" width="100%" height="100%">
                                                            <thead>
                                                                <tr>
                                                                    <th colspan="3">
                                                                        <p class="celula titulo">ROTEIRO DA VIAGEM</p>
                                                                    </th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <p class="celula">1</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="celula">Niquelândia</p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="celula">88,00 km</p>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                    <td colspan="4">
                                                        <table id="informacoes-viagem" class="table table-bordered" width="100%" height="100%">
                                                            <thead>
                                                                <tr>
                                                                    <th colspan="8">
                                                                        <p class="celula titulo">INFORMAÇÕES DA VIAGEM</p>
                                                                    </th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td colspan="8">
                                                                        <p class="celula">Passageiros: 45</p>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td colspan="4">
                                                                        <p class="celula">
                                                                            Saída<br />
                                                                            {{ autorizacao.viagem.dataSaida }} às {{ autorizacao.viagem.horaSaida }}
                                                                        </p>
                                                                    </td>
                                                                    <td colspan="4">
                                                                        <p class="celula">
                                                                            Retorno<br />
                                                                            {{ autorizacao.viagem.dataRetorno }} às {{ autorizacao.viagem.horaRetorno }}
                                                                        </p>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td colspan="4">
                                                                        <p class="celula">
                                                                            Extensão<br />
                                                                            176,00 km ({{ autorizacao.viagem.tipo }})
                                                                        </p>
                                                                    </td>
                                                                    <td colspan="4">
                                                                        <p class="celula">
                                                                            Local de início<br />
                                                                            Niquelândia
                                                                        </p>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="4">
                                                        <table id="nota-fiscal" class="table table-bordered" width="100%" height="100%">
                                                            <thead>
                                                                <tr>
                                                                    <th colspan="4">
                                                                        <p class="celula titulo">NOTA FISCAL DE PRESTAÇÃO DE SERVIÇOS</p>
                                                                    </th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <p class="celula" width="30%">
                                                                            Valor<br />
                                                                            R$ 150,00
                                                                        </p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="celula" width="10%">
                                                                            Série<br />
                                                                            U
                                                                        </p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="celula" width="20%">
                                                                            Número<br />
                                                                            963
                                                                        </p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="celula" width="40%">
                                                                            Data de Emissão<br />
                                                                            25/09/2014
                                                                        </p>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                    <td colspan="4">
                                                        <table id="boleto-bancario" class="table table-bordered" width="100%" height="100%">
                                                            <thead>
                                                                <tr>
                                                                    <th colspan="3">
                                                                        <p class="celula titulo">BOLETO BANCÁRIO</p>
                                                                    </th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <p class="celula">
                                                                            Número<br />
                                                                            426800139
                                                                        </p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="celula">
                                                                            Data e Pagamento<br />
                                                                            25/09/2014
                                                                        </p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="celula">
                                                                            Valor do Boleto<br />
                                                                            R$ 33,79
                                                                        </p>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="8">
                                                        <table id="observacao-nf" class="table table-bordered">
                                                            <tbody>
                                                                <tr>
                                                                    <td colspan="3"><p class="celula">Observação (NF)</p></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="table table-bordered" width="100%" height="100%">
                            <tbody>
                                <tr>
                                    <td>
                                        <p class="celula resolucao-agr"> 
                                            De acordo com o Art. 32, da Resolução Nº 005/2008 - CG, são de porte obrigatório quando da 
                                            realização da viagem, a seguinte documentação, além da exigida pela legislação de trânsito:
                                            <ol>
                                                <li>Cópia autenticada do Certificado de Registro Cadastral - CRC;</li>
                                                <li>Licença de Viagem;</li>
                                                <li>Atestado médico anual de aptidão física e mental do motorista;</li>
                                                <li>
                                                    Apólice de seguro de responsabilidade civil em vigor, onde conste a identificação do veículo 
                                                    utilizado na viagem;
                                                </li>
                                                <li>Certificado de Registro de Veículo - CRV;</li>
                                                <li>
                                                    Certidão Negativa Criminal do condutor do veículo, expedida pelo cartório distribuidor do 
                                                    local de sua residência <br /> nos últimos cinco anos;
                                                </li>
                                                <li>Comprovação do vínculo dos motoristas com a detentora do certificado de registro;</li>
                                                <li>Formulário para registro das reclamações de danos ou extravio de bagagens;</li>
                                                <li>Cópia da Nota Fiscal da viagem, discriminando o seu itinerário;</li>
                                                <li>
                                                    Relação dos passageiros, datilografada ou digitada, sem rasuras, contendo o nome 
                                                    e o número da respectiva identidade, <br/ >devendo a mesma estar fechada após o último nome 
                                                    e conter o carimbo e a assinatura do representante legal da autorizatária, seguida de 
                                                    linha transversal aposta na parte não utilizada da relação.
                                                </li>
                                            </ol>
                                        </p>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
            </b:formgroup>
        </b:panelbody>
    </b:panel>
</div>
