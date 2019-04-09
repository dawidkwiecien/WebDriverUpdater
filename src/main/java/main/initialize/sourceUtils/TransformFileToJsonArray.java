package main.initialize.sourceUtils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TransformFileToJsonArray {
    private final File jsonFile;

    public TransformFileToJsonArray(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    public JSONArray getArray()  {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(jsonFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONParser parser = new JSONParser();
        try {
            return  (JSONArray) parser.parse(fileReader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
