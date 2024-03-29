package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // Printing Name of the system property
        System.out.println(
                "user.dir: " + System.getProperty("user.dir"));
        System.out.println(
                "Collecton " + System.getenv("VehicleCollection"));
    }
}