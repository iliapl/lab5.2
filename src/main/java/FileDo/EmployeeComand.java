package FileDo;

import toVehicle.Vehicle;
import util.FileRead;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;

public class EmployeeComand {
    private final Set<String> scriptNames;
    private final EmployeeCollection employeeCollection;
    private final FileRead reader;
    private final FileManager fileManager;
    private final Method[] methods;
    private boolean isScriptExecuting;

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
        System.out.println("updateid: Обновляет значение элемента коллекции, id которого равен заданному");
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
        Set<Vehicle> vehicles = employeeCollection.getVehicles();
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("Нет vehicles в коллекции");
        } else {
            System.out.println("Vehicles в коллекции");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle.vehicleToString());
            }
        }
    }

    public void add() {
        Vehicle newVehicle = reader.readVehicleFromConsole();
        employeeCollection.getVehicles().add(newVehicle);
        System.out.println("Добавлен новый транспорт: " + newVehicle.getName());
    }

    public void updateID() {
        System.out.println("Обновление элемента коллекции по ID.");
        int id = reader.readIDFromConsole();
        // Находим элемент по `id`
        Vehicle existingVehicle = employeeCollection.getVehicles().stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);

        if (existingVehicle == null) {
            System.out.println("Элемент с ID " + id + " не найден в коллекции.");
            return;
        }

        // Считываем новые данные для обновления
        Vehicle updatedVehicle = reader.readVehicleFromConsole();
        if (updatedVehicle == null) {
            System.out.println("Ошибка при считывании новых данных для обновления.");
            return;
        }
        // Обновляем существующий элемент новыми данными
        existingVehicle.update(updatedVehicle);
        System.out.println("Элемент с ID " + id + " был успешно обновлен.");
    }

    public void removeById(String argument) {
        if (argument == null || argument.trim().isEmpty()) {
            System.out.println("ID нет у пустого элемента.");
            return;
        }
        try {
            int id = Integer.parseInt(argument.trim());
            Set<Vehicle> vehicles = employeeCollection.getVehicles();

            // Найдите элемент по ID
            Vehicle toRemove = null;
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getId() == id) {
                    toRemove = vehicle;
                    break;
                }
            }
            // Удалите, если найден
            if (toRemove != null) {
                vehicles.remove(toRemove);
                System.out.println("Vehicle c ID " + id + " удален");
            } else {
                System.out.println("Vehicle с ID " + id + " не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неправильный формат ID");
        }
    }
    public void save() {

        fileManager.writeFileToXML(fileManager.fileRead.vehiclesCollecton);
        System.out.println("Коллекция успешно сохранена в файл с расширением XML.");
        }

    public void clear() {
        if (employeeCollection.getVehicles() == null) {
            System.out.println("Коллекция уже пуста.");
            return;
        }
        employeeCollection.getVehicles().clear(); // Очистить коллекцию
        System.out.println("Коллекция успешно очищена.");
    }

    public void executeScriptFileName() {
    } //пока не могу реализовать

    public void exit() {
        System.out.println("Завершение программы...");
        System.exit(0);
    }

    public void add_if_min(Vehicle newVehicle) {
        if (newVehicle == null) {
            throw new IllegalArgumentException("Нельзя добавить пустой vehicle в коллекцию");
        }

        Set<Vehicle> vehicles = employeeCollection.getVehicles();

        if (vehicles == null || vehicles.isEmpty()) {
            vehicles.add(newVehicle);
            System.out.println("Vehicle добавленр: " + newVehicle.vehicleToString());
            return;
        }

        // Найдите минимальный элемент по критерию
        Vehicle minVehicle = Collections.min(vehicles, Comparator.comparing(Vehicle::getId));

        if (newVehicle.getId() < minVehicle.getId()) {
            vehicles.add(newVehicle); // Добавить, если новый элемент меньше
            System.out.println("Vehicle added: " + newVehicle.vehicleToString());
        } else {
            System.out.println("New vehicle is not smaller than the minimum.");
        }
    }

    //inputCommandToJavaStyle позволяет динамически находить и вызывать методы по имени,
    // даже если команда вводится в стиле, отличном от используемого в коде.
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

    //при помощи связки inputCommandToJavaStyle и executeCommand происходит нахождение метода с названием команды
    public boolean executeCommand(String inputLine) {
        if (inputLine == null || inputLine.trim().isEmpty()) {
            System.out.println("Введите команду"); // Выводится, если строка пустая
            return false; // Возвращение для предотвращения дальнейших действий
        }

        String[] line = inputLine.trim().split(" ", 2); // Разделение строки на команду и аргумент
        String command = inputCommandToJavaStyle(line[0].toLowerCase()); // Преобразование к camelCase

        try {
            // Если команда "exit", завершите выполнение программы
            if ("exit".equalsIgnoreCase(command)) {
                exit();
                return true;
            }

            Method methodToInvoke = null;

            // Поиск метода, соответствующего названию команды
            for (Method method : methods) {
                if (method.getName().equalsIgnoreCase(command)) {
                    methodToInvoke = method;
                    break;
                }
            }

            if (methodToInvoke == null) {
                System.out.println("Такой команды не существует: " + command);
                return false; // Завершить выполнение, если метод не найден
            }

            // Если метод без аргументов, вызываем его
            if (methodToInvoke.getParameterCount() == 0) {
                methodToInvoke.invoke(this);
            } else if (line.length > 1) { // Если метод принимает аргументы, но нет аргументов
                String argument = line[1].trim(); // Убедитесь, что аргумент корректен
                if (argument.isEmpty()) {
                    System.out.println("Не хватает аргументов для команды: " + command);
                    return false;
                }
                methodToInvoke.invoke(this, argument); // Вызов метода с аргументом
            } else {
                System.out.println("Не хватает аргументов для команды: " + command); // Если аргумент отсутствует
            }

        } catch (InvocationTargetException e) {
            System.out.println("Ошибка при вызове метода: " + e.getCause().getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}