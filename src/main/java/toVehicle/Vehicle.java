package toVehicle;

import java.io.*;
import java.util.*;
import java.util.Objects;
import java.io.Serializable;
public class Vehicle implements Serializable{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double enginePower; //Значение поля должно быть больше 0
    private VehicleType type; //Поле может быть null
    private FuelType fuelType; //Поле может быть null
    private static long nextid =0;
    public  Vehicle(String name, Coordinates coordinates, double enginePower, VehicleType type, FuelType fuelType){
        id=nextid++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.LocalDate.now();
        this.enginePower = enginePower;
        this.type = type;
        this.fuelType = fuelType;
    }
    @Override
    public int hashCode(){
        return (int)id;
    }
    @Override
    public boolean equals(Object obj){
            if(obj == this) return true;
            if(obj == null || obj.getClass() != this.getClass()) return false;
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
    public static void setNextId(Long id) {
        nextid = id;
    }
    public void update(Vehicle vehicle) {
        name = vehicle.name;
        coordinates = vehicle.coordinates;
        type = vehicle.type;
        fuelType = vehicle.fuelType;
    }
}
