import javax.swing.*;
import java.awt.*;

public class GameView {
    //private Map map;
    //private User user;
    //private TowerController tower;
    //private EnemyController enemy;
    //private UserControl userControl;

    private JLabel[] labels;
    private JButton[] buttons;

    public GameView() {
        JFrame frame = new JFrame("Game View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use the utility method from WelcomeScreenView to set the size
        WelcomeScreenView.setScreenSize(frame);

        JLabel label = new JLabel("Yay tower game fun!");
        frame.add(label, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}