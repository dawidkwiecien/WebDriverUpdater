package main.initialize;

import main.download.FileDownloader;
import main.links.BaseLink;
import main.repos.DriverRepo;
import main.links.ChromeLinkToDrivers;
import main.jaxb.TransformXmlToObject;
import main.jaxb.chrome.ChromeListBucketResult;
import main.utils.BrowserTypes;

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

    public InitSource(String path,BrowserTypes driverName) {
        this.path = path;
        this.driverName = driverName;
    }

    public List<BaseLink> getRepo() throws IOException, JAXBException {


        File repoFile=repoSourceXML();
        DriverRepo repo= (DriverRepo) TransformXmlToObject.transform(DriverRepo.class,repoFile);
        String link=repo.getDriverLink();
        File temp=File.createTempFile("chromeRepos","xml");
        temp.deleteOnExit();
        FileDownloader fileDownloader = new FileDownloader(link,temp);
        File downloaded=fileDownloader.download();

        ChromeListBucketResult chrome= (ChromeListBucketResult) TransformXmlToObject.transform(ChromeListBucketResult.class,downloaded);
        chrome.setContents(chrome.getContents().stream().filter(p->p.getKey().endsWith(".zip")).collect(Collectors.toList()));
        List<Object> linkToDrivers = new ArrayList<>();
        chrome.getContents().forEach(p-> linkToDrivers.add(new ChromeLinkToDrivers(p.getKey(),link)));

        switch (driverName) {
            case CHROME:
                return chromeLinks(link,downloaded);
            case FIREFOX:
                break;
        }
return null;

    }

    private File repoSourceXML() throws IOException {
        List<File> filesInFolder = Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .filter(p-> removeExtension(p.toFile().getName()).equalsIgnoreCase(driverName.toString()))
                .map(Path::toFile)
                .collect(Collectors.toList());
        File file=filesInFolder.stream().findFirst().get();
        return file;
    }

    private List<BaseLink> chromeLinks(String link, File downloaded) throws JAXBException {
        ChromeListBucketResult chrome= (ChromeListBucketResult) TransformXmlToObject.transform(ChromeListBucketResult.class,downloaded);
        chrome.setContents(chrome.getContents().stream().filter(p->p.getKey().endsWith(".zip")).collect(Collectors.toList()));
        List<BaseLink> linkToDrivers = new ArrayList<>();
        chrome.getContents().forEach(p-> linkToDrivers.add(new ChromeLinkToDrivers(p.getKey(),link)));
        return linkToDrivers;
    }


}
