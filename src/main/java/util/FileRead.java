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
    private Scanner scannerforbuffer;
    private Scanner scannerhowscipp;
    BufferedInputStream bufferedReaderin;
    public  FileRead(BufferedInputStream bufferedReader, Scanner scanner) {
        this.bufferedReaderin = bufferedReader;
        this.scanner = scanner;
        this.scannerforbuffer = new Scanner(bufferedReader);
        this.scannerhowscipp = new Scanner(bufferedReader);
        scannerforbuffer.useDelimiter("<");
        scannerhowscipp.useDelimiter(">");
    }
    private int howScippInt(){
        return scannerhowscipp.nextLine().length() + 1;
    }
    Vehicle vehicle = null;

    public Vehicle readVehiclefromFile() throws IOException {

        if(bufferedReaderin.available() != 0) {
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
        else{
            return null;
        }

    }


    /*
    далее будут числа от балды, потом возьмём реально скипнутые байты
    кста скипаем на байт +2
     */

    public long readId() throws IOException {
        bufferedReaderin.readNBytes(30);
        bufferedReaderin.readNBytes(howScippInt());//+2 перейти на новую строчку
        //return Long.parseLong(String.valueOf(bufferedReaderin.read()));
        long readID = scannerforbuffer.nextLong();
        bufferedReaderin.readNBytes(String.valueOf(readID).length() + howScippInt() + 2);
        return readID;
    }
    public String readName() throws IOException {
        bufferedReaderin.readNBytes(howScippInt());
        String name = scannerforbuffer.nextLine();
        bufferedReaderin.readNBytes(name.length() + howScippInt() + 2);
        return name;
    }
    public FuelType readFuelType() throws IOException {
        bufferedReaderin.readNBytes(howScippInt());
        FuelType fuelType = FuelType.valueOf(scannerforbuffer.nextLine());
        bufferedReaderin.readNBytes(String.valueOf(fuelType).length() + howScippInt() + 2);
        return fuelType;
    }
    public double readeEnginePower() throws IOException {
        bufferedReaderin.readNBytes(howScippInt());
        double enginePower = scannerforbuffer.nextDouble();
        bufferedReaderin.readNBytes( String.valueOf(enginePower).length() + howScippInt() + 2);
        return enginePower;
    }
    public VehicleType readVehicleType() throws IOException {
        bufferedReaderin.readNBytes(howScippInt());
        VehicleType vehicleType = VehicleType.valueOf(scannerforbuffer.nextLine());
        bufferedReaderin.readNBytes(String.valueOf(vehicleType).length() + howScippInt() + 2);
        return  vehicleType;
    }
    public Coordinates readCoordinates() throws IOException {
        bufferedReaderin.readNBytes(howScippInt());
        long x = scannerforbuffer.nextLong();
        bufferedReaderin.readNBytes( String.valueOf(x).length() + howScippInt() + 2);
        bufferedReaderin.readNBytes(howScippInt());
        Float y = scannerforbuffer.nextFloat();
        bufferedReaderin.readNBytes(String.valueOf(y).length() + howScippInt() + 2);
        return new Coordinates(x,y);
    }
    public java.time.LocalDate readTime() throws IOException {
        bufferedReaderin.readNBytes(howScippInt());
        java.time.LocalDate time = LocalDate.ofEpochDay(scannerforbuffer.nextLong());
        bufferedReaderin.readNBytes(String.valueOf(time).length() + howScippInt() + 2);
        return time;
    }
    public boolean canRead() throws IOException {
        bufferedReaderin.readNBytes(howScippInt());
        bufferedReaderin.readNBytes(22);
        if(bufferedReaderin.available() != 0){
            return true;
        }
        else{
            return false;
        }
    }
    public Vehicle readVehicleFromConsole(){
        return new Vehicle(readNamefromConsole(),readCordinatesFromConsole(), readEnginePowerFromConsole(), readVehicleTypeFromConsole(), readFuelTypeFromConsole() );
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
