package FileDo;

import util.EnvDoing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CreateFile {
    EnvDoing envDoing;

    public CreateFile(EnvDoing envDoing) {
        this.envDoing = envDoing;
    }

    FileInputStream inputstream;

    {
        try {
            inputstream = new FileInputStream(envDoing.getPATHcollection());
            File operationFile = new File("C:\\Users\\plysc\\IdeaProjects\\file\\operationFile.xml");
            if () {
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
