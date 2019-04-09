package main.mavenMojo;

import main.fileUtils.FileDownloader;
import main.initialize.processLinkFromSource.resultClasses.BaseLink;
import main.fileUtils.UnpackFile;
import main.utils.BrowserTypes;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import java.io.File;
import java.util.List;
import java.util.Optional;

@Mojo(name = "driverUpdate",requiresOnline = true)
class GetDriver extends AbstractMojo {
    @Parameter(property = "driverUpdate.source",required = true,readonly = true)
    private BrowserTypes browser;
    @Parameter(property = "driverUpdate.version",required = true,readonly = true)
    private String version;
    @Parameter(property = "driverUpdate.os",required = true,readonly = true)
    private String os;
    @Parameter(property = "driverUpdate.destinationPath",required = true,readonly = true)
    private String destinationPath;

    @Override
    public void execute() {
        List<BaseLink> links = MojoBase.getLinks(this.browser);

        BaseLink driver=getDriver(links);

        FileDownloader fileDownloader = new FileDownloader(driver.getLink());
        File downloadedArchiver = fileDownloader.download();
        UnpackFile unpackFile = new UnpackFile(downloadedArchiver, new File(destinationPath));
        unpackFile.decompress();
    }

    private BaseLink getDriver(List<BaseLink> links){
        Optional<BaseLink> driver = links.stream().filter(p ->
                p.getOs().equalsIgnoreCase(os)
                        &&
                        p.getVersion().equalsIgnoreCase(version)
        ).findFirst();
        if (!driver.isPresent()) throw new IllegalArgumentException(browser.name() + " " + version + " ");
        else return driver.get();
    }

}

