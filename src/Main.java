import java.util.Scanner;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nMain Menu:");
            System.out.println("1: Launch Welcome Screen");
            System.out.println("2: Run Quiz");
            System.out.println("3: Run Map Model Test");
            System.out.println("4: Exit");
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Launch Welcome Screen
                    SwingUtilities.invokeLater(() -> new WelcomeScreenView());
                    break;
                case 2:
                    // Run the quiz logic
                    startQuiz();
                    break;
                case 3:
                    // Run the MapModelTest logic within Main
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

    // Quiz logic remains the same, extracted to a separate method
    public static void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        // Instantiate the question classes with paths to their respective files
        String realtivePath = "../team_boza/";

        MathQuestions mathQuestions = new MathQuestions(realtivePath + "questions/MathTimesTables.txt");
        GeographyQuestions geographyQuestions = new GeographyQuestions(realtivePath + "questions/GeographyStateCap.txt");
        ChemistryQuestions chemistryQuestions = new ChemistryQuestions(realtivePath + "questions/ChemistryPeriodicTable.txt");

        Questions questions = null;
        boolean keepRunning = true;

        while (keepRunning) {
            // User choice for type of questions
            System.out.println("\nSelect the type of questions you want to answer:");
            System.out.println("1: Math");
            System.out.println("2: Geography");
            System.out.println("3: Chemistry");
            System.out.println("4: Quit");
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    questions = mathQuestions;
                    break;
                case 2:
                    questions = geographyQuestions;
                    break;
                case 3:
                    questions = chemistryQuestions;
                    break;
                case 4:
                    System.out.println("Exiting.");
                    keepRunning = false;
                    continue;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    continue;
            }

            if (questions != null) {
                String question = questions.getAnyQuestion();
                if (question != null) {
                    System.out.println("Answer the following question:");
                    System.out.println(question);
                    System.out.print("Your answer: ");
                    String userAnswer = scanner.nextLine();

                    String correctAnswer = questions.getAnswer(question);
                    if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                        System.out.println("Correct!");
                    } else {
                        System.out.println("Incorrect. The correct answer is: " + correctAnswer);
                    }
                } else {
                    System.out.println("No questions available in this category.");
                }
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
