import FileDo.Consoler;
import FileDo.EmployeeCollection;
import FileDo.EmployeeComand;
import FileDo.FileManager;
import org.w3c.dom.Document;
import toVehicle.Vehicle;
import util.EnvDoing;
import util.FileRead;


import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        System.out.println("получилось?");
try{
    Scanner scanner = new Scanner(System.in);
    EnvDoing e = new EnvDoing();
    File file = new File(e.getPATHcollection());
    Vehicle vehicle = null;
    FileInputStream fin = new FileInputStream(file);
    BufferedInputStream bufferedReader = new BufferedInputStream(fin);
    FileRead reader = new FileRead(bufferedReader,scanner);
    FileManager fileManager =new FileManager(file, reader);
    EmployeeCollection employeeCollection = new EmployeeCollection(fileManager.saveVehicles());
    EmployeeComand employeeComand = new EmployeeComand(fileManager, reader, employeeCollection);
    Consoler console = new Consoler(employeeComand,scanner);
} catch (FileNotFoundException e) {
    throw new RuntimeException(e);
} catch (IOException e) {
    throw new RuntimeException(e);
}
    }
}
