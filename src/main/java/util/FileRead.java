package util;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import toVehicle.Coordinates;
import toVehicle.FuelType;
import toVehicle.Vehicle;
import toVehicle.VehicleType;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class FileRead {
    private Scanner scanner;
    private int position = 0;
    BufferedInputStream bufferedReaderin;
    public  FileRead(BufferedInputStream bufferedReader, Scanner scanner) {
        this.bufferedReaderin = bufferedReader;
        this.scanner = scanner;
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
    public Vehicle readVehicleFromConsole(){
        return new Vehicle(readNamefromConsole(),);
    }
    public String readNamefromConsole(){
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        if(name == null|| name.isEmpty()){
            System.out.println("Вы не ввели имя");
            return readNamefromConsole();
        }
        else{
            return name;
        }
    }
    public Coordinates readCordinatesFromConsole(){
        Coordinates coordinates;
        System.out.println("Введите координату х");
        long x = scanner.nextLong();
        System.out.println("Введите координату, у должна быть < 597");
        Float y = scanner.nextFloat();
        while(y == 0 || y>597) {
            System.out.println("Вы не ввели координату, повторите попытку, y должна быть < 597");
            y = scanner.nextFloat();
        }
        return  new Coordinates(x, y);
    }
    public double readEnginePowerFromConsole(){
        double enginePower;
        System.out.println("Введите значение enginePower");
        enginePower = scanner.nextDouble();
        if(enginePower <= 0 ){
            System.out.println("значение enginePower должно быть польше нуля, повторите попытку");
            return readEnginePowerFromConsole();
        }
        else {
            return enginePower;
        }
    }
    public VehicleType readVehicleTypeFromConsole(){
        System.out.println("Тип машины и её номер");
        int nomber = 0;
        VehicleType[] types = new VehicleType[VehicleType.values().length];
        for(VehicleType vehicleType : VehicleType.values()){
            types[nomber] = vehicleType;
            System.out.println(nomber + " " + vehicleType);
            nomber++;
        }
        System.out.println("напишите номер варианта");
        int inputnomber = scanner.nextInt();
        if (inputnomber > nomber || inputnomber <= 0){
            System.out.println("Вы ввели несуществующий номер, повторите попытку");
            System.out.println("Напоминаем");
           return readVehicleTypeFromConsole();
        }
        else{
            return types[inputnomber];
        }
    }
    public FuelType readFuelTypeFromConsole(){
        System.out.println("Тип машины и её номер");
        int nomber = 0;
        FuelType[] types = new FuelType[VehicleType.values().length];
        for(FuelType fuelType : FuelType.values()){
            types[nomber] = fuelType;
            System.out.println(nomber + " " + fuelType);
            nomber++;
        }
        System.out.println("напишите номер варианта");
        int inputnomber = scanner.nextInt();
        if (inputnomber > nomber || inputnomber <= 0){
            System.out.println("Вы ввели несуществующий номер, повторите попытку");
            System.out.println("Напоминаем");
            return readFuelTypeFromConsole();
        }
        else{
            return types[inputnomber];
        }
    }

}
