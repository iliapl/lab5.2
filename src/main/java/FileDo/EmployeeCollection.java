package FileDo;

import toVehicle.Vehicle;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class EmployeeCollection {
    private final Set<Vehicle> vehicles;
    private final java.time.ZonedDateTime creationDate;
    public EmployeeCollection(Set<Vehicle> vehicles) {
        creationDate = java.time.ZonedDateTime.now();
        if (vehicles == null) {
            this.vehicles = new HashSet<>();
        } else {
            this.vehicles = vehicles;
            this.vehicles.addAll(vehicles);
        }
    }

    public Set<Vehicle> getVehicles() {
        return this.vehicles;
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
}