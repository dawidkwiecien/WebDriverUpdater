package main.jaxb;

import javax.xml.bind.*;
import java.io.File;

public class TransformXmlToObject {

    public static <T> T transform(Class<T> clasa, File input) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clasa);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (T)jaxbUnmarshaller.unmarshal(input);
    }
}
