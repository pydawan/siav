package br.gov.go.agr.siav.models;

import java.util.Arrays;
import java.util.List;

/**
 * Representa o estado de Goiás e seus municípios.
 * 
 * @author thiago
 * @version 1.0
 *
 */
public abstract class Goias {
    public final static List<String> municipios = Arrays.asList(
        "Abadia de Goiás",
        "Abadiânia",
        "Acreúna",
        "Adelândia",
        "Água Fria de Goiás",
        "Água Limpa",
        "Águas Lindas de Goiás",
        "Alexânia",
        "Aloândia",
        "Alto Horizonte",
        "Alto Paraíso de Goiás",
        "Alvorada do Norte",
        "Amaralina",
        "Americano do Brasil",
        "Amorinópolis",
        "Anápolis",
        "Anhanguera",
        "Anicuns",
        "Aparecida de Goiânia",
        "Aparecida do Rio Doce",
        "Aporé",
        "Araçu",
        "Aragarças",
        "Aragoiânia",
        "Araguapaz",
        "Arenópolis",
        "Aruanã",
        "Aurilândia",
        "Avelinópolis",
        "Baliza",
        "Barro Alto",
        "Bela Vista de Goiás",
        "Bom Jardim de Goiás",
        "Bom Jesus de Goiás",
        "Bonfinópolis",
        "Bonópolis",
        "Brazabrantes",
        "Britânia",
        "Buriti Alegre",
        "Buriti de Goiás",
        "Buritinópolis",
        "Cabeceiras",
        "Cachoeira Alta",
        "Cachoeira de Goiás",
        "Cachoeira Dourada",
        "Caçu",
        "Caiapônia",
        "Caldas Novas",
        "Caldazinha",
        "Campestre de Goiás",
        "Campinaçu",
        "Campinorte",
        "Campo Alegre de Goiás",
        "Campo Limpo de Goiás",
        "Campos Belos",
        "Campos Verdes",
        "Carmo do Rio Verde",
        "Castelândia",
        "Catalão",
        "Caturaí",
        "Cavalcante",
        "Ceres",
        "Cezarina",
        "Chapadão do Céu",
        "Cidade Ocidental",
        "Cocalzinho de Goiás",
        "Colinas do Sul",
        "Córrego do Ouro",
        "Corumbá de Goiás",
        "Corumbaíba",
        "Cristalina",
        "Cristianópolis",
        "Crixás",
        "Cromínia",
        "Cumari",
        "Damianópolis",
        "Damolândia",
        "Davinópolis",
        "Diorama",
        "Divinópolis de Goiás",
        "Doverlândia",
        "Edealina",
        "Edéia",
        "Estrela do Norte",
        "Faina",
        "Fazenda Nova",
        "Firminópolis",
        "Flores de Goiás",
        "Formosa",
        "Formoso",
        "Gameleira de Goiás",
        "Goianápolis",
        "Goiandira",
        "Goianésia",
        "Goiânia",
        "Goianira",
        "Goiás",
        "Goiatuba",
        "Gouvelândia",
        "Guapó",
        "Guaraíta",
        "Guarani de Goiás",
        "Guarinos",
        "Heitoraí",
        "Hidrolândia",
        "Hidrolina",
        "Iaciara",
        "Inaciolândia",
        "Indiara",
        "Inhumas",
        "Ipameri",
        "Ipiranga de Goiás",
        "Iporá",
        "Israelândia",
        "Itaberaí",
        "Itaguari",
        "Itaguaru",
        "Itajá",
        "Itapaci",
        "Itapirapuã",
        "Itapuranga",
        "Itarumã",
        "Itauçu",
        "Itumbiara",
        "Ivolândia",
        "Jandaia",
        "Jaraguá",
        "Jataí",
        "Jaupaci",
        "Jesúpolis",
        "Joviânia",
        "Jussara",
        "Lagoa Santa",
        "Leopoldo de Bulhões",
        "Luziânia",
        "Mairipotaba",
        "Mambaí",
        "Mara Rosa",
        "Marzagão",
        "Matrinchã",
        "Maurilândia",
        "Mimoso de Goiás",
        "Minaçu",
        "Mineiros",
        "Moiporá",
        "Monte Alegre de Goiás",
        "Montes Claros de Goiás",
        "Montividiu",
        "Montividiu do Norte",
        "Morrinhos",
        "Morro Agudo de Goiás",
        "Mossâmedes",
        "Mozarlândia",
        "Mundo Novo",
        "Mutunópolis",
        "Nazário",
        "Nerópolis",
        "Niquelândia",
        "Nova América",
        "Nova Aurora",
        "Nova Crixás",
        "Nova Glória",
        "Nova Iguaçu de Goiás",
        "Nova Roma",
        "Nova Veneza",
        "Novo Brasil",
        "Novo Gama",
        "Novo Planalto",
        "Orizona",
        "Ouro Verde de Goiás",
        "Ouvidor",
        "Padre Bernardo",
        "Palestina de Goiás",
        "Palmeiras de Goiás",
        "Palmelo",
        "Palminópolis",
        "Panamá",
        "Paranaiguara",
        "Paraúna",
        "Perolândia",
        "Petrolina de Goiás",
        "Pilar de Goiás",
        "Piracanjuba",
        "Piranhas",
        "Pirenópolis",
        "Pires do Rio",
        "Planaltina",
        "Pontalina",
        "Porangatu",
        "Porteirão",
        "Portelândia",
        "Posse",
        "Professor Jamil",
        "Quirinópolis",
        "Rialma",
        "Rianápolis",
        "Rio Quente",
        "Rio Verde",
        "Rubiataba",
        "Sanclerlândia",
        "Santa Bárbara de Goiás",
        "Santa Cruz de Goiás",
        "Santa Fé de Goiás",
        "Santa Helena de Goiás",
        "Santa Isabel",
        "Santa Rita do Araguaia",
        "Santa Rita do Novo Destino",
        "Santa Rosa de Goiás",
        "Santa Tereza de Goiás",
        "Santa Terezinha de Goiás",
        "Santo Antônio da Barra",
        "Santo Antônio de Goiás",
        "Santo Antônio do Descoberto",
        "São Domingos",
        "São Francisco de Goiás",
        "São João da Paraúna",
        "São João d`Aliança",
        "São Luís de Montes Belos",
        "São Luíz do Norte",
        "São Miguel do Araguaia",
        "São Miguel do Passa Quatro",
        "São Patrício",
        "São Simão",
        "Senador Canedo",
        "Serranópolis",
        "Silvânia",
        "Simolândia",
        "Sítio d`Abadia",
        "Taquaral de Goiás",
        "Teresina de Goiás",
        "Terezópolis de Goiás",
        "Três Ranchos",
        "Trindade",
        "Trombas",
        "Turvânia",
        "Turvelândia",
        "Uirapuru",
        "Uruaçu",
        "Uruana",
        "Urutaí",
        "Valparaíso de Goiás",
        "Varjão",
        "Vianópolis",
        "Vicentinópolis",
        "Vila Boa",
        "Vila Propício"
    );
    public final static List<String> rodovias = Arrays.asList(
        "GO-010",
        "GO-020",
        "GO-040",
        "GO-050",
        "GO-060",
        "GO-070",
        "GO-080",
        "GO-108",
        "GO-110",
        "GO-112",
        "GO-114",
        "GO-116",
        "GO-118",
        "GO-132",
        "GO-139",
        "GO-142",
        "GO-147",
        "GO-151",
        "GO-154",
        "GO-156",
        "GO-162",
        "GO-164",
        "GO-173",
        "GO-174",
        "GO-178",
        "GO-180",
        "GO-184",
        "GO-188",
        "GO-194",
        "GO-206",
        "GO-210",
        "GO-213",
        "GO-215",
        "GO-217",
        "GO-219",
        "GO-220",
        "GO-221",
        "GO-222",
        "GO-225",
        "GO-230",
        "GO-236",
        "GO-239",
        "GO-241",
        "GO-244",
        "GO-301",
        "GO-302",
        "GO-305",
        "GO-306",
        "GO-307",
        "GO-309",
        "GO-319",
        "GO-320",
        "GO-324",
        "GO-326",
        "GO-330",
        "GO-333",
        "GO-334",
        "GO-336",
        "GO-338",
        "GO-341",
        "GO-342",
        "GO-346",
        "GO-347",
        "GO-353",
        "GO-401",
        "GO-402",
        "GO-403",
        "GO-404",
        "GO-405",
        "GO-406",
        "GO-407",
        "GO-408",
        "GO-409",
        "GO-410",
        "GO-411",
        "GO-412",
        "GO-413",
        "GO-414",
        "GO-415",
        "GO-416",
        "GO-417",
        "GO-418",
        "GO-419",
        "GO-420",
        "GO-421",
        "GO-422",
        "GO-423",
        "GO-424",
        "GO-425",
        "GO-426",
        "GO-427",
        "GO-428",
        "GO-429",
        "GO-430",
        "GO-431",
        "GO-432",
        "GO-433",
        "GO-434",
        "GO-435",
        "GO-436",
        "GO-437",
        "GO-438",
        "GO-439",
        "GO-440",
        "GO-441",
        "GO-442",
        "GO-443",
        "GO-444",
        "GO-445",
        "GO-446",
        "GO-447",
        "GO-448",
        "GO-449",
        "GO-450",
        "GO-451",
        "GO-452",
        "GO-453",
        "GO-454",
        "GO-455",
        "GO-456",
        "GO-457",
        "GO-458",
        "GO-459",
        "GO-460",
        "GO-461",
        "GO-462",
        "GO-463",
        "GO-465",
        "GO-466",
        "GO-467",
        "GO-468",
        "GO-469",
        "GO-470",
        "GO-471",
        "GO-472",
        "GO-473",
        "GO-474",
        "GO-475",
        "GO-476",
        "GO-477",
        "GO-478",
        "GO-479",
        "GO-480",
        "GO-481",
        "GO-482",
        "GO-483",
        "GO-484",
        "GO-485",
        "GO-486",
        "GO-498",
        "GO-501",
        "GO-502",
        "GO-503",
        "GO-504",
        "GO-505",
        "GO-506",
        "GO-508",
        "GO-509",
        "GO-510",
        "GO-511",
        "GO-512",
        "GO-513",
        "GO-514",
        "GO-515",
        "GO-516",
        "GO-517",
        "GO-518",
        "GO-519",
        "GO-520",
        "GO-521",
        "GO-522",
        "GO-523",
        "GO-524",
        "GO-525",
        "GO-526",
        "GO-527",
        "GO-528",
        "GO-529",
        "GO-530",
        "GO-531",
        "GO-532",
        "GO-533",
        "GO-534",
        "GO-535",
        "GO-536",
        "GO-537",
        "GO-538",
        "GO-539",
        "GO-540",
        "GO-541",
        "GO-542",
        "GO-543",
        "GO-544",
        "GO-545",
        "GO-546",
        "GO-547",
        "GO-548",
        "GO-549",
        "GO-550",
        "GO-551",
        "GO-552",
        "GO-553",
        "GO-554",
        "GO-555",
        "GO-556",
        "GO-557",
        "GO-558",
        "GO-559",
        "GO-560",
        "GO-561",
        "GO-562",
        "GO-563",
        "GO-573",
        "GO-576",
        "GO-585",
        "GO-587",
        "BR-010",
        "BR-020",
        "BR-050",
        "BR-060",
        "BR-070",
        "BR-080",
        "BR-153",
        "BR-158",
        "BR-251",
        "BR-352",
        "BR-364",
        "BR-414",
        "BR-452",
        "BR-457",
        "BR-483",
        "BR-490"
    );
}