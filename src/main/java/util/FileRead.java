package util;

import toVehicle.Coordinates;
import toVehicle.FuelType;
import toVehicle.Vehicle;
import toVehicle.VehicleType;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class FileRead {
    int position = 0;
    File file;
    BufferedInputStream bufferedReaderin;
    public  FileRead(BufferedInputStream bufferedReader, File file) {
        this.bufferedReaderin = bufferedReader;
        this.file = file;
    }
    Vehicle vehicle = null;

    public Vehicle readVehiclefromFile() throws IOException {
            long id = readId();
            String name = readName();
            Coordinates coordinates = readCoordinates();
            java.time.LocalDate date = readTime();
            double power = readeEnginePower();
            VehicleType vtype = readVehicleType();
            FuelType fueltype = readFuelType();
            bufferedReaderin.read();
            bufferedReaderin.read();
        return new Vehicle(name, coordinates, power, vtype, fueltype);
    }
    /*
    далее будут числа от балды, потом возьмём реально скипнутые байты
    кста скипаем на байт +2
     */
    public long readId() throws IOException {
        position = position +10;
        bufferedReaderin.skip(10);//+2 чтобы перейти на новую строчку
        return Long.parseLong(String.valueOf(bufferedReaderin.read()));
    }
    public String readName() throws IOException {
        position = position + 13;
        bufferedReaderin.skip(position);
        return String.valueOf(bufferedReaderin.read());
    }
    public FuelType readFuelType() throws IOException {
        position = position + 12;
        bufferedReaderin.skip(position);
        return FuelType.valueOf(String.valueOf(bufferedReaderin.read()));
    }
    public double readeEnginePower() throws IOException {
        position = position + 15;
        bufferedReaderin.skip(position);
        return Double.parseDouble(String.valueOf(bufferedReaderin.read()));
    }
    public VehicleType readVehicleType() throws IOException {
        position = position + 12;
        bufferedReaderin.skip(position);
        return  VehicleType.valueOf(String.valueOf(bufferedReaderin.read()));
    }
    public Coordinates readCoordinates() throws IOException {
        position = position + 11;
        bufferedReaderin.skip(position);
        long x = Long.parseLong(String.valueOf(bufferedReaderin.read()));
        position = position + 11;
        bufferedReaderin.skip(position);
        Float y = Float.parseFloat(String.valueOf(bufferedReaderin.read()));
        return new Coordinates(x,y);
    }
    public java.time.LocalDate readTime() throws IOException {
        position = position + 11;
        bufferedReaderin.skip(position);
        java.time.LocalDate time = LocalDate.ofEpochDay(bufferedReaderin.read());
        return time;
    }
}
