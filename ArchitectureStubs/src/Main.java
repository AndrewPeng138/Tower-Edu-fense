import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instantiate the question classes
        MathQuestions mathQuestions = new MathQuestions();
        GeographyQuestions geographyQuestions = new GeographyQuestions();
        ChemistryQuestions chemistryQuestions = new ChemistryQuestions();

        // Add some test data to each question category
        mathQuestions.setQuestion("What is 11x11?", "121");
        geographyQuestions.setQuestion("What is the capital of Alabama?", "Montgomery");
        chemistryQuestions.setQuestion("What is the atomic symbol for Helium?", "He");

        // User choice for type of questions
        System.out.println("Select the type of questions you want to answer:");
        System.out.println("1: Math");
        System.out.println("2: Geography");
        System.out.println("3: Chemistry");
        System.out.print("Enter your choice (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Questions questions = null;
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
            default:
                System.out.println("Invalid choice. Exiting.");
                System.exit(1);
        }

        // Ask a question and check the answer
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
            System.out.println("No questions available.");
        }

        scanner.close();
    }
}
