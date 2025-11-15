package com.se310.store.cli;

import com.se310.store.config.ConfigLoader;
import com.se310.store.data.DataManager;
import com.se310.store.model.*;
import com.se310.store.repository.UserRepository;
import com.se310.store.service.AuthenticationService;
import com.se310.store.service.StoreService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.Scanner;

/**
 * StoreManagementCLIClient - Command-line interface for Store Management System
 *
 * This CLI client provides a hybrid interface:
 * - Authentication uses AuthenticationService (direct service access)
 * - Store and User operations use REST API (via HTTP)
 * - All other operations (Products, Customers, etc.) use StoreService directly
 *
 * This demonstrates both API-based and direct service access patterns.
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-11-11
 */
public class StoreManagementCLIClient {

    //TODO: Implement View for Store operations, part of the MVC Pattern

    private static final String BASE_URL = ConfigLoader.getApiBaseUrl();
    private static String authHeader = null;
    private static final Scanner scanner = new Scanner(System.in);

    // Direct service access
    private static StoreService storeService;
    private static AuthenticationService authenticationService;

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║   Smart Store Management CLI Client           ║");
        System.out.println("║   (Hybrid: API + Direct Service Access)       ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.println();

        // Initialize local services
        initializeServices();

        // Authenticate user using AuthenticationService
        if (!authenticate()) {
            System.out.println("Authentication failed. Exiting...");
            return;
        }

        // Main menu loop
        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    manageStores();
                    break;
                case "2":
                    manageUsers();
                    break;
                case "3":
                    manageProducts();
                    break;
                case "4":
                    manageCustomers();
                    break;
                case "5":
                    viewDocumentation();
                    break;
                case "6":
                    logout();
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Initialize local services for direct access
     */
    private static void initializeServices() {
        try {
            // Initialize DataManager and repositories
            DataManager dataManager = DataManager.getInstance();
            UserRepository userRepository = new UserRepository(dataManager);

            // Initialize services
            storeService = new StoreService();
            authenticationService = new AuthenticationService(userRepository);

            System.out.println("✓ Local services initialized");
            System.out.println();
        } catch (Exception e) {
            System.err.println("Error initializing services: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Authenticate user using AuthenticationService
     */
    private static boolean authenticate() {
        System.out.println("Please login");
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        // Create Basic Auth header for both service and API usage
        String credentials = email + ":" + password;
        authHeader = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        // Authenticate using AuthenticationService
        try {
            Optional<User> userOpt = authenticationService.authenticateBasic(authHeader);

            if (userOpt.isPresent()) {
                User user = userOpt.get();
                System.out.println("✓ Authentication successful!");
                System.out.println("  Welcome, " + user.getName() + " (" + user.getRole() + ")");
                System.out.println();
                return true;
            } else {
                System.out.println("✗ Authentication failed: Invalid credentials");
                return false;
            }
        } catch (Exception e) {
            System.out.println("✗ Authentication failed: " + e.getMessage());
            return false;
        }
    }

    private static void printMainMenu() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║              MAIN MENU                        ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║  1. Manage Stores (via API)                   ║");
        System.out.println("║  2. Manage Users (via API)                    ║");
        System.out.println("║  3. Manage Products (via Service)             ║");
        System.out.println("║  4. Manage Customers (via Service)            ║");
        System.out.println("║  5. View API Documentation                    ║");
        System.out.println("║  6. Logout (Switch User)                      ║");
        System.out.println("║  0. Exit                                      ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }

    /**
     * Store operations - Uses REST API
     */
    private static void manageStores() {
        System.out.println("\n=== Store Management (via REST API) ===");
        System.out.println("1. List all stores");
        System.out.println("2. View store details");
        System.out.println("3. Create new store");
        System.out.println("4. Update store");
        System.out.println("5. Delete store");
        System.out.println("0. Back to main menu");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine().trim();
    }

    /**
     * User operations - Uses REST API
     */
    private static void manageUsers() {
        System.out.println("\n=== User Management (via REST API) ===");
        System.out.println("1. List all users");
        System.out.println("2. View user details");
        System.out.println("3. Register new user");
        System.out.println("0. Back to main menu");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine().trim();

    }

    /**
     * Product operations - Uses StoreService directly
     */
    private static void manageProducts() {
        System.out.println("\n=== Product Management (via StoreService) ===");
        System.out.println("1. View product");
        System.out.println("2. Create new product");
        System.out.println("0. Back to main menu");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine().trim();

    }

    /**
     * Customer operations - Uses StoreService directly
     */
    private static void manageCustomers() {
        System.out.println("\n=== Customer Management (via StoreService) ===");
        System.out.println("1. View customer");
        System.out.println("2. Register new customer");
        System.out.println("0. Back to main menu");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine().trim();

    }

    private static void viewDocumentation() {
        System.out.println("\n=== API Documentation ===");
        int port = ConfigLoader.getServerPort();
        System.out.println("API Documentation URL: http://localhost:" + port + "/api/docs");
        System.out.println("OpenAPI Spec: http://localhost:" + port + "/api/docs/openapi.yaml");
        System.out.println("\nArchitecture:");
        System.out.println("  - Store/User operations: REST API (HTTP)");
        System.out.println("  - Other operations: Direct StoreService access");
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Logout and switch to a different user
     */
    private static void logout() {
        System.out.println("\n=== Logout ===");
        System.out.println("Logging out current user...");

        // Clear authentication
        authHeader = null;

        System.out.println("✓ Logged out successfully\n");

        // Re-authenticate with new credentials
        while (!authenticate()) {
            System.out.println("\nAuthentication failed. Please try again.\n");
        }
    }

    /**
     * Send HTTP request to REST API (for Store and User operations)
     */
    private static String sendRequest(String method, String endpoint, String body) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Authorization", authHeader);
        conn.setRequestProperty("Content-Type", "application/json");

        if (body != null) {
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }

        StringBuilder response = getStringBuilder(conn);

        return response.toString();
    }

    private static StringBuilder getStringBuilder(HttpURLConnection conn) throws Exception {
        int responseCode = conn.getResponseCode();

        BufferedReader reader;
        if (responseCode >= 200 && responseCode < 300) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
        }

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        if (responseCode < 200 || responseCode >= 300) {
            throw new Exception("HTTP " + responseCode + ": " + response);
        }
        return response;
    }
}