import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

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
    private Timer gameLoopTimer;
    private int coolDownSeconds = 10;
    private int points = 0;  // Variable to track points/money
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

    private String selectedTower = null;  // To store the currently selected tower
    private int selectedTowerCost = 0;

    private String mapType;  // The type of map

    public GameView(String backgroundImagePath, Questions questions, String mapType) {
        this.questions = questions;
        this.mapModel = new MapModel(mapType);
        this.backgroundImage = new ImageIcon(backgroundImagePath).getImage();
        this.locations = new MapModel(mapType).getLocations();  // Initialize the map (locations)
        this.enemyPath = new ArrayList<>();
        this.mapType = mapType;
        this.enemies = new ArrayList<>();
        System.out.println("Adding enemy...");
        enemies.add(new Roach(mapModel, 0, 14));  // Example: adding an enemy
        System.out.println("Enemy added. Size: " + enemies.size());

        // Set up the JFrame
        JFrame frame = new JFrame("Game View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeScreenView.setScreenSize(frame);
        setLayout(null);

        mapPanel = new MapPanel(locations, backgroundImagePath, this);
        mapPanel.setBounds(300, 0, 800, 750);
        add(mapPanel);

        // Initialize and set up UI components
        initializeUI();

        frame.add(this);
        frame.setSize(1400, 900);
        frame.setVisible(true);

        entranceTile = findEntranceTile(); // Find entrance tile
        exitTile = findExitTile(); // Find exit tile

        // If entrance and exit exist, find the enemy path
//        if (entranceTile != null && exitTile != null) {
//            findEnemyPath();
//        }



        int x = 5; // Example value, replace with the actual x-coordinate
        int y = 3; // Example value, replace with the actual y-coordinate
        boolean isEntrance = false; // Set this based on whether this is the entrance
        boolean isExit = false; // Set this based on whether this is the exit

        Tile enemyTile = new EnemyTile(x, y, isEntrance, isExit);



        // Initialize the UI and start enemy movement
        initializeUI();
        findEnemyPath();
        startEnemyMovement();
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
        answerField.setBounds(10, 160, 300, 30);
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
        Tile currentTile  = findEntranceTile();
        while(!currentTile.isExit()) {
            enemyPath.add(currentTile);
            int row = currentTile.getRow();
            int col = currentTile.getCol();
            Tile below = null;
            Tile right = null;
            Tile left = null;
            if (row + 1 < locations.length) {
                below = locations[row + 1][col];
            }
            if (col+1 < locations[row].length){
                right = locations[row][col+1];
            }
            if (col-1 >= 0){
                left = locations[row][col-1];
            }
            if(below != null && below.isEnemyTile() && !enemyPath.contains(below)) {
                currentTile = below;
                continue;
            }
            if(right != null && right.isEnemyTile() && !enemyPath.contains(right)) {
                currentTile = right;
                continue;
            }
            if(left != null && left.isEnemyTile() && !enemyPath.contains(left)) {
                currentTile = left;
                continue;
            }
        }
        // adding exit tile
        enemyPath.add(currentTile);
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
                case "Default Tower":
                    row -= 1;  // Place DefaultTower at row-1
                    break;
                case "Boat Tower":
                    row -= 2;  // Place BoatTower at row-2
                    break;
                case "Heavy Tower":
                    row -= 1;
                    break;
                case "Lightning Tower":
                    row -= 2;
                    break;
                case "Flame Tower":
                    row -= 2;
                    break;
                case "BUGM3LT3R":
                    row -= 1;
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

            selectedTower = null;  // Reset selected tower
            updateTowerButtons();  // Update tower buttons
        } else {
            JOptionPane.showMessageDialog(null, "Not enough money to place " + selectedTower + "!");
        }
    }

    // Method to get the image path of a tower based on its name
    private String getTowerImagePath(String towerName) {
        switch (towerName) {
            case "Default Tower":
                return "Images/TowerSprites/Default projectile.png";
            case "Boat Tower":
                return "Images/TowerSprites/Boat tower.png";
            case "Heavy Tower":
                return "Images/TowerSprites/Cannon tower.png";
            case "Lightning Tower":
                return "Images/TowerSprites/Lightning tower.png";
            case "Flame Tower":
                return "Images/TowerSprites/Flame tower.png";
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
        defaultTowerButton = createTowerButton("Default Tower", DEFAULT_TOWER_COST, 1120, baseY);
        add(defaultTowerButton);

        // Boat Tower (Disabled on Easy map)
        boatTowerButton = createTowerButton("Boat Tower", BOAT_TOWER_COST, 1120, baseY + buttonHeight);
        if (mapType.equalsIgnoreCase("Easy")) {
            boatTowerButton.setVisible(false);  // Hide if map type is "Easy"
        }
        add(boatTowerButton);

        // Heavy Tower
        heavyTowerButton = createTowerButton("Heavy Tower", HEAVY_TOWER_COST, 1120, baseY + 2 * buttonHeight);
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
    // Method to spawn enemies at the start of the game
    private void spawnEnemies() {
        // Example: Add Roach at a specific tile (row, col)
        int startRow = 0;  // Starting row based on map coordinates
        int startCol = 14; // Starting column based on map coordinates
        enemies.add(new Roach(mapModel, startRow, startCol));
        // Pass the tile coordinates to the enemy
    }


    // Start the game loop timer for continuous updates
    private void startGameLoop() {
        gameLoopTimer = new Timer();
        gameLoopTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
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
        for (EnemyModel enemy : enemies) {
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
                roach.draw(g, screenX, screenY, tileWidth, tileHeight);  // Pass tile sizes to draw the roach properly
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


}
