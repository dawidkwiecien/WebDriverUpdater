package main.initialize;

import main.links.BaseLink;

import java.io.File;
import java.util.List;

public interface BrowserSource {
     List<BaseLink> getLinks(String link, File downloaded);
}
