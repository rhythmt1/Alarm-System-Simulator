package AlarmSystem.model.exception;

import AlarmSystem.model.AlarmCode;

public class CodeException extends AlarmException {
    public CodeException(AlarmCode code) {
        super("Code is not registered in system.");
    }

}
