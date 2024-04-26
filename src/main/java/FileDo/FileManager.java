package FileDo;

import toVehicle.Vehicle;
import util.FileRead;
import util.WriteFileToXML;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashSet;

public class FileManager {

    FileRead fileRead;
    WriteFileToXML writeFileToXML;

    public FileManager(FileRead fileRead, WriteFileToXML writeFileToXML) {
        this.fileRead = fileRead;
        this.writeFileToXML = writeFileToXML;

    }

    public HashSet<Vehicle> saveVehicles() throws IOException {
            return fileRead.parserXML();
    }



}
