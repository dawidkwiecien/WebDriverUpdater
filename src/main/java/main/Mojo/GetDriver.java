package main.Mojo;

import main.download.FileDownloader;
import main.initialize.InitSource;
import main.links.BaseLink;
import main.decompressFile.UnpackFile;
import main.utils.BrowserTypes;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mojo(name = "driverUpdate",requiresOnline = true)
public class GetDriver extends AbstractMojo {
    @Parameter(property = "driverUpdate.browser")
    BrowserTypes browser;
    @Parameter(property = "driverUpdate.version")
    String version;
    @Parameter(property = "driverUpdate.os")
    String os;
    @Parameter(property = "driverUpdate.destinationPath")
    String destinationPath;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        InitSource source = new InitSource("/home/dawid/IdeaProjects/WebDriverUpdater/xmls", this.browser);
        List<BaseLink> links = new ArrayList<>();
        try {
            links = source.getRepo();
        } catch (IOException | JAXBException e) {
            getLog().error(e);
        }
        Optional<BaseLink> driver = links.stream().filter(p ->
                p.getOs().equalsIgnoreCase(os)
                        &&
                        p.getVersion().equalsIgnoreCase(version)
        ).findFirst();
        if (!driver.isPresent()) throw new IllegalArgumentException(browser.name() + " " + version + " " + os);

        FileDownloader fileDownloader = new FileDownloader(driver.get().getLink());
        File downloadedArchiver = null;
        try {
            downloadedArchiver = fileDownloader.download();
        } catch (IOException e) {
            getLog().error(e);
        }
        UnpackFile unpackFile = new UnpackFile(downloadedArchiver, new File(destinationPath));
        unpackFile.decompress();
    }

}

