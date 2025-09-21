package com.emi;

import com.emi.controller.EmiController;
import java.util.Scanner;

/**
 * Main application entry point for EMI Payment System
 * Console-based application for managing EMI payments
 *
 * @author EMI Payment System
 * @version 1.0
 */
public class MainApp {

    public static void main(String[] args) {
        System.out.println("=== EMI PAYMENT SYSTEM ===");

        Scanner scanner = new Scanner(System.in);
        EmiController controller = new EmiController();

        try {
            // Main application loop
            boolean continueRunning = true;
            while (continueRunning) {
                System.out.println("\n1. Make Payment");
                System.out.println("2. Check Pending EMI");
                System.out.println("3. Exit");
                System.out.print("Choose an option (1-3): ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        controller.processPayment(scanner);
                        break;
                    case 2:
                        controller.checkPendingEmi(scanner);
                        break;
                    case 3:
                        continueRunning = false;
                        System.out.println("Thank you for using EMI Payment System!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
