package com.emi.service;

import com.emi.model.Receivable;
import com.emi.model.ReceiptPayment;
import com.emi.model.Allocation;
import java.math.BigDecimal;

/**
 * Service interface for EMI payment business logic
 * Defines methods for EMI payment processing and calculations
 *
 * @author EMI Payment System
 * @version 1.0
 */
public interface EmiService {

    /**
     * Process EMI payment for a loan account
     *
     * @param loanAccountNo the loan account number
     * @param paymentAmount the payment amount
     * @param paymentMode the payment mode (CASH, CHEQUE, ONLINE, etc.)
     * @return ReceiptPayment object with payment details
     */
    ReceiptPayment processPayment(String loanAccountNo, BigDecimal paymentAmount, String paymentMode);

    /**
     * Get pending EMI details for a loan account
     *
     * @param loanAccountNo the loan account number
     * @return Receivable object with pending EMI details
     */
    Receivable getPendingEmiDetails(String loanAccountNo);

    /**
     * Calculate allocation of payment amount to penalty and EMI
     *
     * @param loanAccountNo the loan account number
     * @param paymentAmount the payment amount
     * @return Allocation object with allocation details
     */
    Allocation calculateAllocation(String loanAccountNo, BigDecimal paymentAmount);

    /**
     * Validate loan account number
     *
     * @param loanAccountNo the loan account number to validate
     * @return true if valid, false otherwise
     */
    boolean validateLoanAccount(String loanAccountNo);

    /**
     * Generate receipt number
     *
     * @return unique receipt number
     */
    String generateReceiptNumber();
}
