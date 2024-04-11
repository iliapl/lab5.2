package FileDo;

import toVehicle.Vehicle;
import util.FileRead;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
public class EmployeeComand {
    private final Set<String> scriptNames;
    private final EmployeeCollection employeeCollection;
    private final FileRead reader;
    private final FileManager fileManager;
    private final Method[] methods;
    private boolean isScriptExecuting;
    private String history[] = new String[7];

    public EmployeeComand(FileManager fileManager, FileRead reader, EmployeeCollection employeeCollection) {
        this.fileManager = fileManager;
        this.reader = reader;
        this.employeeCollection = employeeCollection;
        this.methods = EmployeeComand.class.getMethods();
        this.isScriptExecuting = false;
        scriptNames = new HashSet<>();
    }

    public void help() {
        System.out.println("info: Выводит информацию о коллекции");
        System.out.println("show: Выводит все элементы коллекции");
        System.out.println("add: Добавляет элемент в коллекцию");
        System.out.println("updateID: Обновляет значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id: Удаляет элемент из коллекции по его id");
        System.out.println("clear: Очищает коллекцию");
        System.out.println("save: Сохраняет коллекцию в файл");
        System.out.println("execute_script: Считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь");
        System.out.println("add_if_min: Добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("remove_greater: Удаляет из коллекции все элементы, превышающие заданный");
        System.out.println("exit: Завершает выполнение программы без сохранения в файл");
        System.out.println("history: вывести последние 7 команд (без их аргументов)");
        System.out.println("sum_of_engine_power: вывести сумму значений поля enginePower для всех элементов коллекции");
        System.out.println("average_of_engine_power: вывести среднее значение поля enginePower для всех элементов коллекции");
        System.out.println("print_unique_fuel_type: вывести уникальные значения поля fuelType всех элементов в коллекции");
    }

    public void info() {
        System.out.println("Тип коллекции - " + employeeCollection.getCollectionName());
        System.out.println("Количество элементов - " + employeeCollection.getSize());
        System.out.println("Дата инициализации - " + employeeCollection.getCreationDate());
    }

    public void show() {
        for (Vehicle vehicle : employeeCollection.getCollection()) {
            System.out.println(vehicle);
        }
    }

    public void add() throws IOException {
        boolean success = employeeCollection.add(getVehicle());
        if (!success) {
            System.out.println("Ошибка при добавлении элемента. Возможно, такой элемент уже существует.");
        }
    }


    public HashSet<Vehicle> getVehicle() throws IOException {

        if (isScriptExecuting) {
            System.out.println("Попытка чтения элементов из скрипта");
            return reader.readVehiclefromFile();
        }
        return reader.readVehiclefromFile();

    }



    public void updateID(String argument) {
        try {
            long id = Long.parseLong(argument);
            if (employeeCollection.existElementWithId(id)) {
                employeeCollection.updateById(id, getVehicle());
                System.out.println("Элемент успешно обновлён.");
            } else {
                System.out.println("Элемента с таким id не существует.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка при вводе целого числа.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void history() {

    }

    private static String inputCommandToJavaStyle(String str) {
        StringBuilder result = new StringBuilder();
        boolean needUpperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '_') {
                if (needUpperCase) {
                    c = Character.toUpperCase(c);
                    needUpperCase = false;
                }
                result.append(c);
            } else {
                needUpperCase = true;
            }
        }
        return result.toString();
    }

    public boolean executeCommand(String inputLine) {
        String[] line = inputLine.trim().split(" ", 2);
        String command = inputCommandToJavaStyle(line[0].toLowerCase());
        if ("exit".equals(command)) {
            return true;
        }
        try {
            Method methodToInvoke = null;
            for (Method method : methods) {
                if (method.getName().equals(command)) {
                    methodToInvoke = method;
                    break;
                }
            }
            if (methodToInvoke == null) {
                throw new NoSuchMethodException();
            }
            if (line.length == 1) {
                methodToInvoke.invoke(this);
            } else {
                methodToInvoke.invoke(this, line[1]);
            }
        } catch (NoSuchMethodException | IllegalArgumentException e) {
            System.out.println("Такой команды не существует");
        } catch (InvocationTargetException e) {
            if (e.getCause().getClass().equals(NoSuchElementException.class)) {
                return true;
            }
            System.out.println(e.getCause().getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}

