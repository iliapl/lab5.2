package FileDo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Consoler {
    private Scanner scanner;
    private EmployeeComand employeeComand;
    private boolean isRunning;

    public Consoler(EmployeeComand employeeComand, Scanner scanner) {
        this.employeeComand = employeeComand;
        this.scanner = scanner;
        this.isRunning = true;
    }

    public void start() {
        while (isRunning) {
            System.out.println("Здраствуйте. Программа готова к работе");
            String inputLine;
            boolean needExit = false;
            while (!needExit) {
                System.out.println("Введите комманду");
                inputLine = scanner.nextLine();
                needExit = employeeComand.executeCommand(inputLine);
            }
        }
    }

    public void exit() {
        System.out.println("Программа завершена");
        isRunning = false;
    }
}
