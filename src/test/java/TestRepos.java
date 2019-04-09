import main.initialize.InitSource;
import main.initialize.processLinkFromSource.resultClasses.BaseLink;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static main.utils.BrowserTypes.*;

public class TestRepos {
    @Test
    public void chrome() throws IOException {
        InitSource source = new InitSource("/home/dawid/IdeaProjects/WebDriverUpdater/xmls",CHROME);
        List<BaseLink> links=source.getRepo();
        assert(links.size()>0);
    }

    @Test()
    public void firefox() throws IOException {
        InitSource source = new InitSource("/home/dawid/IdeaProjects/WebDriverUpdater/xmls",FIREFOX);
        List<BaseLink> links=source.getRepo();
        assert(links.size()>0);
    }

}
