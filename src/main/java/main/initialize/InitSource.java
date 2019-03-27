package main.initialize;

import main.download.FileDownloader;
import main.jaxb.TransformXmlToObject;
import main.jaxb.chrome.ChromeListBucketResult;
import main.json.GetJsonObject;
import main.links.BaseLink;
import main.links.ChromeLinkToDrivers;
import main.repos.DriverRepo;
import main.utils.BrowserTypes;
import org.json.simple.parser.ParseException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public List<BaseLink> getRepo() throws IOException, JAXBException {


        File repoFile = repoSourceXML();
        DriverRepo repo = (DriverRepo) TransformXmlToObject.transform(DriverRepo.class, repoFile);
        String link = repo.getDriverLink();
        Path tempDirWithPrefix = Files.createTempDirectory("tmp");

        FileDownloader fileDownloader = new FileDownloader(link, tempDirWithPrefix, true);
        File downloaded = fileDownloader.download();

        switch (driverName) {
            case CHROME:
                return chromeLinks(link, downloaded);
            case FIREFOX:
                return firefoxLinks(link, downloaded);
                default:return null;
        }

    }

    private File repoSourceXML() throws IOException {
        List<File> filesInFolder = Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .filter(p -> removeExtension(p.toFile().getName()).equalsIgnoreCase(driverName.toString()))
                .map(Path::toFile)
                .collect(Collectors.toList());
        File file = filesInFolder.stream().findFirst().get();
        return file;
    }

    private List<BaseLink> chromeLinks(String link, File downloaded) throws JAXBException {
        ChromeListBucketResult chrome = (ChromeListBucketResult) TransformXmlToObject.transform(ChromeListBucketResult.class, downloaded);
        chrome.setContents(chrome.getContents().stream().filter(p -> p.getKey().endsWith(".zip")).collect(Collectors.toList()));
        List<BaseLink> linkToDrivers = new ArrayList<>();
        chrome.getContents().forEach(p -> {
            BaseLink baseLink = new ChromeLinkToDrivers(p.getKey(), link);
            linkToDrivers.add(baseLink);
        });
        return linkToDrivers;
    }

    private List<BaseLink> firefoxLinks(String link, File downloaded) {
        GetJsonObject json = new GetJsonObject(downloaded);
        List<BaseLink> linkToDrivers = new ArrayList<>();
        try {
            linkToDrivers = json.getObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return linkToDrivers;
    }


}
