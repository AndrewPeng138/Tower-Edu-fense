import org.junit.Test;
import org.junit.Assert;
import javax.swing.JButton;
import javax.swing.JFrame;

public class WorldMapViewTest {

    @Test
    public void WorldMapViewTests() {
        // Create an instance of WorldMapView
        WorldMapView worldMapView = new WorldMapView();

        // Access the JFrame using the getter
        JFrame frame = worldMapView.getFrame();
        Assert.assertNotNull("JFrame should not be null", frame);
        System.out.println("JFrame is successfully created with the title 'World Map'.");

        // Access the buttons array using the getter
        JButton[] buttons = worldMapView.getButtons();
        Assert.assertNotNull("Buttons array should not be null", buttons);
        Assert.assertEquals("There should be 3 buttons", 3, buttons.length);
        System.out.println("Buttons array is correctly created with 3 buttons.");

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
        Assert.assertEquals(375, buttons[0].getX());
        Assert.assertEquals(300, buttons[0].getY());
        Assert.assertEquals(150, buttons[0].getWidth());
        Assert.assertEquals(100, buttons[0].getHeight());
        System.out.println("Math button has correct location and size.");

        Assert.assertEquals(760, buttons[1].getX());
        Assert.assertEquals(380, buttons[1].getY());
        Assert.assertEquals(150, buttons[1].getWidth());
        Assert.assertEquals(100, buttons[1].getHeight());
        System.out.println("Geography button has correct location and size.");

        Assert.assertEquals(925, buttons[2].getX());
        Assert.assertEquals(620, buttons[2].getY());
        Assert.assertEquals(150, buttons[2].getWidth());
        Assert.assertEquals(100, buttons[2].getHeight());
        System.out.println("Science button has correct location and size.");
    }
}
