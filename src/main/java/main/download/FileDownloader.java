package main.download;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

        OutputStream out = getOutputStream();

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

    private File getResultFile(URL url) throws IOException{
        Map<String,String> resultFileName = getFileName(url);
        return File.createTempFile(resultFileName.get("prefix"), resultFileName.get("sufix"));
    }

    private Map getFileName(URL url){
        String fileName = url.getPath().substring(url.getPath().lastIndexOf('/')+1);
        Map<String,String> resultFileName = new HashMap<>();
        String prefix=fileName.lastIndexOf(".")!=-1?fileName.substring(0,fileName.lastIndexOf(".")):"tmp";
        String sufix=fileName.lastIndexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")):".tmp";
        resultFileName.put("prefix",prefix);
        resultFileName.put("sufix",sufix);
        return resultFileName;
    }

    private OutputStream getOutputStream() throws IOException {
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
