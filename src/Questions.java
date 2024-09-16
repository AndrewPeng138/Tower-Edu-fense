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

    // Method to load questions from the database based on the category name
    protected void loadQuestionsFromDatabase(String category) {
        // Query to join Questions with Categories and Answers based on category name
        String query = "SELECT q.id, q.question, a.answer " +
                "FROM Questions q " +
                "JOIN Categories c ON q.category_id = c.id " +
                "JOIN Answers a ON q.id = a.question_id " +
                "WHERE c.name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the category in the SQL query
            stmt.setString(1, category);

            // Execute the query and load the results into the HashMap
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                questionMap.put(question, answer);  // Store the question-answer pairs
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

    public int getQuestionCountForCategory(String category) {
        int count = 0;
        String query = "SELECT COUNT(q.id) AS question_count " +
                "FROM Questions q " +
                "JOIN Categories c ON q.category_id = c.id " +
                "WHERE c.name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category);  // Make sure category is correctly passed here
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("question_count");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    // New method to log player answers
    public void logPlayerAnswer(int sessionId, int questionId, boolean isCorrect) {
        String query = "INSERT INTO PlayerAnswers (session_id, question_id, is_correct) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, sessionId);  // Set the session ID
            stmt.setInt(2, questionId);  // Set the question ID
            stmt.setBoolean(3, isCorrect);  // Set if the answer was correct or not

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getQuestionId(String question) {
        int questionId = -1;  // Default value if question is not found

        String query = "SELECT id FROM Questions WHERE question = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, question);  // Set the question text in the SQL query

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                questionId = rs.getInt("id");  // Get the question ID from the result set
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionId;  // Return the question ID (or -1 if not found)
    }

}
