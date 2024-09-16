/*
//import org.junit.Test;
//import org.junit.Assert;
import javax.swing.JButton;
import javax.swing.JFrame;

public class WelcomeScreenViewTest {

    @Test
    public void WelcomeScreenTests() {
        // Create an instance of WelcomeScreenView
        WelcomeScreenView welcomeScreenView = new WelcomeScreenView();

        // Access the JFrame using the getter
        JFrame frame = welcomeScreenView.getFrame();
        Assert.assertNotNull("JFrame should not be null", frame);
        System.out.println("JFrame is successfully created with the title 'Welcome Screen'.");

        // Test the play button exists inside the frame
        JButton playButton = welcomeScreenView.getButton();
        Assert.assertNotNull("Play button should be created", playButton);
        System.out.println("Play button is successfully created.");

        // Check button's transparency settings
        Assert.assertFalse("Button content area should not be filled", playButton.isContentAreaFilled());
        Assert.assertFalse("Button should not be opaque", playButton.isOpaque());
        Assert.assertFalse("Button should not have a border painted", playButton.isBorderPainted());
        System.out.println("Play button has correct transparency settings.");

        // Check button's location and size
        Assert.assertEquals(500, playButton.getX());
        Assert.assertEquals(570, playButton.getY());
        Assert.assertEquals(350, playButton.getWidth());
        Assert.assertEquals(250, playButton.getHeight());
        System.out.println("Play button has correct location and size.");
    }
}
*/
