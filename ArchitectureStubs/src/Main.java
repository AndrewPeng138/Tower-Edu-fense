import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instantiate the question classes with paths to their respective files
        String basePath = "/Users/OwenPhotog/Desktop/SoftwareDev/team_boza/ArchitectureStubs/src/";

        MathQuestions mathQuestions = new MathQuestions(basePath + "MathTimesTables.txt");
        GeographyQuestions geographyQuestions = new GeographyQuestions(basePath + "GeographyStateCap.txt");
        ChemistryQuestions chemistryQuestions = new ChemistryQuestions(basePath + "ChemistryPeriodicTable.txt");

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
}
