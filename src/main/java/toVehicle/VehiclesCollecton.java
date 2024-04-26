package toVehicle;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class VehiclesCollecton implements Serializable {
   
    public HashSet<Vehicle> vehicles;

}
