package main.json;

import main.links.BaseLink;
import main.links.FirefoxLinkToDrivers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GetJsonObject {
    File jsonFile;

    public GetJsonObject(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    public List<BaseLink> getObject() throws IOException, ParseException {
        FileReader fileReader = new FileReader(jsonFile);
        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(fileReader);
        List<BaseLink> mapa = new ArrayList<>();
        json.forEach(p->{
            JSONObject element = (JSONObject)p;
            JSONArray assets= (JSONArray) element.get("assets");
            assets.forEach(e->{
                JSONObject asset = (JSONObject)e;
                mapa.add(new FirefoxLinkToDrivers((asset.get("browser_download_url").toString()),(asset.get("name").toString())));
            });

            System.out.println();
        });
        return mapa;
    }
}
