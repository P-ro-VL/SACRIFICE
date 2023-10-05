package neu.cs.sacrifice.api.utils.assets_loader;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class SoundPlayer {

    public static String SOUND_DIR = "assets/sounds/";

    /**
     * Play the given sound name
     * @param soundPath The sound file path.
     * @param volume The sound volume (must be between 0 and 100)
     */
    public static Clip playGlobalSound(String soundPath, int volume) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            return clip;
        } catch (MalformedURLException e) {
            return null;
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
