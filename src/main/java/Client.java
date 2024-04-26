import FileDo.*;
import org.w3c.dom.Document;
import toVehicle.Vehicle;
import util.EnvDoing;
import util.FileRead;
import util.WriteFileToXML;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            EnvDoing e = new EnvDoing(); // Environment configuration handling
            Scanner scanner = new Scanner(System.in); // Scanner for console input
            CreateFile createFile = new CreateFile(); // File creation instance
            createFile.create(); // Attempt to create and/or process the file

            // Using path to read from the file just handled
            FileInputStream fin = new FileInputStream(createFile.getPATHoperationFile());
            BufferedInputStream bufferedReader = new BufferedInputStream(fin);

            // Setup for reading file content
            FileRead reader = new FileRead(bufferedReader, scanner, createFile.getOperationFile());
            // Printer for writing content to the same file
            PrintWriter printWriter = new PrintWriter(createFile.getOperationFile(), StandardCharsets.UTF_8);
            WriteFileToXML writeFileToXML = new WriteFileToXML(printWriter);
            FileManager fileManager = new FileManager(reader, writeFileToXML);

            // Setup for managing employee collections
            EmployeeCollection employeeCollection = new EmployeeCollection(fileManager.saveVehicles());
            EmployeeComand employeeComand = new EmployeeComand(fileManager, reader, employeeCollection);
            Consoler console = new Consoler(employeeComand, scanner);

            // Start console interaction
            console.start();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}