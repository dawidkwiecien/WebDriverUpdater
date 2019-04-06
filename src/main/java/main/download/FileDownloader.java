package main.download;

import org.codehaus.plexus.util.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownloader {
    private final String urlToDownload;
    private File result;

    public FileDownloader(String urlToDownload) {
        this.urlToDownload = urlToDownload;
    }

    public File download() throws IOException {
        URL url = getURL();

        result = getResultFile(url);

        InputStream in = getInputStream(url);

        OutputStream out = getOutputStream(url);

        procedDownload(in, out);

        closeConnection(in, out);

        return result;
    }

    private URL getURL() throws MalformedURLException {
        URL url;
        url = new URL(urlToDownload);

        return url;
    }

    private InputStream getInputStream(URL url) throws IOException {
        InputStream in;
        in = url.openStream();

        return in;
    }

    private File getResultFile(URL url) throws IOException {
            return File.createTempFile("tmp", ".tmp");
    }

    private OutputStream getOutputStream(URL url) throws IOException {
        OutputStream out;
        FileOutputStream fos = new FileOutputStream(result);
        out = new BufferedOutputStream(fos);

        return out;
    }

    private void procedDownload(InputStream in, OutputStream out) throws IOException {
        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
    }

    private void closeConnection(InputStream in, OutputStream out) throws IOException {
        out.close();
        in.close();
    }
}
