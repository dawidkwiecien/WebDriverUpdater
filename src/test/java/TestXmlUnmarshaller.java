import main.repos.DriverRepo;
import main.jaxb.TransformXmlToObject;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.File;

public class TestXmlUnmarshaller {
    @Test
    public void testUnmarshall() throws JAXBException {
        Class klasa = DriverRepo.class;
        File plik=new File("/home/dawid/IdeaProjects/WebDriverUpdater/xmls/chrome.xml");
        DriverRepo repo= (DriverRepo) TransformXmlToObject.transform(klasa,plik);
        assert (repo != null);
        assert(repo.getDriverLink().equalsIgnoreCase(("https://chromedriver.storage.googleapis.com/")));
        assert(repo.getDriverName().equalsIgnoreCase("CHROME"));
    }
}
