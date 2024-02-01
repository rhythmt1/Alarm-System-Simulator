package AlarmSystem.model.exception;

import AlarmSystem.model.AlarmCode;

public class NotValidCodeException extends AlarmException {
    public NotValidCodeException(AlarmCode code) {
        super("Code " + code + " is not valid.");
    }
}