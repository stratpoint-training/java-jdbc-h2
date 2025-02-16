package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import org.h2.tools.Server;

public class SimpleJDBC {
    private static String JDBC_URL;
    private static String USER;
    private static String PASSWORD;
    private static int H2_PORT;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Load application.properties
        loadConfig();

        // Start H2 Console
        startH2Console();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Create table if not exists
            stmt.execute("CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))");
            System.out.println("✅ Table 'users' created!\n");

            while (true) {
                System.out.println("📌 Choose an action:");
                System.out.println("1️⃣ Insert User");
                System.out.println("2️⃣ View Users");
                System.out.println("3️⃣ Update User");
                System.out.println("4️⃣ Delete User");
                System.out.println("5️⃣ Exit");
                System.out.print("👉 Enter choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        insertUser(conn);
                        break;
                    case 2:
                        viewUsers(conn);
                        break;
                    case 3:
                        updateUser(conn);
                        break;
                    case 4:
                        deleteUser(conn);
                        break;
                    case 5:
                        System.out.println("👋 Exiting... Goodbye!");
                        return;
                    default:
                        System.out.println("❌ Invalid choice! Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load Configuration from application.properties
    private static void loadConfig() {
        Properties properties = new Properties();
        try (InputStream input = SimpleJDBC.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("⚠️ Unable to find application.properties!");
                return;
            }
            properties.load(input);
            JDBC_URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");
            H2_PORT = Integer.parseInt(properties.getProperty("h2.console.port"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Start H2 Web Console
    private static void startH2Console() {
        try {
            Server.createWebServer("-web", "-webPort", String.valueOf(H2_PORT)).start();
            System.out.println("🚀 H2 Console started at: http://localhost:" + H2_PORT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert User
    private static void insertUser(Connection conn) {
        System.out.print("👤 Enter name: ");
        String name = scanner.nextLine();
        String sql = "INSERT INTO users (name) VALUES (?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("✅ User added successfully!\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Users
    private static void viewUsers(Connection conn) {
        String sql = "SELECT * FROM users";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n📋 User List:");
            while (rs.next()) {
                System.out.println("🆔 " + rs.getInt("id") + " | 👤 " + rs.getString("name"));
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update User
    private static void updateUser(Connection conn) {
        System.out.print("🆔 Enter user ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("✏️ Enter new name: ");
        String newName = scanner.nextLine();
        String sql = "UPDATE users SET name = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ User updated successfully!\n");
            } else {
                System.out.println("❌ User not found!\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete User
    private static void deleteUser(Connection conn) {
        System.out.print("🆔 Enter user ID to delete: ");
        int id = scanner.nextInt();
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("🗑️ User deleted successfully!\n");
            } else {
                System.out.println("❌ User not found!\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
