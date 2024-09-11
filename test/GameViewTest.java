import org.junit.Test;
import org.junit.Assert;

import javax.swing.*;
import java.awt.*;

public class GameViewTest {

    @Test
    public void testGameViewInitialization() {
        // Create dummy instances of Questions and a dummy image path
        Questions questions = new Questions();
        String testBackgroundPath = "../Images/TestMap.png";
        String mapType = "testMap";

        // Create an instance of GameView
        GameView gameView = new GameView(testBackgroundPath, questions, mapType);

        // Access the JFrame using the parent component
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(gameView);
        Assert.assertNotNull("JFrame should not be null", frame);
        Assert.assertEquals("Frame title should be 'Game View'", "Game View", frame.getTitle());
        System.out.println("JFrame is successfully created with the title 'Game View'.");

        // Verify that the background image is loaded
        Image backgroundImage = gameView.getBackgroundImage();
        Assert.assertNotNull("Background image should be loaded", backgroundImage);
        System.out.println("Background image is successfully loaded from: " + testBackgroundPath);
    }

    @Test
    public void testGameViewFrameSize() {
        // Create dummy instances of Questions and a dummy image path
        Questions questions = new Questions();
        String testBackgroundPath = "../Images/TestMap.png";
        String mapType = "testMap";

        // Create an instance of GameView
        GameView gameView = new GameView(testBackgroundPath, questions, mapType);

        // Access the JFrame using the parent component
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(gameView);

        // Expected frame size (adjust as needed)
        int expectedWidth = 1400;
        int expectedHeight = 900;

        // Allow some time for the frame to be properly set up
        try {
            Thread.sleep(500); // Sleep briefly to ensure the frame size is set
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("Frame width should match expected width", expectedWidth, frame.getWidth());
        Assert.assertEquals("Frame height should match expected height", expectedHeight, frame.getHeight());
        System.out.println("JFrame is set to the correct screen size.");
    }

    @Test
    public void testQuestionDisplay() {
        // Create dummy instances of Questions and a dummy image path
        Questions questions = new Questions();
        questions.addQuestion("What is the capital of France?", "Paris");
        String testBackgroundPath = "../Images/TestMap.png";
        String mapType = "testMap";

        // Create an instance of GameView
        GameView gameView = new GameView(testBackgroundPath, questions, mapType);

        // Access the question label from the GameView
        JLabel questionLabel = (JLabel) TestUtils.getChildNamed(gameView, "questionLabel");
        Assert.assertNotNull("Question label should not be null", questionLabel);

        // Verify that the question label displays the expected question
        String displayedText = questionLabel.getText();
        Assert.assertTrue("Displayed question should contain the question text", displayedText.contains("What is the capital of France?"));
        System.out.println("The question is correctly displayed.");
    }

    @Test
    public void testAnswerSubmission() {
        // Create dummy instances of Questions and a dummy image path
        Questions questions = new Questions();
        questions.addQuestion("What is the capital of France?", "Paris");
        String testBackgroundPath = "../Images/TestMap.png";
        String mapType = "testMap";

        // Create an instance of GameView
        GameView gameView = new GameView(testBackgroundPath, questions, mapType);

        // Access the text field and buttons
        JTextField answerField = (JTextField) TestUtils.getChildNamed(gameView, "answerField");
        JButton submitButton = (JButton) TestUtils.getChildNamed(gameView, "submitButton");
        JLabel feedbackLabel = (JLabel) TestUtils.getChildNamed(gameView, "feedbackLabel");

        Assert.assertNotNull("Answer field should not be null", answerField);
        Assert.assertNotNull("Submit button should not be null", submitButton);
        Assert.assertNotNull("Feedback label should not be null", feedbackLabel);

        // Simulate answering the question
        answerField.setText("Paris");
        submitButton.doClick(); // Simulate button click

        // Verify the feedback label shows correct feedback
        Assert.assertEquals("Feedback should indicate correct answer", "Correct!", feedbackLabel.getText());
        System.out.println("Answer submission and feedback work correctly.");
    }

}
