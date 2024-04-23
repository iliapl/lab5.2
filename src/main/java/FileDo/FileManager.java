package FileDo;

import toVehicle.Vehicle;
import toVehicle.VehiclesCollecton;
import util.FileRead;
import util.WriteFileToXML;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashSet;

public class FileManager {
    FileRead fileRead;
    VehiclesCollecton vehiclesCollecton;
    WriteFileToXML writeFileToXML;

    public FileManager(FileRead fileRead, WriteFileToXML writeFileToXML) {
        this.fileRead = fileRead;
        this.writeFileToXML = writeFileToXML;
    }

    public HashSet<Vehicle> saveVehicles() throws IOException {
        if (fileRead.parserXML() != null) {
            vehiclesCollecton.vehicleList = fileRead.parserXML();
            return fileRead.parserXML();
        } else {
            return null;
        }
    }

    public void writeToXML() throws JAXBException {
        writeFileToXML.saveVehiclesToXML(vehiclesCollecton);
    }
}
