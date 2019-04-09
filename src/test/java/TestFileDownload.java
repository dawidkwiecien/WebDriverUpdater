import main.fileUtils.FileDownloader;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestFileDownload {
    @Test()
    public void testDownload() throws IOException {
        String url="https://chromedriver.storage.googleapis.com/73.0.3683.68/chromedriver_win32.zip";
        FileDownloader fileDownloader = new FileDownloader(url);
        File file=fileDownloader.download();
        assert(file != null);
    }

    @Test()
    public void testDownload2() throws IOException {
        String url="https://github.com/mozilla/geckodriver/releases/download/v0.24.0/geckodriver-v0.24.0-linux32.tar.gz";
        FileDownloader fileDownloader = new FileDownloader(url);
        File file=fileDownloader.download();
        assert(file != null);
    }

    @Test()
    public void testDownload3() throws IOException{
        String url="https://chromedriver.storage.googleapis.com";
        FileDownloader fileDownloader = new FileDownloader(url);
        File file=fileDownloader.download();
        assert(file != null);
    }

    @Test()
    public void testDownload4() throws IOException{
        String url="https://api.github.com/repos/mozilla/geckodriver/releases";
        FileDownloader fileDownloader = new FileDownloader(url);
        File file=fileDownloader.download();
        assert(file != null);
    }
}
