import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public abstract class Questions {
    protected HashMap<String, String> questionMap;
    private Random random = new Random();  // Random object for selecting questions

    // Database connection variables
    private static final String DB_URL = "jdbc:mysql://localhost:1521/OwenDB";
    private static final String USER = "o_mcgann";
    private static final String PASS = "Changeme_00";

    // Constructor that takes a category (Chemistry, Math, Geography)
    public Questions(String category) {
        questionMap = new HashMap<>();
        loadQuestionsFromDatabase(category);  // Load questions based on category from the database
    }

    // Method to load questions from the database based on the category
    protected void loadQuestionsFromDatabase(String category) {
        String query = "SELECT question, answer FROM Questions WHERE category = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the category in the SQL query
            stmt.setString(1, category);

            // Execute the query and load the results into the HashMap
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                questionMap.put(question, answer);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setQuestion(String question, String answer) {
        questionMap.put(question, answer);
    }

    public String getAnswer(String question) {
        return questionMap.get(question);
    }

    // Method to randomly retrieve any question
    public String getAnyQuestion() {
        if (questionMap.isEmpty()) {
            return null; // Return null if no questions are available
        }
        List<String> keys = new ArrayList<>(questionMap.keySet());
        return keys.get(random.nextInt(keys.size())); // Randomly select a question
    }
}
