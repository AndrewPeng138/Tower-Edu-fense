import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private Image backgroundImage;
    private JFrame frame;

    public GameView(String backgroundPath) {
        // Load the background image
        backgroundImage = new ImageIcon(backgroundPath).getImage();
        frame = new JFrame("Game View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeScreenView.setScreenSize(frame);
        setLayout(null);
        frame.add(this);
        frame.setVisible(true);
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

    // Getter for the background image for testing
    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameView("../Images/EasyMap.png"));
    }
}
