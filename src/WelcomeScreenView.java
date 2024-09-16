import javax.swing.*;
import java.awt.*;

public class WelcomeScreenView {
    private JFrame frame;
    private JButton button;

    public WelcomeScreenView() {
        frame = new JFrame("Welcome Screen");
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

        panel.setLayout(null);

        // Play button info
        ImageIcon playButtonIcon = new ImageIcon("Images/PlayButton.png");
        Image playButtonImage = playButtonIcon.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedPlayButtonIcon = new ImageIcon(playButtonImage);

        button = new JButton(resizedPlayButtonIcon);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            new WorldMapView();
            frame.dispose();
        });

        button.setBounds(500, 570, 350, 250);
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void setScreenSize(JFrame frame) {
        frame.setSize(1400, 900);
        frame.setLocationRelativeTo(null);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getButton() {
        return button;
    }

    public static void main(String[] args) {
        new WelcomeScreenView();
    }
}