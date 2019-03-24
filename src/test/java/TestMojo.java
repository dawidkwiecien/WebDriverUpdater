import main.Mojo.GetDriver;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.testng.annotations.Test;

public class TestMojo {
    @Test
    public void testMojo() throws MojoFailureException, MojoExecutionException {
        GetDriver getDriver = new GetDriver();
        getDriver.execute();
    }
}
