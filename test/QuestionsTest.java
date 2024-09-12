import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class QuestionsTest {
    private Questions chemistryQuestions;

    @Before
    public void setUp() {
        // Initialize ChemistryQuestions (which should load questions from the database)
        chemistryQuestions = new ChemistryQuestions();
    }

    @Test
    public void testLoadQuestionsFromDatabase() {
        // Ensure that questions are successfully loaded from the database
        String randomQuestion = chemistryQuestions.getAnyQuestion();
        assertNotNull("Questions should not be null", randomQuestion);
    }

    @Test
    public void testGetAnswer() {
        // Test that a specific question returns the correct answer
        String question = "What is the symbol for Hydrogen?";
        String answer = chemistryQuestions.getAnswer(question);
        assertEquals("The answer should be 'H'", "H", answer);
    }

    @Test
    public void testGetAnyQuestion() {
        // Ensure that any random question can be retrieved and is valid
        String randomQuestion = chemistryQuestions.getAnyQuestion();
        assertNotNull("getAnyQuestion should return a question", randomQuestion);

        // Ensure the question has a corresponding answer
        String answer = chemistryQuestions.getAnswer(randomQuestion);
        assertNotNull("The answer to the random question should not be null", answer);
    }
}
