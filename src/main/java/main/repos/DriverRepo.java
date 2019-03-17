package main.repos;

import lombok.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name="repo")
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class DriverRepo {

    @XmlElement(name = "name")
    protected String driverName;
    @XmlElement(name = "url")
    protected String driverLink;
}
