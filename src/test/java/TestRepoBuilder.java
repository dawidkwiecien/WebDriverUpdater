import main.initialize.localRepo.DriverRepo;
import org.testng.annotations.Test;

public class TestRepoBuilder {
    @Test
    public void buildChrome() {
        DriverRepo repo = new DriverRepo();
        repo.setDriverLink("http://www.link.pl");
        repo.setDriverName("CHROME");

        assert(repo.getDriverLink().equalsIgnoreCase(("http://www.link.pl")));
        assert(repo.getDriverName().equalsIgnoreCase("CHROME"));

    }

    @Test
    public void buildFirefox() {
        DriverRepo repo = new DriverRepo();
        repo.setDriverLink("http://www.link.pl");
        repo.setDriverName("FIREFOX");

        assert(repo.getDriverLink().equalsIgnoreCase(("http://www.link.pl")));
        assert(repo.getDriverName().equalsIgnoreCase("FIREFOX"));

    }

    @Test
    public void buildOpera() {
        DriverRepo repo = new DriverRepo();
        repo.setDriverLink("http://www.link.pl");
        repo.setDriverName("OPERA");

        assert(repo.getDriverLink().equalsIgnoreCase(("http://www.link.pl")));
        assert(repo.getDriverName().equalsIgnoreCase("OPERA"));

    }
}
