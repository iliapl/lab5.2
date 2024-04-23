package FileDo;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private List<String> commandHistory;

    public CommandHistory() {
        this.commandHistory = new ArrayList<>();
    }

    public void addCommand(String command) {
        commandHistory.add(command);
        if (commandHistory.size() > 7) {
            commandHistory.remove(0);
        }
    }

    public void printHistory() {
        if (commandHistory.isEmpty()) {
            System.out.println("История команд пуста.");
        } else {
            System.out.println("Последние 7 команд:");
            for (int i = commandHistory.size() - 1; i >= 0; i--) {
                System.out.println("- " + commandHistory.get(i));
            }
        }
    }
}
