package FileDo;

import toVehicle.Vehicle;

import java.time.ZonedDateTime;
import java.util.*;

public class EmployeeCollection {
    private final Set<Vehicle> vehicles;
    private final java.time.ZonedDateTime creationDate;

    public EmployeeCollection(HashSet<Vehicle> vehicles) {
        this.vehicles = new HashSet<>(vehicles);
        this.vehicles.addAll(vehicles);
        creationDate = java.time.ZonedDateTime.now();
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

    public void removeGreater(Vehicle vehicle) {
        vehicles.removeIf(setVehicle -> setVehicle.getName().compareTo(vehicle.getName()) > 0);
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
}