package AlarmSystem.model;

import AlarmSystem.model.exception.*;
import AlarmSystem.model.*;

import java.util.Collection;
import java.util.HashSet;

public class AlarmController {

    private Collection<AlarmCode> codes;
    private Collection<Sensor> sensors;
    private boolean isArmed;
    private boolean allSensorsClosed;
    private Alarm theAlarm;

    public AlarmController() {
        codes = new HashSet<AlarmCode>();
        sensors = new HashSet<Sensor>();
        isArmed = false;
        theAlarm = new Alarm();
        codes.add(new AlarmCode("1111"));
        update();
    }

    //Adds a code to set of valid codes registered with system.
    //throws NotValidCodeException if code is not valid
    public void addCode(AlarmCode code) throws NotValidCodeException {
        if (!code.isValid())
            throw new NotValidCodeException(code);

        EventLog.getInstance().logEvent(new Event("Added code: " + code));
        codes.add(code);
    }

    //Removes code from set of valid codes registered with the system.
    //throws NotValidCodeException if code is not
    //throws LastCodeException if this code is the last code in the system
    // throws CodeException if this code is not registered in system
    public void removeCode(AlarmCode code) throws NotValidCodeException, CodeException, LastCodeException {
        if (!code.isValid())
            throw new NotValidCodeException(code);
        if (!codes.contains(code))
            throw new CodeException(code);
        if (codes.size() == 1)
            throw new LastCodeException();

        EventLog.getInstance().logEvent(new Event("Removed code: " + code));
        codes.remove(code);
    }

    //Adds a sensor to be monitored by the system.
    // throws DuplicateSensorException if sensor already exists in system
    public void addSensor(Sensor theSensor) throws DuplicateSensorException {
        if (!sensors.contains(theSensor)) {
            sensors.add(theSensor);
            theSensor.setAlarmController(this);
            EventLog.getInstance().logEvent(
                    new Event("Added sensor at: " + theSensor.getLocation()));
            update();
        }
        else
            throw new DuplicateSensorException(theSensor);
    }

    //Determines if system is ready to be armed.
    //return  true if system is ready to be armed, false otherwise.
    public boolean isReady() {
        return allSensorsClosed;
    }

    //Determines if system is armed.
    //return  true if system is armed, false otherwise.
    public boolean isArmed() {
        return isArmed;
    }

    //Arms the system if code is registered with system.
    //throws SystemNotReadyException if system cannot be armed
    //throws CodeException if code is not registered with system
    public void arm(AlarmCode code) throws SystemNotReadyException, CodeException {
        if (!isReady())
            throw new SystemNotReadyException();
        if (!codes.contains(code))
            throw new CodeException(code);

        isArmed = true;
        EventLog.getInstance().logEvent(new Event("Armed system with code " + code));
    }

    //Arms the system (when remote is used).
    //throws SystemNotReadyException if system cannot be armed
    public void arm() throws SystemNotReadyException {
        if (!isReady())
            throw new SystemNotReadyException();
        isArmed = true;
        EventLog.getInstance().logEvent(new Event("Armed system with remote."));
    }

    //Disarms the system.
    //throws CodeException if code is not registered with system.
    public void disarm(AlarmCode code) throws CodeException {
        if (!codes.contains(code))
            throw new CodeException(code);

        isArmed = false;
        EventLog.getInstance().logEvent(new Event("Disarmed system with code " + code));
        if (theAlarm.isSounding())
            theAlarm.silence();
    }


    //Adds an observer to the alarm
    public void addAlarmObserver(Observer o) {
        theAlarm.addObserver(o);
    }

     //Updates the system based on current state of the sensors.
     //If all sensors are closed, system is ready, otherwise system is not ready
    void update() {
        allSensorsClosed = true;
        for (Sensor s : sensors) {
            if (s.isOpen())
                allSensorsClosed = false;
        }

        if (!allSensorsClosed && isArmed && !theAlarm.isSounding()) {
            theAlarm.sound();
        }
    }
}
