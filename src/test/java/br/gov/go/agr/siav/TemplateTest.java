package br.gov.go.agr.siav;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.gov.go.agr.siav.template.TemplateEngine;

public class TemplateTest {

    @Test
    public void test() {
        Map<String, Object> context = new HashMap<>();
        context.put("nome", "Thiago");
        System.out.println(TemplateEngine.render(context, "email.html"));
    }

}
