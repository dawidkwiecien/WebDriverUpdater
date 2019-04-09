package main.mavenMojo;

import main.initialize.processLinkFromSource.resultClasses.BaseLink;
import main.utils.BrowserTypes;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.List;

@Mojo(name = "driverVersions", requiresOnline = true)
class DriverVersion extends AbstractMojo {
    @Parameter(property = "driverVersions.source", required = true, readonly = true)
    private BrowserTypes browser;

    public void execute() {
        List<BaseLink> links = MojoBase.getLinks(this.browser);
        links.forEach(p -> getLog().info(p.toString()));
    }
}
