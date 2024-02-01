package AlarmSystem.model.exception;

public class LastCodeException extends AlarmException {
    public LastCodeException() {
        super("Last code in system cannot be removed.");
    }

    public LastCodeException(String msg) {
        super(msg);
    }
}
