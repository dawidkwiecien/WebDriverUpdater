import main.repos.ChromeDriverRepo;
import org.testng.annotations.Test;

public class TestRepoBuilder {
    @Test
    public void buildChrome() {
        ChromeDriverRepo chromeDriverRepo = ChromeDriverRepo.builder()
                .driverLink("http://www.link.pl")
                .driverName("CHROME")
                .driverVersion("23.23")
                .build();

        assert (chromeDriverRepo != null);
        assert(chromeDriverRepo.getDriverLink().equalsIgnoreCase(("http://www.link.pl")));
        assert(chromeDriverRepo.getDriverName().equalsIgnoreCase("CHROME"));
        assert(chromeDriverRepo.getDriverVersion().equalsIgnoreCase("23.23"));

    }
}
