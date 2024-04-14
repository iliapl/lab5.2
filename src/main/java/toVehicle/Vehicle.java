package toVehicle;

import java.io.Serializable;
import java.time.LocalDate;

public class Vehicle implements Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer enginePower; //Значение поля должно быть больше 0
    private VehicleType type; //Поле может быть null
    private FuelType fuelType; //Поле может быть null
    private static int nextid = 0;

    public Vehicle(String name, Coordinates coordinates, Integer enginePower, VehicleType type, FuelType fuelType) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        if (enginePower == null || enginePower <= 0) {
            throw new IllegalArgumentException("Engine power must be greater than 0 and not null");
        }
        if (type == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }
        if (fuelType == null) {
            throw new IllegalArgumentException("Fuel type cannot be null");
        }
        id = nextid++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.LocalDate.now();
        this.enginePower = enginePower;
        this.type = type;
        this.fuelType = fuelType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return (vehicle.id == this.id);
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public static void setNextId(Integer id) {
        nextid = id;
    }

    public void update(Vehicle vehicle) {
        name = vehicle.name;
        coordinates = vehicle.coordinates;
        type = vehicle.type;
        fuelType = vehicle.fuelType;
    }
}