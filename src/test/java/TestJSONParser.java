

import main.json.GetJsonObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestJSONParser {
    @Test
    public void testJSON() throws IOException, ParseException {
        File file = new File("/tmp/chromeRepos5823313387082634109xml");
        GetJsonObject json = new GetJsonObject(file);
        json.getObject();
            System.out.println();


    }
}
