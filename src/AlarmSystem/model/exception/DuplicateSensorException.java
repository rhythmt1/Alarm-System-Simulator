package AlarmSystem.model.exception;

import AlarmSystem.model.Sensor;

public class DuplicateSensorException extends Exception {

    public DuplicateSensorException(Sensor theSensor) {
        super("There is already a sensor located at: " + theSensor.getLocation());
    }
}