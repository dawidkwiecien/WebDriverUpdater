package main.Mojo;

import main.utils.BrowserTypes;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo( name = "driverVersions")
public class GreetingMojo extends AbstractMojo
{

    @Parameter( property = "driverVersions.browser" )
    private BrowserTypes browser;

    public void execute() throws MojoExecutionException
    {
        getLog().info( this.browser.name() );
    }
}
