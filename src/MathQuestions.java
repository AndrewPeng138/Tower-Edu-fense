import java.util.Map;

public class MathQuestions extends Questions {

    // Constructor that loads math questions from the database
    public MathQuestions() {
        super("Math");  // Pass "Math" as the category to the parent class
    }

    public String getQuestion(String answer) {
        for (Map.Entry<String, String> entry : questionMap.entrySet()) {
            if (entry.getValue().equals(answer)) {
                return entry.getKey();  // Return the question when the answer matches
            }
        }
        return null;  // Return null if no matching question is found
    }

    public void setAnswer(String question, String answer) {
        questionMap.put(question, answer);  // Update or set the answer for the given question
    }
}
