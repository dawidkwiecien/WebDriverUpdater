package main.links;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class BaseLink {
    String version;
    String os;
    String link;
}
