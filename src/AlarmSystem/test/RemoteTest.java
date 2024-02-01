package AlarmSystem.test;

import AlarmSystem.model.AlarmController;
import AlarmSystem.model.Remote;
import AlarmSystem.model.Sensor;
import AlarmSystem.model.exception.DuplicateSensorException;
import AlarmSystem.model.exception.SystemNotReadyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RemoteTest {
    private Remote r;
    private AlarmController ac;
    private Sensor s;

    @BeforeEach
    public void runBefore() {
        ac = new AlarmController();
        r = new Remote(ac);
        try {
            s = new Sensor("Door", ac);
        } catch (DuplicateSensorException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testArm() {
        try {
            assertFalse(ac.isArmed());
            r.activate();
            assertTrue(ac.isArmed());
        } catch (SystemNotReadyException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSNRArm() {
        try {
            assertFalse(ac.isArmed());
            s.open();
            r.activate();
            assertTrue(ac.isArmed());
            fail("SystemNotReadException should have been thrown");
        } catch (SystemNotReadyException e) {
            // expected
        }
    }
}
