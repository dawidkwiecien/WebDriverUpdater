package main.links;

import main.utils.ExtensionsOperations;

public class FirefoxLinkToDrivers extends BaseLink {
    public FirefoxLinkToDrivers(String link,String name) {
        setLink(link);
        link.split("/");
        setOs((name.split("-")[2]).split("\\.")[0]);
        setVersion(name.split("-")[1]);

    }
}
