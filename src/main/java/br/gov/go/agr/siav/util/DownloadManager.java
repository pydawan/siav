package br.gov.go.agr.siav.util;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import com.github.axet.wget.WGet;

/**Gerenciador de Downloads.
 * 
 * @author thiago
 * @version 1.0
 */
public final class DownloadManager {

    private static List<String> urls = new ArrayList<>();
    private static File directory = new File(System.getProperty("user.home"));

    public static List<String> getUrls() {
        return urls;
    }

    public static File getDirectory() {
        return directory;
    }

    public static void setDirectory(String path) {
        path = path == null ? "" : path.trim();
        if (!path.isEmpty()) {
            directory = new File(path);
        }
    }

    public static void download(String source) {
        download(source, null);
    }

    public static void download(String source, String target) {
        source = source == null ? "" : source.trim();
        target = target == null ? "" : target.trim();
        if (!source.isEmpty()) {
            try {
                URL url = new URL(source);
                WGet wget;
                String message = String.format("Downloading %s", source);
                System.out.println(message);
                if (target.isEmpty()) {
                    wget = new WGet(url, DownloadManager.directory);
                } else {
                    wget = new WGet(url, new File(target));
                }
                wget.download();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}
