import javax.swing.*;
import java.awt.*;

public class DifficultyView extends JPanel {
    private JButton[] buttons;
    private Image backgroundImage;
    private Image scaledBackgroundImage;

    public DifficultyView() {
        // Load the background image
        backgroundImage = new ImageIcon("Images/MapScreen.png").getImage();
        JFrame frame = new JFrame("Select Difficulty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeScreenView.setScreenSize(frame);
        setLayout(null);

        // Buttons
        buttons = new JButton[4];
        String[] buttonImages = {
                "Images/EasyButton.png",
                "Images/MediumButton.png",
                "Images/HardButton.png",
                "Images/ExpertButton.png"
        };

        // Custom width and height for buttons
        int buttonWidth = 250;
        int buttonHeight = 120;

        // Button image paths
        String[] gameBackgrounds = {
                "Images/EasyMap.png",
                "Images/MediumMap.png",
                "Images/HardMap.png",
                "Images/ExpertMap.png"
        };

        for (int i = 0; i < buttons.length; i++) {
            ImageIcon originalIcon = new ImageIcon(buttonImages[i]);
            Image scaledImage = originalIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            buttons[i] = new JButton(scaledIcon);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
            buttons[i].setFocusPainted(false);
            buttons[i].setOpaque(false);
        }

        // Set custom positions for each button
        buttons[0].setBounds(575, 150, buttonWidth, buttonHeight);   // Easy Button
        buttons[1].setBounds(575, 300, buttonWidth, buttonHeight);   // Medium Button
        buttons[2].setBounds(575, 450, buttonWidth, buttonHeight);   // Hard Button
        buttons[3].setBounds(575, 600, buttonWidth, buttonHeight);   // Expert Button

        // Add action listeners to each button to load the corresponding background
        for (int i = 0; i < buttons.length; i++) {
            String backgroundPath = gameBackgrounds[i];
            buttons[i].addActionListener(e -> {
                new GameView(backgroundPath);  // Pass the selected background to GameView
                frame.dispose(); // Close current window
            });
            add(buttons[i]);  // Add buttons to the panel
        }

        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (scaledBackgroundImage == null) {
            scaledBackgroundImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        }

        g.drawImage(scaledBackgroundImage, 0, 0, this);
    }

    public static void main(String[] args) {
        new DifficultyView();
    }
}
