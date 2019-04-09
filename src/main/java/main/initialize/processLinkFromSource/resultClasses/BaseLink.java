package main.initialize.processLinkFromSource.resultClasses;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class BaseLink {
    protected String version;
    protected String os;
    protected String link;
}
