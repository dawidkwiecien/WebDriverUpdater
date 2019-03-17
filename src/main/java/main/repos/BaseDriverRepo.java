package main.repos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class BaseDriverRepo {
    protected String driverName;
    protected String driverVersion;
    protected String driverLink;
}
