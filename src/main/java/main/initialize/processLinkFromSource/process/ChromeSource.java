package main.initialize.processLinkFromSource.process;

import main.initialize.processLinkFromSource.process.interfaces.BrowserSource;
import main.initialize.sourceUtils.TransformXmlToObject;
import main.initialize.results.ChromeListBucketResult;
import main.initialize.processLinkFromSource.resultClasses.BaseLink;
import main.initialize.processLinkFromSource.resultClasses.ChromeLinkToDrivers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChromeSource implements BrowserSource {
    @Override
    public List<BaseLink> getLinks(String link, File downloaded) {
        ChromeListBucketResult chrome =  TransformXmlToObject.transform(ChromeListBucketResult.class, downloaded);
        chrome.setContents(chrome.getContents().stream().filter(p -> p.getKey().endsWith(".zip")).collect(Collectors.toList()));
        List<BaseLink> linkToDrivers = new ArrayList<>();
        chrome.getContents().forEach(p -> {
            BaseLink baseLink = new ChromeLinkToDrivers(p.getKey(), link);
            linkToDrivers.add(baseLink);
        });
        return linkToDrivers;
    }
}
