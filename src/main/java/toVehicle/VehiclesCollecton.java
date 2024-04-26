package toVehicle;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VehiclesCollecton {
    @XmlElement
    @XmlElementWrapper(name = "vehicle")
    public HashSet<Vehicle> vehicleList;

}
