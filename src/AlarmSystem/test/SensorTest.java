package AlarmSystem.test;

import AlarmSystem.model.AlarmController;
import AlarmSystem.model.Sensor;
import AlarmSystem.model.exception.DuplicateSensorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SensorTest {
    private Sensor s;

    @BeforeEach
    public void runBefore() {
        AlarmController ac = new AlarmController();
        try {
            s = new Sensor("my location", ac);
        } catch (DuplicateSensorException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSensor() {
        assertEquals("my location", s.getLocation());
        assertFalse(s.isOpen());
    }

    @Test
    public void testOpen() {
        assertFalse(s.isOpen());
        s.open();
        assertTrue(s.isOpen());
    }

    @Test
    public void testClose() {
        assertFalse(s.isOpen());
        s.open();
        assertTrue(s.isOpen());
        s.close();
        assertFalse(s.isOpen());
    }

    @Test
    public void testIsOpen() {
        assertFalse(s.isOpen());
        s.open();
        assertTrue(s.isOpen());
        s.close();
        assertFalse(s.isOpen());
    }

    @Test
    public void testGetLocation() {
        assertEquals("my location", s.getLocation());
    }
}
