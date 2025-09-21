package com.emi.controller;

import com.emi.service.EmiService;
import com.emi.service.EmiServiceImpl;
import com.emi.model.Receivable;
import com.emi.model.ReceiptPayment;
import com.emi.model.Allocation;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Controller class for handling console-based user interactions
 * Manages the flow of EMI payment operations through console interface
 *
 * @author EMI Payment System
 * @version 1.0
 */
public class EmiController {

    private EmiService emiService;
    private Scanner scanner;

    public EmiController() {
        this.emiService = new EmiServiceImpl();
    }

    /**
     * Process payment through console interaction
     *
     * @param scanner Scanner object for user input
     */
    public void processPayment(Scanner scanner) {
        try {
            System.out.println("\n=== PAYMENT PROCESSING ===");

            // Get loan account number
            System.out.print("Enter Loan Account Number: ");
            String loanAccountNo = scanner.nextLine().trim();

            // Get pending EMI details
            Receivable receivable = emiService.getPendingEmiDetails(loanAccountNo);

            if (receivable == null) {
                System.out.println("No pending EMI found for loan account: " + loanAccountNo);
                return;
            }

            // Display pending EMI details
            displayPendingEmiDetails(receivable);

            // Get payment amount
            System.out.print("Enter Payment Amount: ");
            BigDecimal paymentAmount = scanner.nextBigDecimal();
            scanner.nextLine(); // Consume newline

            // Get payment mode
            System.out.print("Enter Payment Mode (CASH/CHEQUE/ONLINE): ");
            String paymentMode = scanner.nextLine().trim().toUpperCase();

            // Validate payment mode
            if (!isValidPaymentMode(paymentMode)) {
                System.out.println("Invalid payment mode. Please use CASH, CHEQUE, or ONLINE.");
                return;
            }

            // Process payment
            ReceiptPayment receipt = emiService.processPayment(loanAccountNo, paymentAmount, paymentMode);

            // Display success message
            displayPaymentSuccess(receipt, receivable);

        } catch (IllegalArgumentException e) {
            System.out.println("Payment Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Check pending EMI details through console interaction
     *
     * @param scanner Scanner object for user input
     */
    public void checkPendingEmi(Scanner scanner) {
        try {
            System.out.println("\n=== PENDING EMI CHECK ===");

            // Get loan account number
            System.out.print("Enter Loan Account Number: ");
            String loanAccountNo = scanner.nextLine().trim();

            // Get pending EMI details
            Receivable receivable = emiService.getPendingEmiDetails(loanAccountNo);

            if (receivable == null) {
                System.out.println("No pending EMI found for loan account: " + loanAccountNo);
                return;
            }

            // Display pending EMI details
            displayPendingEmiDetails(receivable);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Display pending EMI details
     *
     * @param receivable Receivable object containing EMI details
     */
    private void displayPendingEmiDetails(Receivable receivable) {
        System.out.println("\n=== PENDING EMI DETAILS ===");
        System.out.println("Loan Account Number: " + receivable.getLoanAccountNo());
        System.out.println("Pending EMI: " + formatAmount(receivable.getEmiAmount()));
        System.out.println("Penalty Charges: " + formatAmount(receivable.getPenalty()));
        System.out.println("Total Amount: " + formatAmount(receivable.getTotalAmount()));
    }

    /**
     * Display payment success information
     *
     * @param receipt ReceiptPayment object
     * @param receivable Receivable object
     */
    private void displayPaymentSuccess(ReceiptPayment receipt, Receivable receivable) {
        System.out.println("\n=== PAYMENT SUCCESSFUL ===");
        System.out.println("Receipt No: " + receipt.getReceiptNo());
        System.out.println("Amount Paid: " + formatAmount(receipt.getPaidAmount()));
        System.out.println("Payment Mode: " + receipt.getPaymentMode());
        System.out.println("Payment Date: " + receipt.getPaymentDate());

        // Calculate allocation details
        Allocation allocation = emiService.calculateAllocation(receipt.getLoanAccountNo(), receipt.getPaidAmount());
        System.out.println("\n=== ALLOCATION DETAILS ===");
        System.out.println("Amount allocated to Penalty: " + formatAmount(allocation.getAllocatedPenalty()));
        System.out.println("Amount allocated to EMI: " + formatAmount(allocation.getAllocatedEmi()));
    }

    /**
     * Format BigDecimal amount for display
     *
     * @param amount BigDecimal amount
     * @return formatted amount string
     */
    private String formatAmount(BigDecimal amount) {
        if (amount == null) {
            return "0.00";
        }
        return String.format("%.2f", amount);
    }

    /**
     * Validate payment mode
     *
     * @param paymentMode payment mode string
     * @return true if valid, false otherwise
     */
    private boolean isValidPaymentMode(String paymentMode) {
        return paymentMode.equals("CASH") || paymentMode.equals("CHEQUE") || paymentMode.equals("ONLINE");
    }
}
