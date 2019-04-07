import main.decompressFile.UnpackFile;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestUnpackFile {

    @Test
    public void unzipFile() {
        File fileToUnzip = new File("/home/dawid/IdeaProjects/WebDriverUpdater/download/chromedriver_win32.zip");
        File destination = new File("/home/dawid/IdeaProjects/WebDriverUpdater/download");
        UnpackFile unpackFile = new UnpackFile(fileToUnzip,destination);
        unpackFile.decompress();

    }
}
