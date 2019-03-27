package main.download;

import org.codehaus.plexus.util.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

public class FileDownloader {
    private final String urlToDownload;
    private final Path pathToSaveFile;
    private final Boolean temporary;
    private File result;

    public FileDownloader(String urlToDownload, Path pathToSaveFile, Boolean temporary) {
        this.urlToDownload = urlToDownload;
        this.pathToSaveFile = pathToSaveFile;
        this.temporary = temporary;
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
        if (temporary)
            return File.createTempFile("tmp", ".tmp", pathToSaveFile.toFile());
        else
            return new File(pathToSaveFile + System.getProperty("file.separator") + FileUtils.filename(url.getFile()));
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
