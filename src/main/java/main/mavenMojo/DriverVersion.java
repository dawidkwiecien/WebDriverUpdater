package main.mavenMojo;

import main.initialize.InitSource;
import main.initialize.processLinkFromSource.resultClasses.BaseLink;
import main.utils.BrowserTypes;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Mojo( name = "driverVersions", requiresOnline = true)
public class DriverVersion extends AbstractMojo
{

    @Parameter( property = "driverVersions.source" )
    private BrowserTypes browser;

    public void execute() {
        InitSource source = new InitSource("/home/dawid/IdeaProjects/WebDriverUpdater/xmls",this.browser);
        List<BaseLink> links = new ArrayList<>();
        try {
            links=source.getRepo();
        } catch (IOException e) {
            getLog().error(e);
        }
        links.forEach(p-> getLog().info(p.toString()));
    }
}
