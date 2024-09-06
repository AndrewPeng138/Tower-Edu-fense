import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public abstract class Questions {
    protected HashMap<String, String> questionMap;
    private Random random = new Random();  // Random object for selecting questions

    public Questions(String filename) {
        questionMap = new HashMap<>();
        loadQuestionsFromFile(filename);
    }

    // Method to load questions from a file
    private void loadQuestionsFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|"); // Ensure your delimiter matches the one used in the file
                if (parts.length == 3) { // Format should be: Category|Question|Answer
                    String question = parts[1];
                    String answer = parts[2];
                    questionMap.put(question, answer);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filename);
        }
    }

    public void setQuestion(String question, String answer) {
        questionMap.put(question, answer);
    }

    public String getAnswer(String question) {
        return questionMap.get(question);
    }

    // Method to randomly retrieve any question
    public String getAnyQuestion() {
        if (questionMap.isEmpty()) {
            return null; // Return null if no questions are available
        }
        List<String> keys = new ArrayList<>(questionMap.keySet());
        return keys.get(random.nextInt(keys.size())); // Randomly select a question
    }
}
