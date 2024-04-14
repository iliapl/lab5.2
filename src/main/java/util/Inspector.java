package util;

import exceptions.VehicleInspectirException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import toVehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class Inspector {
    private static final Map<Integer, Integer> ID_MAP;
    private static ValidatorFactory factory;
    private static Validator validator;

    static {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        ID_MAP = new HashMap<>();
    }
    private Inspector() {
        throw new UnsupportedOperationException("This class should not be instantiated");
    }
    public static void validateStudyGroup(Vehicle... vehicles) {
        long maxId = 0;
        for (Vehicle vehicle : vehicles) {
            ID_MAP.put((int) vehicle.getId(), ID_MAP.getOrDefault(vehicle.getId(), 0) + 1);
            if (validator.validate(vehicles).size() > 0
                    || validator.validate(vehicle.getCoordinates()).size() > 0 || ID_MAP.get(vehicle.getId()) > 1) {
                throw new VehicleInspectirException("В исходном JSON-файле содержатся ошибки");
            }
            maxId = Math.max(maxId, vehicle.getId());
        }
        Vehicle.setNextId((int) (maxId + 1));
    }
}
