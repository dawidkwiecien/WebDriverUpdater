package main.fileUtils;

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

    public File download()  {
        URL url = getURL();

        result = getResultFile(url);

        InputStream in = getInputStream(url);

        OutputStream out = getOutputStream();

        procedDownload(in, out);

        closeConnection(in, out);

        return result;
    }

    private URL getURL() {
        URL url=null;
        try {
            url = new URL(urlToDownload);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    private InputStream getInputStream(URL url) {
        InputStream in=null;
        try {
            in = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return in;
    }

    private File getResultFile(URL url) {
        Map<String,String> resultFileName = getFileName(url);
        try {
            return File.createTempFile(resultFileName.get("prefix"), resultFileName.get("sufix"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    private OutputStream getOutputStream()  {
        OutputStream out;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out = new BufferedOutputStream(fos);
        return out;
    }

    private void procedDownload(InputStream in, OutputStream out)  {
        try {
            for (int b; (b = in.read()) != -1; ) {
                out.write(b);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void closeConnection(InputStream in, OutputStream out)  {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
