import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

            // Insert data from the three text files
            insertDataFromFile(relativePath + "questions/MathTimesTables.txt");
            insertDataFromFile(relativePath + "questions/GeographyStateCap.txt");
            insertDataFromFile(relativePath + "questions/ChemistryPeriodicTable.txt");

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

    // Method to read data from a text file and insert it into the database
    public static void insertDataFromFile(String relativePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(relativePath))) {
            String line;
            String insertQuery = "INSERT INTO Questions (category, question, answer) VALUES (?, ?, ?)";
            statement = conn.prepareStatement(insertQuery);

            // Read each line from the file and insert into the database
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String category = parts[0];
                    String question = parts[1];
                    String answer = parts[2];

                    statement.setString(1, category);
                    statement.setString(2, question);
                    statement.setString(3, answer);
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
}
