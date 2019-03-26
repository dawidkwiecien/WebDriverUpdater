package main.download;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownloader {
    private final String urlToDownload;
    private final File pathToSaveFile;
    private File result;

    public FileDownloader(String urlToDownload, File pathToSaveFile) {
        this.urlToDownload = urlToDownload;
        this.pathToSaveFile = pathToSaveFile;
    }

    public File download() throws IOException {
        URL url = getURL();

        OutputStream out = getOutputStream(url);

        InputStream in = getInputStream(url);

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

    private File getResultFile() throws IOException {
        return new File(pathToSaveFile + System.getProperty("file.separator"));
    }

    private OutputStream getOutputStream(URL url) throws IOException {
        OutputStream out;
        result = getResultFile();
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
