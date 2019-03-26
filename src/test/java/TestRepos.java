import main.initialize.InitSource;
import main.links.ChromeLinkToDrivers;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class TestRepos {
    @Test
    public void chrome() throws IOException, JAXBException {
        InitSource source = new InitSource("/home/dawid/IdeaProjects/WebDriverUpdater/xmls","CHROME");
        List<ChromeLinkToDrivers> links=source.getRepo();
    }

    @Test
    public void firefox() throws IOException, JAXBException {
        InitSource source = new InitSource("/home/dawid/IdeaProjects/WebDriverUpdater/xmls","CHROME");
        List<ChromeLinkToDrivers> links=source.getRepo();
    }

}
