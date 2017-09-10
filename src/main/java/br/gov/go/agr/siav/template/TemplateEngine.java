package br.gov.go.agr.siav.template;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.pmw.tinylog.Logger;

import jedi.db.engine.JediORMEngine;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.FileLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

/**
 * Mecanismo de criação de templates.
 * 
 * @author thiago
 * @version 1.0
 */
public abstract class TemplateEngine {

    private static final FileLoader loader = new FileLoader();

    private static final PebbleEngine engine = new PebbleEngine();

    private static String TEMPLATES_DIR;

    static {
        TEMPLATES_DIR = String.format("%s/src/main/resources/templates", JediORMEngine.APP_ROOT_DIR);
        TEMPLATES_DIR = TEMPLATES_DIR.replace("//", System.getProperty("file.separator"));
        loader.setPrefix(TEMPLATES_DIR);
        engine.setLoader(loader);
    }

    public static String render(Map<String, Object> context, String templateName) {
        try {
            PebbleTemplate template = engine.getTemplate(templateName);
            Writer writer = new StringWriter();
            template.evaluate(writer, context);
            return writer.toString();
        } catch (PebbleException | IOException e) {
            Logger.warn(e);
        }
        return null;
    }

    public static void setDir(String dir) {
        dir = dir == null ? "" : dir.trim();
        if (!dir.isEmpty()) {
            TEMPLATES_DIR = dir;
        }
    }

    public static String getDir() {
        return TEMPLATES_DIR;
    }

}
