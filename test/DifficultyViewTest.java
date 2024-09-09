import org.junit.Test;
import org.junit.Assert;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DifficultyViewTest {

    @Test
    public void testDifficultyViewComponents() {
        // Create an instance of DifficultyView
        DifficultyView difficultyView = new DifficultyView();

        // Access the JFrame using the getter
        JFrame frame = difficultyView.getFrame();
        Assert.assertNotNull("JFrame should not be null", frame);
        System.out.println("JFrame is successfully created with the title 'Select Difficulty'.");

        // Access the buttons array using the getter
        JButton[] buttons = difficultyView.getButtons();
        Assert.assertNotNull("Buttons array should not be null", buttons);
        Assert.assertEquals("There should be 4 buttons", 4, buttons.length);
        System.out.println("Buttons array is correctly created with 4 buttons.");

        // Test each button's properties
        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            Assert.assertNotNull("Button should not be null", button);
            Assert.assertFalse("Button content area should not be filled", button.isContentAreaFilled());
            Assert.assertFalse("Button should not be opaque", button.isOpaque());
            Assert.assertFalse("Button should not have a border painted", button.isBorderPainted());
            System.out.println("Button " + (i + 1) + " has correct transparency settings.");
        }

        // Check button positions and sizes
        Assert.assertEquals(575, buttons[0].getX());
        Assert.assertEquals(150, buttons[0].getY());
        Assert.assertEquals(250, buttons[0].getWidth());
        Assert.assertEquals(120, buttons[0].getHeight());
        System.out.println("Easy button has correct location and size.");

        Assert.assertEquals(575, buttons[1].getX());
        Assert.assertEquals(300, buttons[1].getY());
        Assert.assertEquals(250, buttons[1].getWidth());
        Assert.assertEquals(120, buttons[1].getHeight());
        System.out.println("Medium button has correct location and size.");

        Assert.assertEquals(575, buttons[2].getX());
        Assert.assertEquals(450, buttons[2].getY());
        Assert.assertEquals(250, buttons[2].getWidth());
        Assert.assertEquals(120, buttons[2].getHeight());
        System.out.println("Hard button has correct location and size.");

        Assert.assertEquals(575, buttons[3].getX());
        Assert.assertEquals(600, buttons[3].getY());
        Assert.assertEquals(250, buttons[3].getWidth());
        Assert.assertEquals(120, buttons[3].getHeight());
        System.out.println("Expert button has correct location and size.");
    }
}
