import FileDo.*;
import util.EnvDoing;
import util.FileRead;
import util.WriteFileToXML;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
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
            console.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}