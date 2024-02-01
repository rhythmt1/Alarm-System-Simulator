package AlarmSystem.model;

import AlarmSystem.model.exception.SystemNotReadyException;

public class Remote {
    private AlarmController alarmController;

    //Constructor
    public Remote(AlarmController controller) {
        alarmController = controller;
    }

    //Arms the alarm system.
    //throws SystemNotReadyException if system cannot be armed
    public void activate() throws SystemNotReadyException {
        alarmController.arm();
    }
}
