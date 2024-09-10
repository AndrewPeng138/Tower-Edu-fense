import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JPanel {
    private Image backgroundImage;
    private Questions questions; // The questions passed to this view
    private JFrame frame;
    private JLabel questionLabel;
    private JTextField answerField;  // Text field for the user to input the answer
    private JButton submitButton;    // Submit button to check the answer
    private JButton nextButton;      // Next button to move to the next question
    private JLabel feedbackLabel;    // Label to show feedback to the user
    private String currentQuestion;  // Store the current question
    private boolean isAnswerChecked; // Flag to track if the answer has been checked

    public GameView(String backgroundImagePath, Questions questions) {
        this.questions = questions;
        backgroundImage = new ImageIcon(backgroundImagePath).getImage();

        frame = new JFrame("Quiz Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeScreenView.setScreenSize(frame);
        setLayout(null);

        // Initialize the UI components
        initializeUI();

        frame.add(this);
        frame.setVisible(true);
    }

    private void initializeUI() {
        // Get a random question to display
        currentQuestion = questions.getAnyQuestion();

        // Display the question
        questionLabel = new JLabel("Question: " + currentQuestion);
        questionLabel.setBounds(0, 100, 600, 50);  // Set position of the question label
        questionLabel.setForeground(Color.WHITE);    // Set text color to white
        add(questionLabel);

        // Text field for the user to input their answer
        answerField = new JTextField();
        answerField.setBounds(0, 200, 300, 30);   // Set position and size of the answer field
        add(answerField);

        // Submit button to check the answer
        submitButton = new JButton("Submit");
        submitButton.setBounds(0, 250, 150, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();  // Method to check if the answer is correct
            }
        });
        add(submitButton);

        // Next button to move to the next question
        nextButton = new JButton("Next");
        nextButton.setBounds(150, 250, 150, 30);
        nextButton.setEnabled(false); // Initially disabled
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToNextQuestion();  // Method to move to the next question
            }
        });
        add(nextButton);

        // Feedback label to show whether the answer is correct or not
        feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(0, 300, 600, 30);  // Position for feedback
        feedbackLabel.setForeground(Color.WHITE);
        add(feedbackLabel);
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim();  // Get the user's input and trim any extra spaces
        String correctAnswer = questions.getAnswer(currentQuestion);  // Get the correct answer to the current question

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            feedbackLabel.setText("Correct!");
        } else {
            feedbackLabel.setText("Incorrect. The correct answer is: " + correctAnswer);
        }

        // Enable the "Next" button after checking the answer
        nextButton.setEnabled(true);
        submitButton.setEnabled(false);  // Disable the submit button after checking
        isAnswerChecked = true;
    }

    private void moveToNextQuestion() {
        // Get the next question
        currentQuestion = questions.getAnyQuestion();

        if (currentQuestion != null) {
            questionLabel.setText("Question: " + currentQuestion); // Update the question label with the new question
            answerField.setText(""); // Clear the answer field
            feedbackLabel.setText(""); // Clear the feedback label
            submitButton.setEnabled(true); // Enable the submit button
            nextButton.setEnabled(false); // Disable the next button until the answer is checked
        } else {
            questionLabel.setText("No more questions available.");
            answerField.setEnabled(false); // Disable the answer field if no more questions are available
            submitButton.setEnabled(false); // Disable the submit button if no more questions are available
            nextButton.setEnabled(false); // Disable the next button if no more questions are available
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        // For testing purposes, pass in a background and sample questions
    }
}
