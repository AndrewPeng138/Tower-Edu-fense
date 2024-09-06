import java.util.HashMap;
import java.util.Map;

public class ChemistryQuestions extends Questions {

    // Constructor that specifies the filename of the chemistry questions
    public ChemistryQuestions(String filename) {
        super(filename); // Specify the text file name containing chemistry questions
    }

    /**
     * Stores a question and its corresponding answer in the questionMap.
     * @param question The text of the question.
     * @param answer The text of the answer.
     */
    @Override
    public void setQuestion(String question, String answer) {
        questionMap.put(question, answer);  // Store the question and its corresponding answer
    }

    /**
     * Retrieves the answer for a given question from the questionMap.
     * @param question The text of the question for which the answer is needed.
     * @return The answer corresponding to the given question, or null if the question does not exist.
     */
    @Override
    public String getAnswer(String question) {
        return questionMap.get(question);  // Retrieve the answer for the given question
    }

    /**
     * Retrieves the question that corresponds to a specific answer by searching through the questionMap.
     * @param answer The text of the answer for which the corresponding question is needed.
     * @return The question that corresponds to the given answer, or null if no matching question is found.
     */
    public String getQuestion(String answer) {
        for (Map.Entry<String, String> entry : questionMap.entrySet()) {
            if (entry.getValue().equals(answer)) {
                return entry.getKey();  // Return the question when the answer matches
            }
        }
        return null;  // Return null if no matching question is found
    }

    /**
     * Updates or sets the answer for a given question in the questionMap.
     * @param question The text of the question.
     * @param answer The new text of the answer.
     */
    public void setAnswer(String question, String answer) {
        questionMap.put(question, answer);  // Update or set the answer for the given question
    }
}
