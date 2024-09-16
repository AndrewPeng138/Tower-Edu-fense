import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends JPanel {
    private Image backgroundImage;
    private Questions questions;
    private MapModel mapModel;
    private MapPanel mapPanel;
    private JLabel questionLabel;
    private JLabel questionCountLabel;  // Label to display total number of questions
    private JLabel answerResultLabel;   // Label to display correct/incorrect count
    private JLabel correctAnswersLabel;  // Label to display correct answer count
    private JLabel incorrectAnswersLabel;  // Label to display incorrect answer count
    private JTextField answerField;
    private JLabel feedbackLabel;
    private JLabel countdownLabel;  // Countdown/cool-down timer display
    private JLabel moneyLabel;  // Label to display the user's points/money
    private String currentQuestion;

    private Timer coolDownTimer;
    private Timer gameLoopTimer;

    private int coolDownSeconds = 5;
    private int points = 10000;  // Variable to track points/money
    private int sessionId = 1;  // Assuming each player has a session ID. In a real scenario, this would be dynamic.
    private int correctAnswers = 0;  // Track correct answers
    private int incorrectAnswers = 0;  // Track incorrect answers


    private Tile entranceTile;
    private Tile exitTile;
    private List<Tile> enemyPath;  // This will store the path of enemy tiles
    private Tile[][] locations;    // Reference to the map of tiles
    private Image enemyTileImage;


    // List to store enemies
    private List<EnemyModel> enemies = new ArrayList<>();

    // Tower costs
    private final int DEFAULT_TOWER_COST = 500;
    private final int BOAT_TOWER_COST = 1500;
    private final int HEAVY_TOWER_COST = 3000;
    private final int LIGHTNING_TOWER_COST = 4500;
    private final int FLAME_TOWER_COST = 2000;
    private final int BUGM3LT3R_TOWER_COST = 10000;

    // Towers
    private JButton defaultTowerButton;
    private JButton boatTowerButton;
    private JButton heavyTowerButton;
    private JButton lightningTowerButton;
    private JButton flameTowerButton;
    private JButton bugm3lt3rButton;


    public String selectedTower = null;  // To store the currently selected tower
    public int selectedTowerCost = 0;
    private String selectedTowerName = null;
    private String mapType;  // The selected map type (Easy, Medium, etc.)
    private String category; // The category of questions (Math, Geography, Chemistry)

    // ***** AUDIO PLAYERS *****
    // Background music
    WAVPlayer BGMUSIC_Player = new WAVPlayer("Audio/TE_BGMUSIC.wav");
    // Sound effect when bug dies [UNIMPLEMENTED]
    WAVPlayer bugDeath_Player = new WAVPlayer("Audio/bugDeath_SE.wav");
    // Sound effect when bug is hit [UNIMPLEMENTED]
    WAVPlayer bugHit_Player = new WAVPlayer("Audio/bugHit_SE.wav");
    // Sound effect when tower is bought [IMPLEMENTED IN placeTowerOnTile]
    WAVPlayer buyTower_Player = new WAVPlayer("Audio/buyTower_SE.wav");
    // Sound effect when a tower fires, several alternate sounds could be used [UNIMPLEMENTED]
    WAVPlayer fire_Player = new WAVPlayer("Audio/fire1_SE.wav");
    // Sound effect when the player runs out of health [UNIMPLEMENTED]
    WAVPlayer gameOver_Player = new WAVPlayer("Audio/gameOver_SE.wav");
    // Sound effect when the player beats all 20 waves [UNIMPLEMENTED]
    WAVPlayer levelWin_Player = new WAVPlayer("Audio/levelWin_SE.wav");
    // Sound effect when a question is answered correctly [UNIMPLEMENTED]
    WAVPlayer questionCorrect_Player = new WAVPlayer("Audio/questionCorrect_SE.wav");



    public GameView(String backgroundImagePath, Questions questions, String mapType) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.questions = questions;
        this.mapModel = new MapModel(mapType);
        this.backgroundImage = new ImageIcon(backgroundImagePath).getImage();
        this.mapType = mapType;

        // Load the total number of questions in the current category
        String category = questions.getClass().getSimpleName().replace("Questions", ""); // Extract category from the class name
        int questionCount = questions.getQuestionCountForCategory(category);
        System.out.println("Total questions in category '" + category + "': " + questionCount);

        // Set up the JFrame
        JFrame frame = new JFrame("Game View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeScreenView.setScreenSize(frame);
        setLayout(null);

        mapPanel = new MapPanel(mapModel.getLocations(), backgroundImagePath, this);
        mapPanel.setBounds(300, 0, 800, 750);
        add(mapPanel);

        // Initialize UI components with question count and session-based tracking
        initializeUI(questionCount);

        // Initialize enemies, start game loop, and other setups...
        frame.add(this);
        frame.setSize(1400, 900);
        frame.setVisible(true);
    }

    private void initializeUI(int questionCount) {
        // Display the total number of questions in the current category
        questionCountLabel = new JLabel("Total questions: " + questionCount);
        questionCountLabel.setBounds(10, 20, 300, 40);  // Increased height for better spacing
        questionCountLabel.setForeground(Color.WHITE);
        questionCountLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Bold and larger font
        add(questionCountLabel);

        // Label to display correct answers
        correctAnswersLabel = new JLabel("Correct answers: 0");
        correctAnswersLabel.setBounds(10, 70, 300, 40);  // Increased height and adjusted position
        correctAnswersLabel.setForeground(Color.WHITE);
        correctAnswersLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Bold and larger font
        add(correctAnswersLabel);

        // Label to display incorrect answers
        incorrectAnswersLabel = new JLabel("Incorrect answers: 0");
        incorrectAnswersLabel.setBounds(10, 120, 300, 40);  // Increased height and adjusted position
        incorrectAnswersLabel.setForeground(Color.WHITE);
        incorrectAnswersLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Bold and larger font
        add(incorrectAnswersLabel);

        // Get a random question to display
        currentQuestion = questions.getAnyQuestion();

        // Display the question
        JLabel questionTextLabel = new JLabel("Question:");
        questionTextLabel.setBounds(10, 170, 600, 50);  // Adjusted position for more spacing
        questionTextLabel.setForeground(Color.WHITE);
        questionTextLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Bold and larger font for "Question"
        add(questionTextLabel);

        questionLabel = new JLabel(currentQuestion);
        questionLabel.setBounds(10, 230, 600, 50);  // Adjusted position below "Question" label
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 20));  // Slightly larger font for the actual question
        add(questionLabel);

        // Text field for user input
        answerField = new JTextField();
        answerField.setBounds(10, 290, 300, 30);  // Adjusted position below the question
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
        feedbackLabel.setBounds(10, 340, 600, 40);  // Increased height and adjusted position
        feedbackLabel.setForeground(Color.WHITE);
        add(feedbackLabel);

        // Countdown label (for cool down)
        countdownLabel = new JLabel("");
        countdownLabel.setBounds(10, 380, 600, 40);  // Increased height and adjusted position
        countdownLabel.setForeground(Color.RED);
        add(countdownLabel);

        // Money label to display points
        JLabel moneyTextLabel = new JLabel("Money:");
        moneyTextLabel.setBounds(1120, 50, 200, 40);  // Positioned on the right side
        moneyTextLabel.setForeground(Color.WHITE);
        moneyTextLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Bold and larger font for "Money"
        add(moneyTextLabel);

        moneyLabel = new JLabel("0");  // Initial money is 0
        moneyLabel.setBounds(1220, 52, 200, 40);
        moneyLabel.setForeground(Color.WHITE);
        moneyLabel.setFont(new Font("Arial", Font.PLAIN, 20));  // Slightly larger font for the amount of money
        add(moneyLabel);

        // Create tower buttons
        createTowerButtons();
        updateTowerButtons();
    }


    private Tile findEntranceTile() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                if (locations[i][j].isEntrance()) {
                    return locations[i][j];
                }
            }
        }
        return null;
    }

    private Tile findExitTile() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                if (locations[i][j].isExit()) {
                    return locations[i][j];
                }
            }
        }
        return null;
    }
    private void findEnemyPath() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                if (locations[i][j].getType().equals("enemy")) {  // Check if the current tile is an enemy tile
                    enemyPath.add(locations[i][j]);  // Add this tile to the enemy path
                }
            }
        }
    }


    private void moveRoachToTile(Roach roach, Tile tile) {
        int row = getTileRow(tile);
        int col = getTileCol(tile);

        roach.moveTo(row, col);  // Move the roach to the tile's row and column
        // failed attempt at trying to redraw the roach after it moves
//        int tileWidth = mapPanel.getWidth() / mapModel.getLocations()[0].length;
//        int tileHeight = mapPanel.getHeight() / mapModel.getLocations().length;
//        int screenX = roach.getCurrentCol() * tileWidth;
//        int screenY = roach.getCurrentRow() * tileHeight;
//        roach.draw(g, screenX, screenY, tileWidth, tileHeight);
        mapPanel.repaint();      // Repaint the panel to show the updated position of the roach
    }
    private int getTileRow(Tile tile) {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                if (locations[i][j] == tile) {
                    return i;
                }
            }
        }
        return -1;  // In case the tile isn't found, return an invalid value
    }

    private int getTileCol(Tile tile) {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                if (locations[i][j] == tile) {
                    return j;
                }
            }
        }
        return -1;  // In case the tile isn't found, return an invalid value
    }

    private void startEnemyMovement() {
        if (enemies == null) {
            throw new IllegalStateException("Enemies list is not initialized");
        }
        new Thread(() -> {
            for (Tile tile : enemyPath) {
                moveRoachToTile((Roach) enemies.get(0), tile);  // Move the enemy (roach) to each tile in the path
                try {
                    Thread.sleep(500);  // Pause for 0.5 seconds between moves
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    // Method to handle placing a tower on a tile
    // Method to handle placing a tower on a tile
    public void placeTowerOnTile(int row, int col) {
        if (selectedTower != null && points >= selectedTowerCost) {
            // Adjust the row/column based on the selected tower type
            switch (selectedTower) {
                case "Cannon Tower":
                    row -= 0;  // Place DefaultTower at row-1
                    break;
                case "Boat Tower":
                    row -= 0;
                    break;
                case "Mortar Tower":
                    row -= 0;
                    break;
                case "Lightning Tower":
                    row -= 0;
                    break;
                case "Flame Tower":
                    row -= 0;
                    break;
                case "BUGM3LT3R":
                    row -= 0;
                    break;
                default:
                    // No adjustment for unknown tower types
                    break;
            }

            points -= selectedTowerCost;  // Deduct the cost
            updateMoneyLabel();  // Update the money label

            // Create a Tower object based on the selected tower
            Tower tower = new Tower(selectedTower, getTowerImagePath(selectedTower));
            mapPanel.placeTower(row, col, tower);  // Place the tower on the map
            // Play tower buy sound effect
            buyTower_Player.play();

            selectedTower = null;  // Reset selected tower
            updateTowerButtons();  // Update tower buttons
        } else {
            JOptionPane.showMessageDialog(null, "Not enough money to place " + selectedTower + "!");
        }
    }

    // Method to get the selected tower as a Tower object
    public Tower getSelectedTower() {
        if (selectedTower != null) {
            switch (selectedTower) {
                case "Cannon Tower":
                    return new Tower("Cannon Tower", "Images/TowerSprites/CannonTower.png");
                case "Boat Tower":
                    return new Tower("Boat Tower", "Images/TowerSprites/BoatTower.png");
                case "Mortar Tower":
                    return new Tower("Mortar Tower", "Images/TowerSprites/Mortar.png");
                case "Lightning Tower":
                    return new Tower("Lightning Tower", "Images/TowerSprites/LightningTower.png");
                case "Flame Tower":
                    return new Tower("Flame Tower", "Images/TowerSprites/FlameTower.png");
                case "BUGM3LT3R":
                    return new Tower("BUGM3LT3R", "Images/TowerSprites/BUGM3LT3R.png");
                default:
                    return null;
            }
        }
        return null;
    }


    // Method to get the image path of a tower based on its name
    private String getTowerImagePath(String towerName) {
        switch (towerName) {
            case "Cannon Tower":
                return "Images/TowerSprites/CannonTower.png";
            case "Boat Tower":
                return "Images/TowerSprites/BoatTower.png";
            case "Mortar Tower":
                return "Images/TowerSprites/Mortar.png";
            case "Lightning Tower":
                return "Images/TowerSprites/LightningTower.png";
            case "Flame Tower":
                return "Images/TowerSprites/FlameTower.png";
            case "BUGM3LT3R":
                return "Images/TowerSprites/BUGM3LT3R.png";
            default:
                return null;  // No image
        }
    }

    // Create tower buttons and place them under the money label
    private void createTowerButtons() {
        int baseY = 100;  // Base Y position under the money label
        int buttonHeight = 50;

        // Default Tower
        defaultTowerButton = createTowerButton("Cannon Tower", DEFAULT_TOWER_COST, 1120, baseY);
        add(defaultTowerButton);

        // Boat Tower (Disabled on Easy map)
        boatTowerButton = createTowerButton("Boat Tower", BOAT_TOWER_COST, 1120, baseY + buttonHeight);
        if (mapType.equalsIgnoreCase("Easy")) {
            boatTowerButton.setVisible(false);  // Hide if map type is "Easy"
        }
        add(boatTowerButton);

        // Heavy Tower
        heavyTowerButton = createTowerButton("Mortar Tower", HEAVY_TOWER_COST, 1120, baseY + 2 * buttonHeight);
        add(heavyTowerButton);

        // Lightning Tower
        lightningTowerButton = createTowerButton("Lightning Tower", LIGHTNING_TOWER_COST, 1120, baseY + 3 * buttonHeight);
        add(lightningTowerButton);

        // Flame Tower
        flameTowerButton = createTowerButton("Flame Tower", FLAME_TOWER_COST, 1120, baseY + 4 * buttonHeight);
        add(flameTowerButton);

        // BUGM3LT3R Tower
        bugm3lt3rButton = createTowerButton("BUGM3LT3R", BUGM3LT3R_TOWER_COST, 1120, baseY + 5 * buttonHeight);
        add(bugm3lt3rButton);
    }

    // Method to create a tower button with an action listener
    private JButton createTowerButton(String name, int cost, int x, int y) {
        JButton button = new JButton(name + " - $" + cost);
        button.setBounds(x, y, 200, 50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (points >= cost) {
                    selectedTower = name;  // Set the selected tower
                    System.out.println("Selected Tower: " + selectedTower); // Debug statement to check the selected tower

                    selectedTowerCost = cost;  // Set the tower cost
                    System.out.println(name + " selected");
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough money for " + name + "!");
                }
            }
        });
        return button;
    }

    // Update tower buttons, enabling/disabling them based on current points
    private void updateTowerButtons() {
        defaultTowerButton.setEnabled(points >= DEFAULT_TOWER_COST);
        boatTowerButton.setEnabled(points >= BOAT_TOWER_COST && !mapType.equalsIgnoreCase("Easy"));  // Only enabled if not "Easy" map
        heavyTowerButton.setEnabled(points >= HEAVY_TOWER_COST);
        lightningTowerButton.setEnabled(points >= LIGHTNING_TOWER_COST);
        flameTowerButton.setEnabled(points >= FLAME_TOWER_COST);
        bugm3lt3rButton.setEnabled(points >= BUGM3LT3R_TOWER_COST);
    }


    // Method to update the money label
    private void updateMoneyLabel() {
        moneyLabel.setText(String.valueOf(points));
    }


    // Update tower buttons, enabling/disabling them based on current points


    // Method to update the user's points and refresh the money label
    private void updatePoints(int amount) {
        points += amount;  // Add the specified amount to the current points
        updateMoneyLabel();  // Update the label with the new points
        updateTowerButtons();  // Update button states
    }


    // Method to spawn enemies at the start of the game
    private void spawnEnemies() {

        // Get the entrance position from the MapModel
        int startRow = mapModel.getEntranceRow();
        int startCol = mapModel.getEntranceCol();

        // Loops through 20 waves
        for (int i = 1; i < 21; i++) {
            Wave theWave = new Wave(i, mapModel);
            ArrayList<EnemyModel> waveList = theWave.getWave();
            System.out.println("We are on wave " + i);
            System.out.println("waveList.size() == " + waveList.size());

            for (EnemyModel enemy : waveList) {
                // Set the starting position of the enemy to the entrance
                enemy.setCurrentRow(startRow);
                enemy.setCurrentCol(startCol);
                enemies.add(enemy);
            }
        }

    }



    // Start the game loop timer for continuous updates
    private void startGameLoop() {
        gameLoopTimer = new Timer();
        gameLoopTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                findEnemyPath();
                updateGame();  // Update game state
                repaint();  // Redraw the panel with updated enemy positions
            }
        }, 0, 100);  // Run every 100ms (10 times per second)
    }

    private void updateGame() {
        if (mapModel == null) {
            System.err.println("MapModel is not initialized");
            return;
        }

        // Move each enemy based on the tile map logic
        for (EnemyModel enemy : enemies)
        {
            if (enemy != null) {
                enemy.moveToNextEnemyTile(mapModel);  // Move based on map tiles
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Determine tile size based on the panel dimensions

        int tileWidth = mapPanel.getWidth() / mapModel.getLocations()[0].length;
        int tileHeight = mapPanel.getHeight() / mapModel.getLocations().length;

        // Draw each enemy based on their tile positions
        for (EnemyModel enemy : enemies) {
            if (enemy instanceof Roach) {
                Roach roach = (Roach) enemy;
                // Convert map coordinates to screen coordinates
                int screenX = roach.getCurrentCol() * tileWidth;
                int screenY = roach.getCurrentRow() * tileHeight;
                roach.draw(g, screenX, screenY, tileWidth, tileHeight);  // Draw the roach
            }
        }
        if (enemies != null) {
            for (EnemyModel enemy : enemies) {
                // Draw each enemy
            }
        } else {
            System.out.println("Enemies list is null in paintComponent");
        }
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim();
        String correctAnswer = questions.getAnswer(currentQuestion);

        // Fetch the question ID based on the current question
        int questionId = questions.getQuestionId(currentQuestion);

        // Disable the answer field immediately when checking the answer
        answerField.setEnabled(false);

        boolean isCorrect = userAnswer.equalsIgnoreCase(correctAnswer);
        if (isCorrect) {
            feedbackLabel.setText("Correct!");
            updatePoints(100);  // Award points for correct answer
            correctAnswers++;  // Increment correct answer count
            // Log the player's correct answer
            int sessionId = 1; // You can dynamically fetch sessionId as needed
            questions.logPlayerAnswer(sessionId, questionId, true);

            // Move to the next question immediately after correct answer
            moveToNextQuestion();
            answerField.setEnabled(true);  // Re-enable answer field
        } else {
            feedbackLabel.setText("Incorrect. The correct answer is: " + correctAnswer);
            incorrectAnswers++;  // Increment incorrect answer count

            // Log the player's incorrect answer
            int sessionId = 1; // You can dynamically fetch sessionId as needed
            questions.logPlayerAnswer(sessionId, questionId, false);

            // Start the cool-down timer and wait for 5 seconds before moving to the next question
            startCoolDown();  // Start the cool-down and prevent switching question during the cool-down
        }

        // Update the answer result labels
        updateAnswerResultLabel();  // This will update correct/incorrect answers
    }


    private void updateAnswerResultLabel() {
        // Update the correct and incorrect answers labels
        correctAnswersLabel.setText("Correct answers: " + correctAnswers);
        incorrectAnswersLabel.setText("Incorrect answers: " + incorrectAnswers);
    }



    private void startCoolDown() {
        coolDownSeconds = 5;
        countdownLabel.setText("Wait for " + coolDownSeconds + " seconds...");

        // Disable input during cool down
        answerField.setEnabled(false);

        coolDownTimer = new Timer();
        coolDownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (coolDownSeconds > 0) {
                    countdownLabel.setText("Wait for " + coolDownSeconds + " seconds...");
                    coolDownSeconds--;
                } else {
                    // After the cool-down ends, switch to the next question
                    countdownLabel.setText("");
                    coolDownTimer.cancel();
                    moveToNextQuestion();  // Switch to the next question only after the cool-down
                    answerField.setEnabled(true);  // Re-enable input after the cool-down
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



}