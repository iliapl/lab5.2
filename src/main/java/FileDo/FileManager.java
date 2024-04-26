package FileDo;

import toVehicle.Vehicle;
import toVehicle.VehiclesCollecton;
import util.EnvDoing;
import util.FileRead;
import util.WriteFileToXML;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;

public class FileManager {
    EnvDoing envDoing = new EnvDoing();
    FileRead fileRead;
    WriteFileToXML writeFileToXML;
    CreateFile createFile;
    public FileManager(FileRead fileRead, WriteFileToXML writeFileToXML, CreateFile createFile) {
        this.fileRead = fileRead;
        this.writeFileToXML = writeFileToXML;
        this.createFile = createFile;
    }

    public HashSet<Vehicle> saveVehicles() throws IOException {
        return fileRead.parserXML();
    }
    public void writeFileToXML(VehiclesCollecton vehiclesCollecton){
        try {
            Files.newBufferedWriter(Path.of(createFile.getPATHoperationFile()) , StandardOpenOption.TRUNCATE_EXISTING);
            createFile.create();
            Files.newBufferedWriter(Path.of(createFile.getPATHFile()) , StandardOpenOption.TRUNCATE_EXISTING);
            writeFileToXML.toSaveToXML();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
