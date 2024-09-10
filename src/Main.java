import java.util.Scanner;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nMain Menu:");
            System.out.println("1: Launch Welcome Screen");
            System.out.println("2: Run Map Model Test");
            System.out.println("4: Exit");
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    SwingUtilities.invokeLater(() -> new WelcomeScreenView());
                    break;
                case 2:
                    runMapModelTest();
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    // Map model test logic moved into a separate method in Main
    public static void runMapModelTest() {
        // Create MapModel objects
        MapModel easyMap = new MapModel("easy");
        MapModel normalMap = new MapModel("normal");
        MapModel hardMap = new MapModel("hard");
        MapModel extremeMap = new MapModel("extreme");

        // Run the tests
        System.out.println("EASY MAP...");
        easyMap.printLocations();
        System.out.println("NORMAL MAP...");
        normalMap.printLocations();
        System.out.println("HARD MAP...");
        hardMap.printLocations();
        System.out.println("EXTREME MAP...");
        extremeMap.printLocations();
    }
}
