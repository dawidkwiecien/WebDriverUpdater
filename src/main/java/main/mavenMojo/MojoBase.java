package main.mavenMojo;

import main.initialize.InitSource;
import main.initialize.processLinkFromSource.resultClasses.BaseLink;
import main.utils.BrowserTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

 class MojoBase  {
    static List<BaseLink> getLinks(BrowserTypes browser){
        InitSource source = new InitSource("/home/dawid/IdeaProjects/WebDriverUpdater/xmls",browser);
        List<BaseLink> links = new ArrayList<>();
        try {
            links=source.getRepo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;
    }
}
