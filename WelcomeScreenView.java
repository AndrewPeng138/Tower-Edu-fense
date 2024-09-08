import javax.swing.*;
import java.awt.*;

public class WelcomeScreenView {
    private JButton button;

    public WelcomeScreenView() {
        JFrame frame = new JFrame("Welcome Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setScreenSize(frame);

        // Create a JPanel with a background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Images/MapScreen.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set the panel layout to null for absolute positioning
        panel.setLayout(null);

        // Play button info
        ImageIcon playButtonIcon = new ImageIcon("Images/PlayButton.png");
        Image playButtonImage = playButtonIcon.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedPlayButtonIcon = new ImageIcon(playButtonImage);

        button = new JButton(resizedPlayButtonIcon);
        button.setOpaque(false);
        button.setContentAreaFilled(false); // Make the button background transparent
        button.setBorderPainted(false);     // Remove button border
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            new WorldMapView();  // Navigate to WorldMapView
            frame.dispose();     // Close current window
        });

        // Set the button's location and size (x, y, width, height)
        button.setBounds(500, 570, 350, 250);

        // Add the button to the panel at the specified location
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void setScreenSize(JFrame frame) {
        frame.setSize(1400, 900); // Set fixed size
        frame.setLocationRelativeTo(null);  // Center the window on the screen
    }

    public static void main(String[] args) {
        new WelcomeScreenView();
    }
}
