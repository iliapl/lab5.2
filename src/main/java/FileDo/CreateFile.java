package FileDo;

import java.io.*;

public class CreateFile {

    File operationFile = new File("/Users/ahahac_be3_xboctuka/Desktop/lab5-3.2-master/collection.xml");

    public void create() {
        try {
            operationFile = new File("C:\\Users\\plysc\\IdeaProjects\\file\\operationFile.xml");
            FileInputStream inputstream = new FileInputStream("C:\\Users\\plysc\\IdeaProjects\\file\\collection.xml");
            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\plysc\\IdeaProjects\\file\\operationFile.xml", true);
            int i;
            while((i=inputstream.read())!= -1){
                outputStream.write((char)i);
            }
            outputStream.close();
            inputstream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public File getOperationFile() {
        return operationFile;
    }

    public String getPATHoperationFile() {
        return "C:\\Users\\plysc\\IdeaProjects\\file\\operationFile.xml";
    }
    public String getPATHFile() {
        return "C:\\Users\\plysc\\IdeaProjects\\file\\collection.xml";
    }

}