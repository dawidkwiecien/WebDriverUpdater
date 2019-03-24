package main.Mojo;

import main.initialize.InitSource;
import main.utils.BrowserTypes;
import main.utils.ChromeLinkToDrivers;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Mojo( name = "driverVersions")
public class GreetingMojo extends AbstractMojo
{

    @Parameter( property = "driverVersions.browser" )
    private BrowserTypes browser;

    public void execute() throws MojoExecutionException
    {
        InitSource source = new InitSource("/home/dawid/IdeaProjects/WebDriverUpdater/xmls",this.browser.name());
        List<ChromeLinkToDrivers> links = new ArrayList<>();
        try {
            links=source.getRepo();
        } catch (IOException e) {
            getLog().error(e);
        } catch (JAXBException e) {
            getLog().error(e);
        }
        links.forEach(p->{
            getLog().info(p.toString());
        });
        //getLog().info( this.browser.name() );
    }
}
