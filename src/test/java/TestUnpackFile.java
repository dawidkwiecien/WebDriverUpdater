import main.fileUtils.UnpackFile;
import org.testng.annotations.Test;

import java.io.File;

public class TestUnpackFile {

    File destination = new File("/home/dawid/IdeaProjects/WebDriverUpdater/download");

    @Test
    public void unzipFile() {
        File fileToUnzip = new File("src/test/resources/geckodriver-v0.24.0-win64.zip");
        UnpackFile unpackFile = new UnpackFile(fileToUnzip,destination);
        unpackFile.decompress();

    }

    @Test
    public void unTarFile() {
        File fileToUnzip = new File("src/test/resources/geckodriver-v0.24.0-linux32.tar.gz");
        UnpackFile unpackFile = new UnpackFile(fileToUnzip,destination);
        unpackFile.decompress();

    }
}
