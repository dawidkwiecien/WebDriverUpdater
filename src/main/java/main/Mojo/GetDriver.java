package main.Mojo;

import main.download.FileDownloader;
import main.initialize.InitSource;
import main.unpack.UnzipFile;
import main.utils.BrowserTypes;
import main.links.ChromeLinkToDrivers;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
        InitSource source = new InitSource("/home/dawid/IdeaProjects/WebDriverUpdater/xmls", this.browser.name());
        List<ChromeLinkToDrivers> links = new ArrayList<>();
        try {
            links = source.getRepo();
        } catch (IOException | JAXBException e) {
            getLog().error(e);
        }
        Optional<ChromeLinkToDrivers> driver = links.stream().filter(p ->
                p.getOs().equalsIgnoreCase(os)
                        &&
                        p.getVersion().equalsIgnoreCase(version)
        ).findFirst();
        if (!driver.isPresent()) throw new IllegalArgumentException(browser.name() + " " + version + " " + os);
        File tempFile = null;
        try {
            tempFile = Files.createTempDirectory("myFile").toFile();
        } catch (IOException e) {
            getLog().error(e);
        }

        tempFile.deleteOnExit();

        FileDownloader fileDownloader = new FileDownloader(driver.get().getLink(), tempFile);
        File downloadedArchiver = null;
        try {
            downloadedArchiver = fileDownloader.download();
        } catch (IOException e) {
            getLog().error(e);
        }
        UnzipFile unzipFile = new UnzipFile(downloadedArchiver, new File(destinationPath));
        try {
            unzipFile.unzip();
        } catch (IOException e) {
            getLog().error(e);
        }
    }

}

