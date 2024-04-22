package util;

public class EnvDoing {
    public String getPATHcollection(){
        System.out.println("vnjewhvioud   "  + System.getenv("VARIABLE_NAME") );
        return System.getenv("VehicleCollection");
    }
    public String getPATHhCommandHistory(){return System.getenv("CommandHistory");}
}
