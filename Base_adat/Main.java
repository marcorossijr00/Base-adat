import java.sql.*;
import java.util.Scanner;

class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

class UserDAO {
    public static void addUser(String name, String email) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Felhasználó hozzáadva.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listUsers() {
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("name") + " - " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(int id, String name, String email) {
        String sql = "UPDATE users SET name=?, email=? WHERE id=?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("Felhasználó frissítve.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Felhasználó törölve.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Felhasználó hozzáadása");
            System.out.println("2. Felhasználók listázása");
            System.out.println("3. Felhasználó frissítése");
            System.out.println("4. Felhasználó törlése");
            System.out.println("5. Kilépés");
            System.out.print("Válassz egy műveletet: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Név: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    UserDAO.addUser(name, email);
                    break;
                case 2:
                    UserDAO.listUsers();
                    break;
                case 3:
                    System.out.print("Módosítandó felhasználó ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Új név: ");
                    String newName = scanner.nextLine();
                    System.out.print("Új email: ");
                    String newEmail = scanner.nextLine();
                    UserDAO.updateUser(id, newName, newEmail);
                    break;
                case 4:
                    System.out.print("Törlendő felhasználó ID: ");
                    int deleteId = scanner.nextInt();
                    UserDAO.deleteUser(deleteId);
                    break;
                case 5:
                    System.out.println("Kilépés...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Érvénytelen választás.");
            }
        }
    }
}
