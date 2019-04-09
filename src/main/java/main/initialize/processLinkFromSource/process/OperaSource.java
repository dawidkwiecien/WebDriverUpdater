package main.initialize.processLinkFromSource.process;

import main.initialize.processLinkFromSource.process.interfaces.BrowserSource;
import main.initialize.processLinkFromSource.resultClasses.BaseLink;
import main.initialize.processLinkFromSource.resultClasses.OperaLinkToDrivers;
import main.initialize.sourceUtils.TransformFileToJsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OperaSource implements BrowserSource {
    @Override
    public List<BaseLink> getLinks(String link, File downloaded) {
        TransformFileToJsonArray json = new TransformFileToJsonArray(downloaded);
        List<BaseLink> linkToDrivers = new ArrayList<>();
        JSONArray jsonArray = json.getArray();
        jsonArray.forEach(p -> {
            JSONObject element = (JSONObject) p;
            JSONArray assets = (JSONArray) element.get("assets");
            assets.forEach(e -> {
                JSONObject asset = (JSONObject) e;
                linkToDrivers.add(new OperaLinkToDrivers((asset.get("browser_download_url").toString()), (asset.get("name").toString())));
            });

        });
        return linkToDrivers;
    }
}
