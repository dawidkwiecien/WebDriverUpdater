package main.utils;

import main.initialize.processLinkFromSource.process.ChromeSource;
import main.initialize.processLinkFromSource.process.FirefoxSource;
import main.initialize.processLinkFromSource.process.interfaces.BrowserSource;

public enum BrowserTypes {
    CHROME(new ChromeSource()),
    FIREFOX(new FirefoxSource());

    private final BrowserSource browserSource;

    BrowserTypes(BrowserSource browserSource) {
        this.browserSource = browserSource;
    }

    public BrowserSource getBrowserSource() {
        return browserSource;
    }


}
