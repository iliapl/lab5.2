package util;

import toVehicle.Vehicle;
import toVehicle.VehiclesCollecton;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.PrintWriter;
import java.util.HashSet;

public class WriteFileToXML {
    private PrintWriter printWriter;
    public WriteFileToXML(PrintWriter printWriter){
        this.printWriter = printWriter;
    }
public void saveVehiclesToXML(VehiclesCollecton vehicles) throws JAXBException {
    JAXBContext jaxbContext = JAXBContext.newInstance(Vehicle.class);
    Marshaller marshaller = jaxbContext.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(vehicles, printWriter);
}
}
