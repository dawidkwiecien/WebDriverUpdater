package main.utils;

public class ChromeLinkToDrivers {
    String version;
    String os;
    String link;

    public ChromeLinkToDrivers(String conent, String link) {
        String[] splitedBySlash=conent.split("/");
        System.out.println();
        this.version=splitedBySlash[0];
        this.os=splitedBySlash[1].split("_")[1];
        this.os=this.os.substring(0,this.os.length()-4);
        this.link=link+conent;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
