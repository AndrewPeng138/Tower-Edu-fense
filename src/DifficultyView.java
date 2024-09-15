import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DifficultyView extends JPanel {
    private JButton[] buttons;
    private Image backgroundImage;
    private Image scaledBackgroundImage;
    private JFrame frame;
    private Questions questions;

    public DifficultyView(Questions questions) {
        this.questions = questions;  // Store the questions passed to DifficultyView
        backgroundImage = new ImageIcon("Images/MapScreen.png").getImage();

        frame = new JFrame("Select Difficulty");
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

        // Button image paths for the map backgrounds
        String[] gameBackgrounds = {
                "Images/GameViewBackground.png",   // Easy map background
                "Images/GameViewBackground.png", // Medium map background
                "Images/GameViewBackground.png",   // Hard map background
                "Images/GameViewBackground.png"  // Expert map background
        };

        // Map types corresponding to the difficulty levels
        String[] mapTypes = {
                "easy",    // Easy map type
                "normal",  // Medium map type
                "hard",    // Hard map type
                "extreme"   // Expert map type
        };

        // Loop through and create the buttons with action listeners
        for (int i = 0; i < buttons.length; i++) {
            ImageIcon originalIcon = new ImageIcon(buttonImages[i]);
            Image scaledImage = originalIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            buttons[i] = new JButton(scaledIcon);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
            buttons[i].setFocusPainted(false);
            buttons[i].setOpaque(false);

            // Set the custom button positions
            int buttonY = 150 + i * 150;  // Vertical position for each button
            buttons[i].setBounds(575, buttonY, buttonWidth, buttonHeight);

            // Add action listeners to each button to load the corresponding background and map type
            String backgroundPath = gameBackgrounds[i];  // Background image path for this difficulty
            String mapType = mapTypes[i];  // Map type corresponding to this difficulty

            buttons[i].addActionListener(e -> {
                // When a difficulty is selected, pass the background and map type to GameView
                try {
                    new GameView(backgroundPath, questions, mapType);  // Pass background, questions, and map type to GameView
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();  // Close current window
            });

            add(buttons[i]);  // Add the button to the panel
        }

        // Add the DifficultyView panel to the frame
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
}
