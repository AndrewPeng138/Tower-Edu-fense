import java.util.HashMap;
import java.util.Scanner;

public abstract class Questions
{
    protected HashMap<String, String> questionMap;
    protected Scanner answerInput;

    public Questions()
    {
        questionMap = new HashMap<>();
        answerInput = new Scanner(System.in);
    }

    /**
     * Sets a question and its answer in the questionMap.
     * @param question The question text.
     * @param answer The answer text.
     */
    public abstract void setQuestion(String question, String answer);

    /**
     * Retrieves the answer for a given question.
     * @param question The question text.
     * @return The answer text.
     */
    public abstract String getAnswer(String question);

    /**
     * Retrieves the question for a given answer.
     * @param answer The answer text.
     * @return The question text.
     */
    public abstract String getQuestion(String answer);

    /**
     * Sets an answer for a given question.
     * @param question The question text.
     * @param answer The answer text.
     */
    public abstract void setAnswer(String question, String answer);


}
