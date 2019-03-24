import main.download.FileDownloader;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class TestFileDownload {
    @Test
    public void testDownload() throws IOException {
        String url="https://chromedriver.storage.googleapis.com/73.0.3683.68/chromedriver_win32.zip";
        File path= new File("/home/dawid/IdeaProjects/WebDriverUpdater/download");
        FileDownloader fileDownloader = new FileDownloader(url,path);
        File file=fileDownloader.download();
        assert(file != null);
    }
}
