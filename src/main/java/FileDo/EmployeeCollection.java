package FileDo;

import toVehicle.Vehicle;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class EmployeeCollection {
        private final Set<Vehicle> vehicles;
        private final java.time.ZonedDateTime creationDate;
        public EmployeeCollection(HashSet<Vehicle> vehicles) {
            if(vehicles != null) {
                this.vehicles = new HashSet<>(vehicles);
                this.vehicles.addAll(vehicles);
                creationDate = java.time.ZonedDateTime.now();
            }
            else{
                this.vehicles = new HashSet<>();
                creationDate = java.time.ZonedDateTime.now();
            }
        }

        public NavigableSet<Vehicle> getCollection() {
            return new TreeSet<>(vehicles);
        }
        public boolean add(Vehicle vehicle) {
            return vehicles.add(vehicle);
        }
        public void updateId(long id, Vehicle vehicle) {
            for (Vehicle setVehicles : vehicles) {
                if (setVehicles.getId() == (id)) {
                    setVehicles.update(vehicle);
                }
            }
        }
        public void clear() {
            vehicles.clear();
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
            if (setVehicles.getId()==(id)) {
                setVehicles.update(vehicle);
            }
        }
    }
    public boolean existElementWithId(long id) {
        for (Vehicle setVehicle : vehicles) {
            if (setVehicle.getId()==(id)) {
                return true;
            }
        }
        return false;
    }
}