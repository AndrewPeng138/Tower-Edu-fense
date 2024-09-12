import javax.swing.*;
import java.awt.*;

public class WorldMapView extends JPanel {
    private JButton[] buttons;
    private Image backgroundImage;
    private JFrame frame;

    public WorldMapView() {
        // Load the background image
        backgroundImage = new ImageIcon("Images/MapScreen.png").getImage();

        frame = new JFrame("World Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeScreenView.setScreenSize(frame);
        setLayout(null);

        buttons = new JButton[3];

        // Button 1: Math
        buttons[0] = createButton("Images/MathButton.png", 200, 100);
        buttons[0].setBounds(375, 300, 150, 100);  // Set position for Math button

        // Button 2: Geography
        buttons[1] = createButton("Images/GeographyButton.png", 200, 100);
        buttons[1].setBounds(760, 380, 150, 100);  // Set position for Geography button

        // Button 3: Chemistry (was Science earlier, adjust as per your naming)
        buttons[2] = createButton("Images/ScienceButton.png", 200, 100);
        buttons[2].setBounds(925, 620, 150, 100);  // Set position for Chemistry button

        // Add the buttons and their actions
        buttons[0].addActionListener(e -> {
            // Load Math questions from the database and pass them to DifficultyView
            Questions mathQuestions = new MathQuestions();  // No more file paths needed
            new DifficultyView(mathQuestions);  // Pass the math questions to DifficultyView
            frame.dispose();       // Close current window
        });

        buttons[1].addActionListener(e -> {
            // Load Geography questions from the database and pass them to DifficultyView
            Questions geographyQuestions = new GeographyQuestions();  // No more file paths needed
            new DifficultyView(geographyQuestions);  // Pass the geography questions to DifficultyView
            frame.dispose();       // Close current window
        });

        buttons[2].addActionListener(e -> {
            // Load Chemistry questions from the database and pass them to DifficultyView
            Questions chemistryQuestions = new ChemistryQuestions();  // No more file paths needed
            new DifficultyView(chemistryQuestions);  // Pass the chemistry questions to DifficultyView
            frame.dispose();       // Close current window
        });

        // Add each button to the panel
        for (JButton button : buttons) {
            add(button);
        }

        frame.add(this);
        frame.setVisible(true);
    }

    // Method to create buttons with resized images and no border
    private JButton createButton(String imagePath, int width, int height) {
        ImageIcon buttonIcon = new ImageIcon(imagePath);
        Image scaledImage = buttonIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(resizedIcon);
        button.setOpaque(false);                   // Make the button background transparent
        button.setContentAreaFilled(false);        // Disable the content area filling
        button.setBorderPainted(false);            // Disable the button's border
        button.setFocusPainted(false);             // Disable the focus border when clicked
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    // Getter for the JFrame for testing
    public JFrame getFrame() {
        return frame;
    }

    // Getter for the buttons for testing
    public JButton[] getButtons() {
        return buttons;
    }

    public static void main(String[] args) {
        new WorldMapView();
    }
}
