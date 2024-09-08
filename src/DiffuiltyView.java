import javax.swing.*;
import java.awt.*;

public class DifficultyView {
    private JButton[] buttons;

    public DifficultyView() {
        JFrame frame = new JFrame("Select Difficulty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use the utility method from WelcomeScreenView to set the size
        WelcomeScreenView.setScreenSize(frame);

        buttons = new JButton[4];
        String[] difficulties = {"Easy", "Medium", "Hard", "Expert"};

        JPanel panel = new JPanel(new GridLayout(4, 1));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(difficulties[i]);
            buttons[i].addActionListener(e -> {
                new GameView();  // Navigate to GameView
                frame.dispose(); // Close current window
            });
            panel.add(buttons[i]);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}



