package main.initialize.browser;

import main.initialize.BrowserSource;
import main.json.GetJsonObject;
import main.links.BaseLink;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirefoxSource implements BrowserSource {
    @Override
    public List<BaseLink> getLinks(String link, File downloaded) {
        GetJsonObject json = new GetJsonObject(downloaded);
        List<BaseLink> linkToDrivers = new ArrayList<>();
        try {
            linkToDrivers = json.getObject();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return linkToDrivers;
    }
}
