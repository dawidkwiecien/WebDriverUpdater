package main.Mojo;

import main.initialize.InitSource;
import main.links.BaseLink;
import main.utils.BrowserTypes;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Mojo( name = "driverVersions", requiresOnline = true)
public class DriverVersion extends AbstractMojo
{

    @Parameter( property = "driverVersions.browser" )
    private BrowserTypes browser;

    public void execute() {
        InitSource source = new InitSource("/home/dawid/IdeaProjects/WebDriverUpdater/xmls",this.browser);
        List<BaseLink> links = new ArrayList<>();
        try {
            links=source.getRepo();
        } catch (IOException | JAXBException e) {
            getLog().error(e);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        links.forEach(p-> getLog().info(p.toString()));
    }
}
