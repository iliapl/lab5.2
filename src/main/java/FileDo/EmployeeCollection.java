package FileDo;

import toVehicle.FuelType;
import toVehicle.Vehicle;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeCollection {
    private final Set<Vehicle> vehicles;
    private final java.time.ZonedDateTime creationDate;

    public EmployeeCollection(HashSet<Vehicle> vehicles) {
        if (vehicles != null) {
            this.vehicles = vehicles;
            this.vehicles.addAll(vehicles);
            creationDate = java.time.ZonedDateTime.now();
        } else {
            this.vehicles = null;
            creationDate = java.time.ZonedDateTime.now();
        }
    }

    public NavigableSet<Vehicle> getCollection() {
        return new TreeSet<>(vehicles);
    }

    public boolean add(Vehicle vehicle) {
        return vehicles.add(vehicle);
    }

    public void clear() {
        if (vehicles.isEmpty()) {
            System.out.println("Коллекция пуста, поэтому очистить можно только твои грехи");
        } else {
            vehicles.clear();
        }
    }

    public void addIfMin(Vehicle vehicle) {
        Optional<Vehicle> minVehicle = vehicles.stream().min(Comparator.comparing(Vehicle::getEnginePower));
        if (minVehicle.isPresent() && vehicle.getEnginePower() < minVehicle.get().getEnginePower()) {
            boolean success = vehicles.add(vehicle);
            if (success) {
                System.out.println("Элемент успешно добавлен в коллекцию.");
            } else {
                System.out.println("Ошибка при добавлении элемента. Возможно, такой элемент уже существует.");
            }
        } else {
            System.out.println("Элемент не добавлен. Новый элемент должен иметь значение enginePower меньше, чем у наименьшего элемента в коллекции.");
        }
    }

    public void removeGreater(Vehicle vehicle) {
        vehicles.removeIf(setVehicle -> setVehicle.getName().compareTo(vehicle.getName()) > 0);
        System.out.println("Все элементы, превышающие заданный, удалены из коллекции");
    }

    public String getCollectionName() {
        return vehicles.getClass().toString();
    }

    public int getSize() {
        return vehicles.size();
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void updateById(long id, Vehicle vehicle) {
        for (Vehicle setVehicles : vehicles) {
            if (setVehicles.getId() == (id)) {
                setVehicles.update(vehicle);
            }
        }
    }

    public void removeById(long id) {
        Iterator<Vehicle> iterator = vehicles.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            if (vehicle.getId() == id) {
                iterator.remove();
                found = true;
                System.out.println("Vehicle с данным ID " + id + " успешно удален.");
                break;
            }
        }
        if (!found) {
            System.out.println("Vehicle c данным " + id + " не найден.");
        }
    }

    public boolean existElementWithId(long id) {
        for (Vehicle setVehicle : vehicles) {
            if (setVehicle.getId() == (id)) {
                return true;
            }
        }
        return false;
    }

    public void sumOfEnginePower() {
        int sum = vehicles.stream().mapToInt(Vehicle::getEnginePower).sum();
        System.out.println("Сумма значений enginePower для всех элементов коллекции: " + sum);
    }

    public void averageOfEnginePower() {
        OptionalDouble average = vehicles.stream().mapToInt(Vehicle::getEnginePower).average();
        if (average.isPresent()) {
            System.out.println("Среднее значение enginePower для всех элементов коллекции: " + average.getAsDouble());
        } else {
            System.out.println("Коллекция пуста, нет данных для расчета среднего.");
        }
    }

    public void printUniqueFuelType() {
        Set<FuelType> uniqueFuelTypes = vehicles.stream()
                .map(Vehicle::getFuelType)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (uniqueFuelTypes.isEmpty()) {
            System.out.println("Нет уникальных значений поля fuelType в коллекции.");
        } else {
            System.out.println("Уникальные значения fuelType:");
            for (FuelType fuelType : uniqueFuelTypes) {
                System.out.println("- " + fuelType);
            }
        }
    }
}