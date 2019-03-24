import main.initialize.InitSource;
import main.utils.ChromeLinkToDrivers;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class TestRepos {
    @Test
    public void getRepo() throws IOException, JAXBException {
        InitSource source = new InitSource("/home/dawid/IdeaProjects/WebDriverUpdater/xmls","CHROME");
        List<ChromeLinkToDrivers> links=source.getRepo();
    }

}
