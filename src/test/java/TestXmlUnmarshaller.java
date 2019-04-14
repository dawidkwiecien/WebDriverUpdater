import main.initialize.localRepo.DriverRepo;
import main.initialize.sourceUtils.TransformXmlToObject;
import org.testng.annotations.Test;

import java.io.File;

@SuppressWarnings("ALL")
public class TestXmlUnmarshaller {
    @Test
    public void testUnmarshallChrome() {
        Class klasa = DriverRepo.class;
        File plik=new File("xmls/chrome.xml");
        DriverRepo repo= (DriverRepo) TransformXmlToObject.transform(klasa,plik);
        assert (repo != null);
        assert(repo.getDriverLink().equalsIgnoreCase(("https://chromedriver.storage.googleapis.com/")));
        assert(repo.getDriverName().equalsIgnoreCase("CHROME"));
    }

    @Test
    public void testUnmarshallFirefox() {
        Class klasa = DriverRepo.class;
        File plik=new File("xmls/firefox.xml");
        DriverRepo repo= (DriverRepo) TransformXmlToObject.transform(klasa,plik);
        assert (repo != null);
        assert(repo.getDriverLink().equalsIgnoreCase(("https://api.github.com/repos/mozilla/geckodriver/releases")));
        assert(repo.getDriverName().equalsIgnoreCase("FIREFOX"));
    }
}
