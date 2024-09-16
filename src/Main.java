import java.util.Scanner;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SwingUtilities.invokeLater(() -> new WelcomeScreenView());
    }

}
