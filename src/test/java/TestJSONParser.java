

import main.initialize.sourceUtils.TransformFileToJsonArray;
import org.testng.annotations.Test;

import java.io.File;

public class TestJSONParser {
    @Test(enabled = false)
    public void testJSON() {
        File file = new File("/tmp/chromeRepos5823313387082634109xml");
        TransformFileToJsonArray json = new TransformFileToJsonArray(file);
        json.getArray();
    }
}
