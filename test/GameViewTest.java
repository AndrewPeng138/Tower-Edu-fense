import org.junit.Test;
import org.junit.Assert;

import javax.swing.*;
import java.awt.*;
/**
public class GameViewTest {

    @Test
    public void testGameViewInitialization() {
        // Create an instance of GameView with a dummy image path
        String testBackgroundPath = "../Images/TestMap.png";
        GameView gameView = new GameView(testBackgroundPath);

        // Access the JFrame using the getter
        JFrame frame = gameView.getFrame();
        Assert.assertNotNull("JFrame should not be null", frame);
        Assert.assertEquals("Frame title should be 'Game View'", "Game View", frame.getTitle());
        System.out.println("JFrame is successfully created with the title 'Game View'.");

        // Verify that the background image is loaded
        Image backgroundImage = gameView.getBackgroundImage();
        Assert.assertNotNull("Background image should be loaded", backgroundImage);
        System.out.println("Background image is successfully loaded from: " + testBackgroundPath);
    }

    @Test
    public void testGameViewFrameSize() {
        // Create an instance of GameView with a dummy image path
        GameView gameView = new GameView("../Images/TestMap.png");

        // Access the JFrame using the getter
        JFrame frame = gameView.getFrame();

        // Note: You may need to wait or ensure the frame is fully initialized before checking size
        // For testing purposes, assume default sizes or set expected values
        int expectedWidth = 1400; // Example width, adjust as needed
        int expectedHeight = 875; // Example height, adjust as needed

        // Allow some time for the frame to be properly set up
        try {
            Thread.sleep(500); // Sleep briefly to ensure the frame size is set
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("Frame width should match expected width", expectedWidth, frame.getWidth());
        Assert.assertEquals("Frame height should match expected height", expectedHeight, frame.getHeight());
        System.out.println("JFrame is set to the correct screen size.");
    }
}
*/