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
                ImageIcon backgroundImage = new ImageIcon("src/WelcomeScreen.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BorderLayout());

        // Play button info
        ImageIcon playButtonIcon = new ImageIcon("src/PlayButton.png");
        Image playButtonImage = playButtonIcon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);  // Resize to 100x50
        ImageIcon resizedPlayButtonIcon = new ImageIcon(playButtonImage);

        button = new JButton(resizedPlayButtonIcon);
        button.setContentAreaFilled(false); // Make the button background transparent
        button.setBorderPainted(false);     // Remove button border
        button.addActionListener(e -> {
            new WorldMapView();  // Navigate to WorldMapView
            frame.dispose();     // Close current window
        });

        // Add the button to the center of the panel
        panel.add(button, BorderLayout.CENTER);

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
