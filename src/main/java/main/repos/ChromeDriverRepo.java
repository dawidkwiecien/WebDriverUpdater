package main.repos;

import lombok.*;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Builder
public class ChromeDriverRepo extends BaseDriverRepo {
}
