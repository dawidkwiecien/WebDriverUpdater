import main.initialize.InitSource;
import main.utils.ChromeLinkToDrivers;
import main.utils.TransformXmlToObject;
import main.utils.chrome.jaxb.ChromeListBucketResult;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestRepos {
    @Test
    public void getRepo() throws IOException, JAXBException {
        InitSource source = new InitSource("/home/dawid/IdeaProjects/WebDriverUpdater/xmls","CHROME");
        List<ChromeLinkToDrivers> links=source.getRepo();
        System.out.println();
    }

}
