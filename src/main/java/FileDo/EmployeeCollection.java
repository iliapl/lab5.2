package FileDo;

import toVehicle.FuelType;
import toVehicle.Vehicle;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeCollection {
    private final Set<Vehicle> vehicles;
    private final java.time.ZonedDateTime creationDate;

    public EmployeeCollection(Set<Vehicle> vehicles) {
        if (vehicles != null) {
            this.vehicles = vehicles;
            this.vehicles.addAll(vehicles);
            creationDate = java.time.ZonedDateTime.now();
        } else {
            this.vehicles = null;
            creationDate = java.time.ZonedDateTime.now();
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