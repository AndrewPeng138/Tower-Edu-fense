import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends JPanel {
    private Image backgroundImage;
    private Questions questions;
    private MapModel mapModel;
    private MapPanel mapPanel;
    private JLabel questionLabel;
    private JTextField answerField;
    private JLabel feedbackLabel;
    private JLabel countdownLabel;  // Countdown/cool-down timer display
    private JLabel moneyLabel;  // Label to display the user's points/money
    private String currentQuestion;

    private Timer coolDownTimer;
    private int coolDownSeconds = 10;
    private int points = 0;  // Variable to track points/money

    public GameView(String backgroundImagePath, Questions questions, String mapType) {
        this.questions = questions;
        this.mapModel = new MapModel(mapType);
        this.backgroundImage = new ImageIcon(backgroundImagePath).getImage();

        // Set up the JFrame
        JFrame frame = new JFrame("Game View");
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
        JLabel questionTextLabel = new JLabel("Question:");
        questionTextLabel.setBounds(10, 50, 600, 50);
        questionTextLabel.setForeground(Color.WHITE);
        questionTextLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Bold and larger font for "Question"
        add(questionTextLabel);

        questionLabel = new JLabel(currentQuestion);
        questionLabel.setBounds(10, 100, 600, 50);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 20));  // Slightly larger font for the actual question
        add(questionLabel);

        // Text field for user input
        answerField = new JTextField();
        answerField.setBounds(0, 160, 300, 30);
        add(answerField);

        // Set key listener for "Enter" key to submit the answer
        answerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkAnswer();
                }
            }
        });

        // Feedback label
        feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(10, 240, 600, 30);
        feedbackLabel.setForeground(Color.WHITE);
        add(feedbackLabel);

        // Countdown label (for cool down)
        countdownLabel = new JLabel("");
        countdownLabel.setBounds(10, 280, 600, 30);
        countdownLabel.setForeground(Color.RED);
        add(countdownLabel);

        // Money label to display points
        JLabel moneyTextLabel = new JLabel("Money:");
        moneyTextLabel.setBounds(1120, 50, 200, 30);  // Positioned on the right side
        moneyTextLabel.setForeground(Color.WHITE);
        moneyTextLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Bold and larger font for "Money"
        add(moneyTextLabel);

        moneyLabel = new JLabel("0");  // Initial money is 0
        moneyLabel.setBounds(1220, 52, 200, 30);
        moneyLabel.setForeground(Color.WHITE);
        moneyLabel.setFont(new Font("Arial", Font.PLAIN, 20));  // Slightly larger font for the amount of money
        add(moneyLabel);
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim();
        String correctAnswer = questions.getAnswer(currentQuestion);

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            feedbackLabel.setText("Correct!");
            updatePoints(100);  // Award 100 points for correct answer
            moveToNextQuestion();  // Automatically move to the next question if the answer is correct
        } else {
            feedbackLabel.setText("Incorrect. The correct answer is: " + correctAnswer);
            startCoolDown();  // Start the 10-second cool down if the answer is incorrect
        }
    }

    private void startCoolDown() {
        coolDownSeconds = 10;
        countdownLabel.setText("Wait for " + coolDownSeconds + " seconds...");

        // Disable input during cool down if the answer is incorrect
        answerField.setEnabled(false);

        coolDownTimer = new Timer();
        coolDownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (coolDownSeconds > 0) {
                    countdownLabel.setText("Wait for " + coolDownSeconds + " seconds...");
                    coolDownSeconds--;
                } else {
                    // Re-enable input after the cool-down period
                    countdownLabel.setText("");
                    coolDownTimer.cancel();
                    moveToNextQuestion();  // Automatically move to the next question after the cool down
                }
            }
        }, 0, 1000);  // Execute every 1 second
    }

    private void moveToNextQuestion() {
        currentQuestion = questions.getAnyQuestion();

        if (currentQuestion != null) {
            questionLabel.setText(currentQuestion);
            answerField.setText("");  // Clear the input field for the next question
            answerField.setEnabled(true);  // Ensure the answer field is enabled for the next question
            answerField.requestFocus();  // Set focus to the answerField so user can type immediately
        } else {
            questionLabel.setText("No more questions available.");
            answerField.setEnabled(false);
        }
    }

    // Method to update the user's points and refresh the money label
    private void updatePoints(int amount) {
        points += amount;  // Add the specified amount to the current points
        moneyLabel.setText(String.valueOf(points));  // Update the label with the new points
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
