package util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import toVehicle.Vehicle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.NavigableSet;

import static junit.framework.Assert.assertNotNull;

public class WriteFileToXML {
    private PrintWriter printWriter;

    public WriteFileToXML(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }
/*
    public void saveVehiclesToXML() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Vehicle.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal( printWriter);
    }

 */
}

