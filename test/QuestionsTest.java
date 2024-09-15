/*
//import junit.framework.TestCase;
//import org.junit.Assert;


public class QuestionsTest extends TestCase {
    private Questions questions;

    @Override
    protected void setUp() {
        // Initialize with dummy data to avoid file I/O
        questions = new Questions("unused") {
            @Override
            protected void loadQuestionsFromFile(String filename) {
                // Manually setting dummy data
                questionMap.put("What is the chemical symbol for Helium?", "He");
                questionMap.put("What is the formula for water?", "H2O");
            }
        };
    }

    // Test setting and getting questions
    public void testSetAndGetQuestions() {
        String testQuestion = "What is the atomic number of Carbon?";
        String testAnswer = "6";
        questions.setQuestion(testQuestion, testAnswer);

        // Assert that the question is set correctly
        Assert.assertEquals("Test setting a question and getting its answer",
                testAnswer, questions.getAnswer(testQuestion));

        // Test getting answer for non-existing question
        Assert.assertNull("Getting a non-existing question should return null",
                questions.getAnswer("What is the symbol for Gold?"));
    }

    // Test random question retrieval
    public void testGetAnyQuestion() {
        String question = questions.getAnyQuestion();
        Assert.assertNotNull("Should retrieve a question randomly", question);
        // Verify it retrieves one of the known questions
        boolean isValidQuestion = "What is the chemical symbol for Helium?".equals(question) ||
                "What is the formula for water?".equals(question);
        Assert.assertTrue("The retrieved question should be valid", isValidQuestion);
    }
}*/
