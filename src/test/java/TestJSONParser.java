

import main.utils.TransformFileToJsonArray;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestJSONParser {
    @Test(enabled = false)
    public void testJSON() throws IOException, ParseException {
        File file = new File("/tmp/chromeRepos5823313387082634109xml");
        TransformFileToJsonArray json = new TransformFileToJsonArray(file);
        json.getArray();
    }
}
