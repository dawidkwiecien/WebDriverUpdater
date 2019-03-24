import main.unpack.UnzipFile;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestUnzipFile {

    @Test
    public void unzipFile() throws IOException {
        File fileToUnzip = new File("/home/dawid/IdeaProjects/WebDriverUpdater/download/chromedriver_win32.zip");
        File destination = new File("/home/dawid/IdeaProjects/WebDriverUpdater/download");
        UnzipFile unzipFile = new UnzipFile(fileToUnzip,destination);
        unzipFile.unzip();

    }
}
