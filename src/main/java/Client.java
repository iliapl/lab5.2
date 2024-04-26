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
    public static void main(String[] args){
        // я заменил значение игрика в coordinates на float, должен быть Float
try{
    EnvDoing e = new EnvDoing();
    Scanner scanner = new Scanner(System.in);
    /*
    CreateFile createFile = new CreateFile();
    createFile.create();

     */
    File file = new File("C:\\Users\\plysc\\IdeaProjects\\file\\collection.xml");
    FileInputStream fin = new FileInputStream("C:\\Users\\plysc\\IdeaProjects\\file\\collection.xml");
    BufferedInputStream bufferedReader = new BufferedInputStream(fin);
    FileRead reader = new FileRead(bufferedReader, scanner,file);
    //ща станет 0
    FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\plysc\\IdeaProjects\\file\\operationFile.xml", true);
    PrintWriter printWriter = new PrintWriter(fileOutputStream);
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
