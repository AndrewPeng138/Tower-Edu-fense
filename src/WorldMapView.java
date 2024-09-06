//import javax.swing.*;
//import java.awt.*;
//
//public class WorldMapView {
//    private JButton[] buttons;
//
//    public WorldMapView() {
//        JFrame frame = new JFrame("World Map");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Use the utility method from WelcomeScreenView to set the size
//        WelcomeScreenView.setScreenSize(frame);
//
//        buttons = new JButton[3];
//        String[] titles = {"Map 1", "Map 2", "Map 3"};
//
//        JPanel panel = new JPanel(new GridLayout(3, 1));
//        for (int i = 0; i < buttons.length; i++) {
//            buttons[i] = new JButton(titles[i]);
//            buttons[i].addActionListener(e -> {
//                new DifficultyView();  // Navigate to DifficultyView
//                frame.dispose();       // Close current window
//            });
//            panel.add(buttons[i]);
//        }
//
//        frame.add(panel, BorderLayout.CENTER);
//        frame.setVisible(true);
//    }
//}
//
//
//
