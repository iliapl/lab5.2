package util;

import exceptions.ReadElementException;
import toVehicle.Coordinates;
import toVehicle.Vehicle;
import toVehicle.VehicleType;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
public class Reader {
    public String getPATHcollection(){
        return System.getenv("VehicleCollection");
    }
    private BufferedReader bufferedReader;
    public Reader(BufferedReader bufferedReader){
    this.bufferedReader=bufferedReader;
    }
    public void setReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }
    public BufferedReader getReader() {
        return bufferedReader;
    }
    public Vehicle readVehicleFromScript() {
        try {
            String vehicleName = bufferedReader.readLine();

            int coordinatesX = Integer.parseInt(bufferedReader.readLine());
            Double coordinatesY = Double.parseDouble(bufferedReader.readLine());

            int fromX = Integer.parseInt(bufferedReader.readLine());
            int fromY = Integer.parseInt(bufferedReader.readLine());
            double fromZ = Double.parseDouble(bufferedReader.readLine());
            String fromName = bufferedReader.readLine();

            int toX = Integer.parseInt(bufferedReader.readLine());
            int toY = Integer.parseInt(bufferedReader.readLine());
            double toZ = Double.parseDouble(bufferedReader.readLine());
            String toName = bufferedReader.readLine();

            Vehicle vehicle = new Vehicle(vehicleName, new Coordinates(coordinatesX, coordinatesY));
            return vehicle;
        } catch (Exception e) {
            throw new ReadElementException("Ошибка при чтении элемента. Проверьте правильность данных");
        }
    }
    public String readName() {
        System.out.print("Введите название маршрута: ");
        String name = readVehicleFromScript().getName();
        while (name == null || name.isEmpty()) {
            System.out.print("Название маршрута не может быть пустым, повторите попытку: ");
            name = readName();
        }
        return name;
    }
    public Coordinates readCoordinates() {
        final int xMaxValue = 412;
        long x;
        Float y;
        System.out.print("Введите координату X маршрута: ");
        x = readLong();
        while (x > xMaxValue) {
            System.out.print("Координата X не может быть больше 412, повторите попытку: ");
            x = readLong();
        }
        System.out.print("Введите координату Y маршрута: ");
        y = readFloat();
        return new Coordinates(x, y);
    }
    private int readInt() {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(bufferedReader.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Ошибка при вводе, повторите попытку: ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return value;
    }

    private long readLong() {
        long value;
        while (true) {
            try {
                value = Long.parseLong(bufferedReader.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Ошибка при вводе, повторите попытку: ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return value;
    }

    private double readDouble() {
        double value;
        while (true) {
            try {
                value = Double.parseDouble(bufferedReader.readLine());
                break;
            } catch (NumberFormatException | NullPointerException e) {//catch pofiks
                System.out.print("Ошибка при вводе, повторите попытку: ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return value;
    }
    public Float readFloat(){
        Float value;
        try {
            value = Float.parseFloat(bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
public VehicleType readVehicleType() {
    String string;
}
}
