import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InsertQuestions {

    // Database connection object
    static Connection conn = null;
    static PreparedStatement statement = null;

    public static void main(String[] args) {
        try {
            // Make a connection to the database
            makeDBConnection();

            // Define relative path
            String relativePath = "../team_boza/";

            // Insert categories
            insertCategories();

            // Insert data from the three text files (linking questions to categories)
            insertDataFromFile(relativePath + "questions/MathTimesTables.txt", getCategoryId("Math"));
            insertDataFromFile(relativePath + "questions/GeographyStateCap.txt", getCategoryId("Geography"));
            insertDataFromFile(relativePath + "questions/ChemistryPeriodicTable.txt", getCategoryId("Chemistry"));

            // Insert sample users
            insertUsers();

            // Close the statement and connection after insertion
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to establish a database connection
    public static void makeDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            // Modify the connection string with your database details
            conn = DriverManager.getConnection("jdbc:mysql://localhost:1521/OwenDB?user=o_mcgann&password=Changeme_00");
            if (conn != null) {
                System.out.println("Database connection successful");
            } else {
                System.out.println("Database connection failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert predefined categories
    // Method to insert predefined categories
    public static void insertCategories() throws SQLException {
        String checkCategoryQuery = "SELECT COUNT(*) FROM Categories WHERE name = ?";
        String insertCategoryQuery = "INSERT INTO Categories (name) VALUES (?)";

        statement = conn.prepareStatement(checkCategoryQuery);

        // Insert categories (Math, Geography, Chemistry)
        String[] categories = {"Math", "Geography", "Chemistry"};
        for (String category : categories) {
            // Check if the category already exists
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            rs.next(); // Move to the first row of the result set
            int count = rs.getInt(1);

            // If the category does not exist, insert it
            if (count == 0) {
                statement = conn.prepareStatement(insertCategoryQuery);
                statement.setString(1, category);
                statement.executeUpdate();
                System.out.println(category + " category inserted successfully!");
            } else {
                System.out.println(category + " category already exists, skipping insertion.");
            }
        }
    }


    // Method to get category ID based on category name
    public static int getCategoryId(String categoryName) throws SQLException {
        String query = "SELECT id FROM Categories WHERE name = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, categoryName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new SQLException("Category not found: " + categoryName);
        }
    }

    // Method to insert data from a text file into the database (linked with categories)
    public static void insertDataFromFile(String relativePath, int categoryId) {
        try (BufferedReader br = new BufferedReader(new FileReader(relativePath))) {
            String line;
            String insertQuestionQuery = "INSERT INTO Questions (question, answer, category_id) VALUES (?, ?, ?)";
            statement = conn.prepareStatement(insertQuestionQuery);

            // Read each line from the file and insert into the database
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String question = parts[1];
                    String answer = parts[2];

                    statement.setString(1, question);
                    statement.setString(2, answer);
                    statement.setInt(3, categoryId); // Link with category
                    statement.executeUpdate();
                }
            }
            System.out.println("Data from " + relativePath + " inserted successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert sample users into the database
    public static void insertUsers() throws SQLException {
        String insertUserQuery = "INSERT INTO Users (username) VALUES (?)";
        statement = conn.prepareStatement(insertUserQuery);

        // Add sample users
        String[] users = {"Player1", "Player2"};
        for (String user : users) {
            statement.setString(1, user);
            statement.executeUpdate();
        }
        System.out.println("Users inserted successfully!");
    }
}
