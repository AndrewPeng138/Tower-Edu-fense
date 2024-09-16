import java.util.Scanner;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;/**/

        while (keepRunning) {
            System.out.println("\nMain Menu:");
            System.out.println("1: Launch Welcome Screen");
            System.out.println("2: Exit");
            System.out.print("Enter your choice (1-2): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    SwingUtilities.invokeLater(() -> new WelcomeScreenView());
                    break;
                case 2:
                    System.out.println("Exiting program.");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

}
