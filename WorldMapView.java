import javax.swing.*;
import java.awt.*;

public class WorldMapView extends JPanel {
    private JButton[] buttons;
    private Image backgroundImage;

    public WorldMapView() {
        // Load the background image
        backgroundImage = new ImageIcon("src/MapScreen.png").getImage();

        JFrame frame = new JFrame("World Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeScreenView.setScreenSize(frame);
        setLayout(null);

        buttons = new JButton[3];
        String[] titles = {"Math", "Geography", "Chemistry"};

        // Button 1: Math
        buttons[0] = new JButton(titles[0]);
        buttons[0].setBounds(390, 300, 120, 50);  // x = 50, y = 50

        // Button 2: Geography
        buttons[1] = new JButton(titles[1]);
        buttons[1].setBounds(768, 400, 120, 50);  // x = 200, y = 300

        // Button 3: Chemistry
        buttons[2] = new JButton(titles[2]);
        buttons[2].setBounds(930, 620, 120, 50);  // x = 400, y = 500

        // Add the buttons and their actions
        for (JButton button : buttons) {
            button.addActionListener(e -> {
                new DifficultyView();  // Navigate to DifficultyView
                frame.dispose();       // Close current window
            });
            add(button);  // Add each button to the panel
        }

        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        new WorldMapView();
    }
}
