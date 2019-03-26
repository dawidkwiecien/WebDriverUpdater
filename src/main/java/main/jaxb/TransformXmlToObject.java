package main.jaxb;

import javax.xml.bind.*;
import java.io.File;

public class TransformXmlToObject {

    public static Object transform(Class clasa, File input) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clasa);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (clasa.cast(jaxbUnmarshaller.unmarshal(input))) ;
    }
}
