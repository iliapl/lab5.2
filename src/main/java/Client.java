import FileDo.Consoler;
import FileDo.EmployeeCollection;
import FileDo.EmployeeComand;
import FileDo.FileManager;
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
    public static void main(String[] args){
        System.out.println("получилось?");
        System.out.println("педро-педро-педро");
        System.out.println("гитхаб 3ае6ал в квадрате");
try{
    EnvDoing e = new EnvDoing();
    Scanner scanner = new Scanner(System.in);
    File file = new File(e.getPATHcollection());
    FileInputStream fin = new FileInputStream(file);
    BufferedInputStream bufferedReader = new BufferedInputStream(fin);
    FileRead reader = new FileRead(bufferedReader, scanner, file);
    PrintWriter printWriter = new PrintWriter(file, StandardCharsets.UTF_8);
    WriteFileToXML writeFileToXML = new WriteFileToXML(printWriter);
    FileManager fileManager =new FileManager(reader, writeFileToXML);
    EmployeeCollection employeeCollection = new EmployeeCollection(fileManager.saveVehicles());
    EmployeeComand employeeComand = new EmployeeComand(fileManager, reader, employeeCollection);
    Consoler console = new Consoler(employeeComand,scanner);
    console.start();

} catch (FileNotFoundException e) {
    throw new RuntimeException(e);
} catch (IOException e) {
    throw new RuntimeException(e);
    /*} catch (JAXBException e) {
    throw new RuntimeException(e);

     */
}


    }
}
