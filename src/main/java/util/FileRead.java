package util;

import FileDo.Filewas;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import toVehicle.*;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class FileRead {
    Document doc;
    private Filewas filewas;
    Scanner scanner;
    BufferedInputStream bufferedReaderin;
    private File file;
    HashSet<Vehicle> vehicles = new HashSet<>();

    public FileRead(BufferedInputStream bufferedReader, Scanner scanner, File file) {
        this.bufferedReaderin = bufferedReader;
        this.file = file;
        this.scanner = scanner;
        filewas = new Filewas();
    }

    public Document buildDocument() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        return docFactory.newDocumentBuilder().parse(bufferedReaderin);
    }

    public Node getFirstNode() {
        try {
            doc = buildDocument();
            return doc.getFirstChild();
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.out.println("Ошибка парсинша");
            return null;
        }

    }

    public NodeList getNodes() {
        return getFirstNode().getChildNodes();
    }

    public boolean canReadElements() {
        if (filewas.canReadFile(file)) {
            if (getFirstNode() != null) {
                return true;
            } else {
                System.out.println("Файл не содержит элементов");
                return false;
            }
        } else {
            return false;
        }
    }

    public HashSet<Vehicle> parserXML() {
        if (canReadElements()) {
            NodeList nodeList = getNodes();
            for (int i = 0; i < getNodes().getLength(); i++) {
                if (getNodes().item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                if (getNodes().item(i).equals("vehicle")) {
                    continue;
                }
                NodeList elements = nodeList.item(i).getChildNodes();
                for (int c = 0; c < elements.getLength(); c++) {

                    if (elements.item(c).getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    long id = 0;
                    String name = null;
                    Coordinates coordinates = null;
                    java.time.LocalDate creationDate = null;
                    int enginePower = 0;
                    VehicleType type = null;
                    FuelType fuelType = null;
                    switch (elements.item(c).getNodeName()) {
                        case "id": {
                            id = Long.parseLong(elements.item(c).getTextContent());
                            break;
                        }
                        case "name": {
                            name = elements.item(c).getTextContent();
                            break;
                        }
                        case "creationDate": {
                            creationDate = LocalDate.parse(elements.item(c).getTextContent());
                            break;
                        }
                        case "enginePower": {
                            enginePower = Integer.parseInt(elements.item(c).getTextContent());
                            break;
                        }
                        case "type": {
                            type = VehicleType.valueOf(elements.item(c).getTextContent());
                            break;
                        }
                        case "fuelType": {
                            fuelType = FuelType.valueOf(elements.item(c).getTextContent());
                            break;
                        }
                    }
                    if (elements.item(c).equals("coordinates")) {
                        NodeList nodeCoordinates = elements.item(i).getChildNodes();
                        long x = 0;
                        Float y = null;
                        for (int h = 0; h < nodeCoordinates.getLength(); h++) {
                            if (nodeCoordinates.item(h).getNodeType() != Node.ELEMENT_NODE) {
                                continue;
                            }
                            switch (nodeCoordinates.item(h).getNodeName()) {
                                case "x": {
                                    x = Long.parseLong(nodeCoordinates.item(h).getTextContent());
                                    break;
                                }
                                case "y": {
                                    y = Float.valueOf(nodeCoordinates.item(h).getTextContent());
                                    break;
                                }
                            }
                            coordinates = new Coordinates(x, y);
                        }
                    }
                    Vehicle vehicle = new Vehicle(name, coordinates, enginePower, type, fuelType);
                    vehicle.setId((int) id);
                    vehicle.setCreationDate(creationDate);
                    vehicles.add(vehicle);
                }
            }
        } else {
            return null;
        }
        return vehicles;
        /*в менеджере, если ретёрнится,
         то сказать что файл пуст, и сразу предложить ввести команду add
         */
    }

    /*
    public String parse() throws IOException {//фактическ парсю файл
        String string;
        char c;
        List<Character> charmass = new ArrayList<>();
        while (bufferedReaderin.available() > 0) {
            if ((char) bufferedReaderin.read() == '>') {
                while (bufferedReaderin.available() > 0 && (c = (char) bufferedReaderin.read()) != '<') {
                    charmass.add(c);
                }
            }
        }
        StringBuilder builder = new StringBuilder(charmass.size());
        for (Character ch : charmass) {
            String string1 = String.valueOf(ch);
            if(!string1.equals(" ")) {
                builder.append(ch);
            }
        }
        string = builder.toString();
        return string;
    }
    Vehicle vehicle = null;

    public HashSet<Vehicle> readVehiclefromFile() throws IOException {
        HashSet<Vehicle> vehicles = new HashSet<>();
        String[] strings = deliteEnters();
        int numVehicle = strings.length % 8;
        int num = numVehicle;
        for(int i = 1; i < num; i++){
            if (strings[i * 8] == null) {
                numVehicle = num - 1;
            }
        }
        for(int inter = 0;inter < numVehicle; inter++) {
            long id = Long.parseLong(strings[0 + inter * 8]);
            String name = strings[1 + inter * 8];
            long x = Long.parseLong(strings[2 + inter *8]);
            Float y = Float.valueOf(strings[3 + inter *8]);
            Coordinates coordinates = new Coordinates(x, y);
            java.time.LocalDate date = LocalDate.parse(strings[4+ inter *8]);
            double power = Double.parseDouble(strings[5+ inter *8]);
            VehicleType vtype = VehicleType.valueOf(strings[6+ inter *8]);
            FuelType fueltype = FuelType.valueOf(strings[7+ inter *8]);
            vehicles.add(new Vehicle(name, coordinates, power, vtype, fueltype));
        }
        bufferedReaderin.close();

        return  vehicles;
    }
    public String[] deliteEnters() throws IOException {
        String string = parse();
        String[] strings = new String[string.length()];
        int i = 0;
        while (scanner.hasNextLine()){
            String c;
            c = scanner.nextLine();
            if(!c.isEmpty()){
                strings[i] = c;
                i++;
                System.out.println(c);
            }
        }
        return strings;
    }

     */
    /*
    public long readId() throws IOException {
        bufferedReaderin.readNBytes(howScippInt() + 1);
        System.out.println(bufferedReaderin.read());
        bufferedReaderin.readNBytes(howScippInt() + 2);//+2 перейти на новую строчку
        //return Long.parseLong(String.valueOf(bufferedReaderin.read()));
        bufferedReaderin.readNBytes(howScippInt());
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
     */
    public boolean canRead() throws IOException {
        if (bufferedReaderin.available() != 0) {
            System.out.println("Файл может быть прочитан");
            return true;
        } else {
            System.out.println("Файл не может быть прочитан, количество байтов в файле" + bufferedReaderin.available());
            return false;
        }
    }

    public Vehicle readVehicleFromConsole() {
        return new Vehicle(readNamefromConsole(), readCordinatesFromConsole(), readEnginePowerFromConsole(), readVehicleTypeFromConsole(), readFuelTypeFromConsole());
    }

    public String readNamefromConsole() {
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        if (name == null || name.isEmpty()) {
            System.out.println("Вы не ввели имя");
            return readNamefromConsole();
        } else {
            return name;
        }
    }

    public Coordinates readCordinatesFromConsole() {
        Coordinates coordinates;
        System.out.println("Введите координату х");
        long x = scanner.nextLong();
        System.out.println("Введите координату, у должна быть < 597");
        Float y = scanner.nextFloat();
        while (y == 0 || y > 597) {
            System.out.println("Вы не ввели координату, повторите попытку, y должна быть < 597");
            y = scanner.nextFloat();
        }
        return new Coordinates(x, y);
    }

    public int readEnginePowerFromConsole() {
        int enginePower;
        System.out.println("Введите значение enginePower");
        enginePower = scanner.nextInt();
        if (enginePower <= 0) {
            System.out.println("значение enginePower должно быть польше нуля, повторите попытку");
            return readEnginePowerFromConsole();
        } else {
            return enginePower;
        }
    }

    public VehicleType readVehicleTypeFromConsole() {
        System.out.println("Тип машины и её номер");
        int nomber = 0;
        VehicleType[] types = new VehicleType[VehicleType.values().length];
        for (VehicleType vehicleType : VehicleType.values()) {
            types[nomber] = vehicleType;
            System.out.println(nomber + " " + vehicleType);
            nomber++;
        }
        System.out.println("напишите номер варианта");
        int inputnomber = scanner.nextInt();
        if (inputnomber > nomber || inputnomber <= 0) {
            System.out.println("Вы ввели несуществующий номер, повторите попытку");
            System.out.println("Напоминаем");
            return readVehicleTypeFromConsole();
        } else {
            return types[inputnomber];
        }
    }

    public FuelType readFuelTypeFromConsole() {
        System.out.println("Тип машины и её номер");
        int nomber = 0;
        FuelType[] types = new FuelType[VehicleType.values().length];
        for (FuelType fuelType : FuelType.values()) {
            types[nomber] = fuelType;
            System.out.println(nomber + " " + fuelType);
            nomber++;
        }
        System.out.println("напишите номер варианта");
        int inputnomber = scanner.nextInt();
        if (inputnomber > nomber || inputnomber <= 0) {
            System.out.println("Вы ввели несуществующий номер, повторите попытку");
            System.out.println("Напоминаем");
            return readFuelTypeFromConsole();
        } else {
            return types[inputnomber];
        }
    }


}
