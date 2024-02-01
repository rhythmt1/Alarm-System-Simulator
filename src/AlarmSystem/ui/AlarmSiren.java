package AlarmSystem.ui;

import AlarmSystem.model.Observer;

import javax.sound.sampled.*;
import java.io.File;

public class AlarmSiren implements Observer {

    private Clip line;

    //Constructor loads siren wave file and sets up audio system.
    public AlarmSiren() {

        try {
            String sep = System.getProperty("file.separator");
            File soundFile = new File(System.getProperty("user.dir") + sep
                    + "resources" + sep + "alarm.wav");
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = sound.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                line = null;
            }
            else {
                line = (Clip) AudioSystem.getLine(info);
                //line.setLoopPoints(0, -1);
                line.open(sound);
            }
        } catch (Exception ex) {
            line = null;
        }
    }

    @Override
    public void update(boolean isRinging) {
        if (line != null) {
            if (isRinging) {
                line.setFramePosition(0);
                line.loop(10);
            }
            else
                line.stop();
        }
    }
}
