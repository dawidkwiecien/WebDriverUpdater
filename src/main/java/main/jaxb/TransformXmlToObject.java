package main.jaxb;

import javax.xml.bind.*;
import java.io.File;

public class TransformXmlToObject {

    public static <T> T transform(Class<T> clasa, File input)  {
        T result = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clasa);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            result=(T)jaxbUnmarshaller.unmarshal(input);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
}
