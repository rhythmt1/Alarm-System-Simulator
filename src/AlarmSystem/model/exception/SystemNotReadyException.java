package AlarmSystem.model.exception;

public class SystemNotReadyException extends AlarmException {
    public SystemNotReadyException() {
        super("System not ready");
    }

    public SystemNotReadyException(String msg) {
        super(msg);
    }
}
