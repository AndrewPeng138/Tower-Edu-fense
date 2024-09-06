import javax.swing.*;
import java.awt.*;

public class GameView {
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

    public GameView() {
        JFrame frame = new JFrame("Select Difficulty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeScreenView.setScreenSize(frame);
        frame.setLayout(new GridLayout(1, 3));

        // Top panel:
        topPanel = new JPanel();
        topPanel.add(new JLabel("Top Section"));

        // Middle panel: 15x15 grid with boxes
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(15, 15)); // 15x15 grid layout
        int boxSize = 94; // Size of each box
        for (int i = 0; i < 225; i++) {
            JPanel box = new JPanel();
            box.setPreferredSize(new Dimension(boxSize, boxSize));
            box.setBackground(Color.LIGHT_GRAY);
            box.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border around each box
            middlePanel.add(box);
        }

        // Set a preferred size for the middlePanel to ensure proper width
        middlePanel.setPreferredSize(new Dimension(15 * boxSize, 15 * boxSize));

        // Bottom panel
        bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Bottom Section"));

        // Add all panels to the main frame
        frame.add(topPanel);     // Top panel
        frame.add(middlePanel);  // Middle panel
        frame.add(bottomPanel);  // Bottom panel
        frame.setSize(new Dimension(15 * boxSize, 15 * boxSize + 100));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameView::new);
    }
}
