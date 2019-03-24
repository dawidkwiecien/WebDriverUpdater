package main.utils;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChromeLinkToDrivers {
    String version;
    String os;
    String link;

    public ChromeLinkToDrivers(String conent, String link) {
        String[] splitedBySlash=conent.split("/");
        this.version=splitedBySlash[0];
        this.os=splitedBySlash[1].split("_")[1];
        this.os=this.os.substring(0,this.os.length()-4);
        this.link=link+conent;
    }
}
