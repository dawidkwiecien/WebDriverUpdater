package main.initialize.processLinkFromSource.resultClasses;


public class FirefoxLinkToDrivers extends BaseLink {
    public FirefoxLinkToDrivers(String link,String name) {
        setLink(link);
        setOs((name.split("-")[2]).split("\\.")[0]);
        setVersion(name.split("-")[1]);

    }
}
