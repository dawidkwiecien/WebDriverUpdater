import main.repos.DriverRepo;
import org.testng.annotations.Test;

public class TestRepoBuilder {
    @Test
    public void buildChrome() {
        DriverRepo repo = new DriverRepo();
        repo.setDriverLink("http://www.link.pl");
        repo.setDriverName("CHROME");

        assert (repo != null);
        assert(repo.getDriverLink().equalsIgnoreCase(("http://www.link.pl")));
        assert(repo.getDriverName().equalsIgnoreCase("CHROME"));

    }
}
