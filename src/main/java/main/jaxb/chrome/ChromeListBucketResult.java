package main.jaxb.chrome;

import lombok.Data;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@XmlRootElement(name = "ListBucketResult", namespace = "http://doc.s3.amazonaws.com/2006-03-01")
@XmlAccessorType(value = FIELD)
@Data
public class ChromeListBucketResult {
    @XmlElement(name = "Name", namespace = "http://doc.s3.amazonaws.com/2006-03-01")
    String name;
    @XmlElement(name = "Contents", namespace = "http://doc.s3.amazonaws.com/2006-03-01")
    List<Contents> contents= new ArrayList<>();

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "Contents",namespace = "http://doc.s3.amazonaws.com/2006-03-01")
    public static  class Contents {
        @XmlElement(name = "Key", namespace = "http://doc.s3.amazonaws.com/2006-03-01")
        String Key;
    }


}
