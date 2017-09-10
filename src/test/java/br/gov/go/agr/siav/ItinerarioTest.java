package br.gov.go.agr.siav;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import jedi.db.engine.JediORMEngine;

import org.junit.BeforeClass;
import org.junit.Test;

import br.gov.go.agr.siav.models.Itinerario;

import com.opencsv.CSVReader;

public class ItinerarioTest {

    @BeforeClass
    public static void setup() {
        JediORMEngine.FOREIGN_KEY_CHECKS = false;
//        JediORMEngine.reset();
    }

    @Test
    public void test() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("itinerarios.csv").getFile());
            CSVReader reader = new CSVReader(new FileReader(file));
            reader.readAll().forEach(linha -> {
                Itinerario itinerario = new Itinerario();
                if (!isNullOrEmpty(linha[0])) {
                    itinerario.setTrecho(linha[0]);
                    itinerario.setOrigem(linha[0].split("/")[0].trim());
                    itinerario.setDestino(linha[0].split("/")[1].trim());
                }
                if (!isNullOrEmpty(linha[1])) {
                    itinerario.setExtensao(new BigDecimal(Double.parseDouble(linha[1].replace(",", "."))));
                }
                if (!isNullOrEmpty(linha[2])) {
                    itinerario.setPrecoIdaOnibus(new BigDecimal(Double.parseDouble(linha[2].replace(",", "."))));
                }
                if (!isNullOrEmpty(linha[3])) {
                    itinerario.setPrecoIdaVoltaOnibus(new BigDecimal(Double.parseDouble(linha[3].replace(",", "."))));
                }
                if (!isNullOrEmpty(linha[4])) {
                    itinerario.setPrecoIdaMicroOnibus(new BigDecimal(Double.parseDouble(linha[4].replace(",", "."))));
                }
                if (!isNullOrEmpty(linha[5])) {
                    itinerario.setPrecoIdaVoltaMicroOnibus(new BigDecimal(Double.parseDouble(linha[5].replace(",", "."))));
                }
                itinerario.save();
            });
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
