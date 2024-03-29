import util.EnvDoing;
import util.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Client {
    public static void main(String[] args){
try{
    EnvDoing e = new EnvDoing();
    File file = new File(e.getPATHcollection());
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
    Reader reader = new Reader(bufferedReader);
} catch (FileNotFoundException e) {
    throw new RuntimeException(e);
}
    }
}
