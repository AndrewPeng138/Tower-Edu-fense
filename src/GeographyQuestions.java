import java.util.Map;

public class GeographyQuestions extends Questions {

    /**
     * Constructor for GeographyQuestions which loads questions from a specific file.
     * @param filename The path to the file containing geography questions and answers.
     */
    public GeographyQuestions(String filename) {
        super(filename);  // Call the superclass constructor to load questions from the specified file
    }

    /**
     * Retrieves the question that corresponds to a specific answer by searching through the questionMap.
     * This method returns the first question that matches the given answer, if any.
     * If no questions match the answer, this method returns null.
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
     * This method allows modifying the answer for an existing question or adding a new question-answer pair if the question does not exist.
     * @param question The text of the question.
     * @param answer The new text of the answer.
     */
    public void setAnswer(String question, String answer) {
        questionMap.put(question, answer);  // Update or set the answer for the given question
    }
}
