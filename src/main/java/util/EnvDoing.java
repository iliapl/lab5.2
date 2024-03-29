package util;

public class EnvDoing {
    public String getPATHcollection(){
        return System.getenv("VehicleCollection");
    }
    public String getPATHhCommandHistory(){return System.getenv("CommandHistory");}
}
