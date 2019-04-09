package main.initialize;

import main.fileUtils.FileDownloader;
import main.initialize.processLinkFromSource.process.interfaces.BrowserSource;
import main.utils.TransformXmlToObject;
import main.initialize.processLinkFromSource.resultClasses.BaseLink;
import main.initialize.localRepo.DriverRepo;
import main.utils.BrowserTypes;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static main.utils.ExtensionsOperations.removeExtension;

public class InitSource {
    private final String path;
    private final BrowserTypes driverName;

    public InitSource(String path, BrowserTypes driverName) {
        this.path = path;
        this.driverName = driverName;
    }

    public List<BaseLink> getRepo() throws IOException {
        File repoFile = repoSourceXML();
        DriverRepo repo = TransformXmlToObject.transform(DriverRepo.class, repoFile);
        String link = repo.getDriverLink();

        FileDownloader fileDownloader = new FileDownloader(link);
        File downloaded = fileDownloader.download();
        BrowserSource browserSource=driverName.getBrowserSource();

        return browserSource.getLinks(link, downloaded);

    }

    private File repoSourceXML() throws IOException {
        List<File> filesInFolder = Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .filter(p -> removeExtension(p.toFile().getName()).equalsIgnoreCase(driverName.toString()))
                .map(Path::toFile)
                .collect(Collectors.toList());
        return filesInFolder.stream().findFirst().get();
    }


}
