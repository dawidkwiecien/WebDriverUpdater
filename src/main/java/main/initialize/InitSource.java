package main.initialize;

import main.download.FileDownloader;
import main.repos.DriverRepo;
import main.utils.ChromeLinkToDrivers;
import main.utils.TransformXmlToObject;
import main.utils.chrome.jaxb.ChromeListBucketResult;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InitSource {
    private final String path;
    private final String driverName;

    public InitSource(String path,String driverName) {
        this.path = path;
        this.driverName = driverName;
    }

    public List<ChromeLinkToDrivers> getRepo() throws IOException, JAXBException {

        List<File> filesInFolder = Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .filter(p-> removeExtension(p.toFile().getName()).equalsIgnoreCase(driverName))
                .map(Path::toFile)
                .collect(Collectors.toList());
        File file=filesInFolder.stream().findFirst().get();
        DriverRepo repo= (DriverRepo) TransformXmlToObject.transform(DriverRepo.class,file);
        String link=repo.getDriverLink();
        File temp=File.createTempFile("chromeRepos","xml");
        temp.deleteOnExit();
        FileDownloader fileDownloader = new FileDownloader(link,temp);
        File downloaded=fileDownloader.download();
        ChromeListBucketResult chrome= (ChromeListBucketResult) TransformXmlToObject.transform(ChromeListBucketResult.class,downloaded);
        chrome.setContents(chrome.getContents().stream().filter(p->p.getKey().endsWith(".zip")).collect(Collectors.toList()));
        List<ChromeLinkToDrivers> linkToDrivers = new ArrayList<>();
        chrome.getContents().forEach(p-> linkToDrivers.add(new ChromeLinkToDrivers(p.getKey(),link)));

        return linkToDrivers;

    }
    private static String removeExtension(String filename) {
        if (filename == null) {
            return null;
        }

        int index = indexOfExtension(filename);

        if (index == -1) {
            return filename;
        } else {
            return filename.substring(0, index);
        }
    }

    private static int indexOfExtension(String filename) {

        if (filename == null) {
            return -1;
        }
        int extensionPos = filename.lastIndexOf(".");

        int lastDirSeparator = filename.lastIndexOf(System.getProperty("file.separator"));

        if (lastDirSeparator > extensionPos) {
            return -1;
        }

        return extensionPos;
    }

}
