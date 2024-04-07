import FileDo.EmployeeCollection;
import FileDo.EmployeeComand;
import FileDo.FileManager;
import org.w3c.dom.Document;
import toVehicle.Vehicle;
import util.EnvDoing;
import util.Reader;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

public class Client {
    public static void main(String[] args){
try{
    EnvDoing e = new EnvDoing();
    File file = new File(e.getPATHcollection());
    Vehicle vehicle = null;
    FileInputStream fin = new FileInputStream(file);
    BufferedInputStream bufferedReader = new BufferedInputStream(fin);
    FileReader reader = new FileReader(String.valueOf(bufferedReader));
    FileManager fileManager =new FileManager(file);
    EmployeeCollection employeeCollection = new EmployeeCollection(fileManager.);
    EmployeeComand employeeComand = new EmployeeComand();
} catch (FileNotFoundException e) {
    throw new RuntimeException(e);
}
    }
}
