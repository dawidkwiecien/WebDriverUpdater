package main.utils;

import main.initialize.processLinkFromSource.process.*;
import main.initialize.processLinkFromSource.process.interfaces.BrowserSource;

public enum BrowserTypes {
    CHROME(new ChromeSource()),
    FIREFOX(new FirefoxSource()),
    OPERA(new OperaSource());

    private final BrowserSource browserSource;

    BrowserTypes(BrowserSource browserSource) {
        this.browserSource = browserSource;
    }

    public BrowserSource getBrowserSource() {
        return browserSource;
    }


}
