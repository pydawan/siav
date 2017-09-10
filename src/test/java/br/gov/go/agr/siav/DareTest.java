package br.gov.go.agr.siav;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import net.anthavio.phanbedder.Phanbedder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author thiago
 *
 */
public class DareTest {

    @Test
    public void testConsultaDAREPago() {
        File phantomjs = Phanbedder.unpack();

        ArrayList<String> arguments = new ArrayList<String>();
        arguments.add("--proxy=10.243.1.3:2303");
        arguments.add("--proxy-type=https");
        arguments.add("--ssl-protocol=any");
        arguments.add("--ignore-ssl-errors=true");

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability("phantomjs.cli.args", arguments);
        capabilities.setCapability(
            PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjs.getAbsolutePath()
        );

        PhantomJSDriver driver = new PhantomJSDriver(capabilities);

        driver.get("https://app.sefaz.go.gov.br/arr-www/view/consultaDocumentos.jsf");

        WebElement numeroDare = driver.findElement(By.name("frmConsulta:tabConsulta:txtNumeroDare"));
        numeroDare.sendKeys("12602541512800081");

        WebElement consultar = driver.findElement(By.name("frmConsulta:tabConsulta:btnConsultar"));
        consultar.click();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement modal = driver.findElement(By.id("frmConsulta:dlgPreviaDare"));

        String html = modal.getAttribute("innerHTML");

        Document doc = Jsoup.parse(html);
        doc.outputSettings().indentAmount(4);
        doc.outputSettings().prettyPrint(true);

        html = doc.html();

        WebElement table = modal.findElement(By.id("frmConsulta:acpDadosDare:j_idt79"));

        System.out.println(table.getText());

        Optional<WebElement> row = table.findElements(By.tagName("tr"))
        .stream()
        .filter(
            e -> e.getText().startsWith("Data de Pagamento:")
        ).findFirst();

        if (row.get().getText().replace("Data de Pagamento:", "").trim().isEmpty()) {
            System.out.println("ATENÇÃO: O D.A.R.E não foi pago!");
        } else {
            System.out.println("PARABÉNS: O D.A.R.E foi pago!");
        }

        driver.quit();
    }
}
