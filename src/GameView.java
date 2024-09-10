import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JPanel {
    private Image backgroundImage;
    private Questions questions;
    private MapModel mapModel;
    private MapPanel mapPanel;
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JButton nextButton;
    private JLabel feedbackLabel;
    private String currentQuestion;
    private boolean isAnswerChecked;

    public GameView(String backgroundImagePath, Questions questions, String mapType) {
        this.questions = questions;
        this.mapModel = new MapModel(mapType);
        this.backgroundImage = new ImageIcon(backgroundImagePath).getImage();

        // Set up the JFrame
        JFrame  frame = new JFrame("Game View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeScreenView.setScreenSize(frame);
        setLayout(null);

        // Create and set up the map panel
        mapPanel = new MapPanel(mapModel.getLocations(), backgroundImagePath);
        mapPanel.setBounds(300, 0, 800, 750);
        add(mapPanel);

        // Initialize and set up UI components
        initializeUI();

        frame.add(this);
        frame.setSize(1400, 900);  // Adjusted size to fit components
        frame.setVisible(true);
    }

    private void initializeUI() {
        // Get a random question to display
        currentQuestion = questions.getAnyQuestion();

        // Display the question
        questionLabel = new JLabel("Question: " + currentQuestion);
        questionLabel.setBounds(10, 100, 600, 50);
        questionLabel.setForeground(Color.WHITE);
        add(questionLabel);

        // Text field for user input
        answerField = new JTextField();
        answerField.setBounds(10, 160, 300, 30);
        add(answerField);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setBounds(10, 200, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
        add(submitButton);

        // Next button
        nextButton = new JButton("Next");
        nextButton.setBounds(120, 200, 100, 30);
        nextButton.setEnabled(false);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToNextQuestion();
            }
        });
        add(nextButton);

        // Feedback label
        feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(10, 240, 600, 30);
        feedbackLabel.setForeground(Color.WHITE);
        add(feedbackLabel);
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim();
        String correctAnswer = questions.getAnswer(currentQuestion);

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            feedbackLabel.setText("Correct!");
        } else {
            feedbackLabel.setText("Incorrect. The correct answer is: " + correctAnswer);
        }

        // Enable the "Next" button after checking the answer
        nextButton.setEnabled(true);
        submitButton.setEnabled(false);
        isAnswerChecked = true;
    }

    private void moveToNextQuestion() {
        currentQuestion = questions.getAnyQuestion();

        if (currentQuestion != null) {
            questionLabel.setText("Question: " + currentQuestion);
            answerField.setText("");
            feedbackLabel.setText("");
            submitButton.setEnabled(true);
            nextButton.setEnabled(false);
        } else {
            questionLabel.setText("No more questions available.");
            answerField.setEnabled(false);
            submitButton.setEnabled(false);
            nextButton.setEnabled(false);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
