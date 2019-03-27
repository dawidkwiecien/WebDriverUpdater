import main.download.FileDownloader;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFileDownload {
    @Test(enabled = true)
    public void testDownload() throws IOException {
        String url="https://chromedriver.storage.googleapis.com/73.0.3683.68/chromedriver_win32.zip";
        Path path= Paths.get("/home/dawid/IdeaProjects/WebDriverUpdater/download");
        FileDownloader fileDownloader = new FileDownloader(url,path);
        File file=fileDownloader.download();
        assert(file != null);
    }

    @Test(enabled = true)
    public void testDownload2() throws IOException {
        String url="https://github.com/mozilla/geckodriver/releases/download/v0.24.0/geckodriver-v0.24.0-linux32.tar.gz";
        Path path= Paths.get("/home/dawid/IdeaProjects/WebDriverUpdater/download");
        FileDownloader fileDownloader = new FileDownloader(url,path);
        File file=fileDownloader.download();
        assert(file != null);
    }
}
