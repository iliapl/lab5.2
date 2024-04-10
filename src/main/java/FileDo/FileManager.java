package FileDo;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import toVehicle.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.apache.commons.text.lookup.DefaultStringLookup.XML;

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
    public List<Vehicle> readElementsFromFile() throws IOException {
        StringBuilder inputArray = new StringBuilder();
        for (String string : fileSave()) {
            inputArray.append(string);
        }
        XmlMapper xmlMapper = new XmlMapper();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        xmlMapper.writeValue(byteArrayOutputStream, file);
        assertEquals(XML, byteArrayOutputStream.toString());
        return new ArrayList<>(Arrays.asList());
    }
}
