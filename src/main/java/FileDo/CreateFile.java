package FileDo;


import java.io.*;

public class CreateFile {

    public File operationFile;

    /*
    public void create() {
        try {
            FileInputStream inputstream;
            inputstream = new FileInputStream("C:/Users/plysc/IdeaProjects/file/collection.xml");
            operationFile = new File("C:/Users/plysc/IdeaProjects/file/operationFile.xml");
            if (operationFile.exists()) {
                operationFile.mkdir();
            }
            try (FileOutputStream outputStream = new FileOutputStream("C:/Users/plysc/IdeaProjects/file/operationFile.xml", true)) {
                byte[] bytes = new byte[inputstream.available()];
                int length;
                while ((length = inputstream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, length);
                }
            } catch (IOException e) {

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public File getOperationFile() {
        return operationFile;
    }

    public String getPATHoperationFile() {
        return operationFile.getAbsolutePath();
    }

     */
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
