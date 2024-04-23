package util;

public class EnvDoing {
    public String getPATHcollection() {
        System.out.println(System.getenv("VehicleCollection"));
        return System.getenv("VehicleCollection");
    }

    public String getPATHhCommandHistory() {
        return System.getenv("CommandHistory");
    }
}
