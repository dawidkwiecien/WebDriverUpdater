
import main.initialize.sourceUtils.TransformFileToJsonArray;
import org.json.simple.JSONArray;
import org.testng.annotations.Test;
import java.io.File;

public class TestJSONParser {

    @Test(enabled = true)
    public void testJSON() {
        File file = new File("src/test/resources/releases.json");
        TransformFileToJsonArray json = new TransformFileToJsonArray(file);
        JSONArray jsonArray=json.getArray();
        assert jsonArray.size()==30;
    }
}
