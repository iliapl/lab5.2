package FileDo;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import toVehicle.Vehicle;
import toVehicle.VehiclesCollecton;
import util.FileRead;
import util.WriteFileToXML;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.io.PrintWriter;

import static junit.framework.Assert.assertEquals;
import static org.apache.commons.text.lookup.DefaultStringLookup.XML;

public class FileManager {
    FileRead fileRead;
    HashSet<Vehicle> vehicles;
    VehiclesCollecton vehiclesCollecton;
    WriteFileToXML writeFileToXML;
    public FileManager(FileRead fileRead, WriteFileToXML writeFileToXML) {
        this.fileRead = fileRead;
        this.writeFileToXML = writeFileToXML;
    }
    public HashSet<Vehicle> saveVehicles() throws IOException {
        vehiclesCollecton.vehicleList = fileRead.parserXML();
        return fileRead.parserXML();
    }
    public void writeToXML() throws JAXBException {
        writeFileToXML.saveVehiclesToXML(vehiclesCollecton);
    }
    /*
    public void saveToXml() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        xmlMapper.writeValue(byteArrayOutputStream, vehicles);
        assertEquals(XML, byteArrayOutputStream.toString());
    }

  ////////////////////////////////////////////////////////////////

    public HashSet<String> fileSave() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        HashSet<String> list = new HashSet<>();
        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }
        return list;
    }
    public List<Vehicle> readElementsFromFile() throws IOException {
        StringBuilder inputArray = new StringBuilder();
        for (String string : fileSave()) {
            inputArray.append(string);
        }
        XmlMapper xmlMapper = new XmlMapper();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        xmlMapper.writeValue(byteArrayOutputStream, file);
        assertEquals(XML, byteArrayOutputStream.toString());
        return new ArrayList<>(Arrays.asList());
    }

 */
}
