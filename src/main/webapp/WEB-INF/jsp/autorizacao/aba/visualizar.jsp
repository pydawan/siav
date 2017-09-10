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
                        <h1 class="text-center">Licen�a de Viagem</h1>
                        <h4 class="text-center">Viagem sem Car�ter de Linha</h4>
                        <table id="autorizacao" class="table table-bordered" width="100%" height="100%">
                            <thead>
                                <tr>
                                    <th colspan="3">
                                        <p class="celula titulo">IDENTIFICA��O DA EMPRESA</p>
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
                                            Raz�o Social<br/>
                                            {{ autorizacao.empresa.razaoSocial | uppercase }}
                                        </p>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <p class="celula">
                                            Endere�o<br />
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
                                                        <p class="celula titulo">IDENTIFICA��O DO VE�CULO</p>
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
                                                            N� do Ve�culo<br />
                                                            {{ autorizacao.veiculo.numero | uppercase }}
                                                        </p>
                                                    </td>
                                                    <td width="15%">
                                                        <p class="celula">
                                                            Tipo do Ve�culo<br />
                                                            {{ autorizacao.veiculo.tipo | uppercase }}
                                                        </p>
                                                    </td>
                                                    <td width="10%">
                                                        <p class="celula">
                                                            Ano Fabrica��o<br />
                                                            {{ autorizacao.veiculo.anoFabricacao }}
                                                        </p>
                                                    </td>
                                                    <td colspan="2" width="5%">
                                                        <p class="celula">
                                                            Lota��o<br />
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
                                                            1� Motorista<br />
                                                            {{ autorizacao.veiculo.primeiroMotorista | uppercase }}
                                                        </p>
                                                    </td>
                                                    <td width="15%">
                                                        <p class="celula">
                                                            Prontu�rio<br />
                                                            {{ autorizacao.veiculo.cnhPrimeiroMotorista | uppercase }}
                                                        </p>
                                                    </td>
                                                    <td colspan="3" width="35%">
                                                        <p class="celula">
                                                            2� Motorista<br/>
                                                            {{ autorizacao.veiculo.segundoMotorista | uppercase }}
                                                        </p>
                                                    </td>
                                                    <td width="15%">
                                                        <p class="celula">
                                                            Prontu�rio<br/>
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
                                                                        <p class="celula">Niquel�ndia</p>
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
                                                                        <p class="celula titulo">INFORMA��ES DA VIAGEM</p>
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
                                                                            Sa�da<br />
                                                                            {{ autorizacao.viagem.dataSaida }} �s {{ autorizacao.viagem.horaSaida }}
                                                                        </p>
                                                                    </td>
                                                                    <td colspan="4">
                                                                        <p class="celula">
                                                                            Retorno<br />
                                                                            {{ autorizacao.viagem.dataRetorno }} �s {{ autorizacao.viagem.horaRetorno }}
                                                                        </p>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td colspan="4">
                                                                        <p class="celula">
                                                                            Extens�o<br />
                                                                            176,00 km ({{ autorizacao.viagem.tipo }})
                                                                        </p>
                                                                    </td>
                                                                    <td colspan="4">
                                                                        <p class="celula">
                                                                            Local de in�cio<br />
                                                                            Niquel�ndia
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
                                                                        <p class="celula titulo">NOTA FISCAL DE PRESTA��O DE SERVI�OS</p>
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
                                                                            S�rie<br />
                                                                            U
                                                                        </p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="celula" width="20%">
                                                                            N�mero<br />
                                                                            963
                                                                        </p>
                                                                    </td>
                                                                    <td>
                                                                        <p class="celula" width="40%">
                                                                            Data de Emiss�o<br />
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
                                                                        <p class="celula titulo">BOLETO BANC�RIO</p>
                                                                    </th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <p class="celula">
                                                                            N�mero<br />
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
                                                                    <td colspan="3"><p class="celula">Observa��o (NF)</p></td>
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
                                            De acordo com o Art. 32, da Resolu��o N� 005/2008 - CG, s�o de porte obrigat�rio quando da 
                                            realiza��o da viagem, a seguinte documenta��o, al�m da exigida pela legisla��o de tr�nsito:
                                            <ol>
                                                <li>C�pia autenticada do Certificado de Registro Cadastral - CRC;</li>
                                                <li>Licen�a de Viagem;</li>
                                                <li>Atestado m�dico anual de aptid�o f�sica e mental do motorista;</li>
                                                <li>
                                                    Ap�lice de seguro de responsabilidade civil em vigor, onde conste a identifica��o do ve�culo 
                                                    utilizado na viagem;
                                                </li>
                                                <li>Certificado de Registro de Ve�culo - CRV;</li>
                                                <li>
                                                    Certid�o Negativa Criminal do condutor do ve�culo, expedida pelo cart�rio distribuidor do 
                                                    local de sua resid�ncia <br /> nos �ltimos cinco anos;
                                                </li>
                                                <li>Comprova��o do v�nculo dos motoristas com a detentora do certificado de registro;</li>
                                                <li>Formul�rio para registro das reclama��es de danos ou extravio de bagagens;</li>
                                                <li>C�pia da Nota Fiscal da viagem, discriminando o seu itiner�rio;</li>
                                                <li>
                                                    Rela��o dos passageiros, datilografada ou digitada, sem rasuras, contendo o nome 
                                                    e o n�mero da respectiva identidade, <br/ >devendo a mesma estar fechada ap�s o �ltimo nome 
                                                    e conter o carimbo e a assinatura do representante legal da autorizat�ria, seguida de 
                                                    linha transversal aposta na parte n�o utilizada da rela��o.
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
