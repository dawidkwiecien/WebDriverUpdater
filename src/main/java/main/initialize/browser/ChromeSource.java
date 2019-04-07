package main.initialize.browser;

import main.initialize.BrowserSource;
import main.jaxb.TransformXmlToObject;
import main.jaxb.chrome.ChromeListBucketResult;
import main.links.BaseLink;
import main.links.ChromeLinkToDrivers;

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
