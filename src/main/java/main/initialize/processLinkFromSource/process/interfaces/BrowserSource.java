package main.initialize.processLinkFromSource.process.interfaces;

import main.initialize.processLinkFromSource.resultClasses.BaseLink;

import java.io.File;
import java.util.List;

public interface BrowserSource {
     List<BaseLink> getLinks(String link, File downloaded);
}
