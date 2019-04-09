package main.initialize.processLinkFromSource.resultClasses;

public class OperaLinkToDrivers extends BaseLink {
    public OperaLinkToDrivers(String link,String name) {
        setLink(link);
        setOs((name.split("_")[1]).split("\\.")[0]);
        setVersion(link.split("/")[7]);
    }
}
