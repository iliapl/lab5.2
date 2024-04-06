package FileDo;

import toVehicle.Vehicle;

import java.io.*;
import java.util.HashSet;
import java.util.List;

public class FileManager {
    private File file;

    public FileManager(File file) {
        this.file = file;
    }

    public HashSet<String> fileSave() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        HashSet<String> list = new HashSet<>();
        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }
        return list;
    }
    public List<StudyGroup> readElementsFromFile() throws FileNotFoundException {
        StringBuilder inputArray = new StringBuilder();
        for (String string : fileToStringList()) {
            inputArray.append(string);
        }

}
