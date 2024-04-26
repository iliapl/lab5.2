package FileDo;

import java.io.*;

public class CreateFile {

    File operationFile = new File("/Users/ahahac_be3_xboctuka/Desktop/lab5-3.2-master/collection.xml");

    public void create() {
        String sourcePath = "Users/ahahac_be3_xboctuka/Desktop/lab5-3.2-master/collection.xml";
        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists()) {
            System.out.println("Source file does not exist: " + sourceFile.getAbsolutePath());
            return;
        }

        try (FileInputStream inputstream = new FileInputStream(sourceFile);
             FileOutputStream outputStream = new FileOutputStream(operationFile, true)) {
            int i;
            while ((i = inputstream.read()) != -1) {
                outputStream.write(i);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public File getOperationFile() {
        return operationFile;
    }

    public String getPATHoperationFile() {
        return operationFile.getAbsolutePath();
    }
}