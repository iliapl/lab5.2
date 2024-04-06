package FileDo;

import java.io.BufferedReader;
import java.io.IOException;

public class Console {
    private EmployeeComand employeeComand;
    private BufferedReader bufferedReader;

    public Console(EmployeeComand employeeComand, BufferedReader bufferedReader) {
        this.employeeComand = employeeComand;
        this.bufferedReader = bufferedReader;
    }

    public void start() {
        System.out.println("Здраствуйте. Программа готова к работе");
        String inputLine;
        boolean needExit = false;
        while (!needExit) {
            System.out.println("Введите комманду");
            try {
                inputLine = bufferedReader.readLine();
            } catch (IOException e) {
                break;
            }
            needExit = employeeComand.executeCommand(inputLine);
        }
    }
}
