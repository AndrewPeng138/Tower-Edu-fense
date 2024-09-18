import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class WAVPlayer {

    private Clip audioClip;
    private FloatControl volumeControl;

    // Constructor to load the WAV file and prepare the audio stream
    public WAVPlayer(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File audioFile = new File(filePath);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

        // Get the audio format and set up the clip
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStream);

        // Obtain the volume control for the clip
        volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
    }

    // Method to play the WAV file
    public void play() {
        if (audioClip != null) {
            // Stop and reset the clip if it's already running
            if (audioClip.isRunning()) {
                audioClip.stop();
            }

            // Set the clip's position to the start and then play it
            audioClip.setFramePosition(0);
            audioClip.start();
        }
    }


    // Method to stop the WAV file
    public void stop() {
        if (audioClip != null && audioClip.isRunning()) {

            audioClip.stop();
        }
    }

    // Method to close the audio clip when done
    public void close() {
        if (audioClip != null) {
            audioClip.close();
        }
    }

    // Method to set the volume (input is a value between 0.0 and 1.0)
    public void setVolume(float volume) {
        if (volumeControl != null) {
            float minVolume = volumeControl.getMinimum();
            float maxVolume = volumeControl.getMaximum();

            // Scale the volume to the appropriate range and set it
            float newVolume = minVolume + (maxVolume - minVolume) * volume;
            volumeControl.setValue(newVolume);
            System.out.println("Volume set to: " + volume);
        }
    }
}
