package com.emi.dao;

import com.emi.model.Receivable;
import com.emi.model.ReceiptPayment;
import com.emi.model.Allocation;
import java.math.BigDecimal;
import java.util.List;

/**
 * Data Access Object interface for loan operations
 * Defines methods for database operations related to EMI payments
 *
 * @author EMI Payment System
 * @version 1.0
 */
public interface LoanDao {

    /**
     * Find receivable details by loan account number
     *
     * @param loanAccountNo the loan account number
     * @return Receivable object containing EMI and penalty details
     */
    Receivable findReceivableByLoanAccountNo(String loanAccountNo);

    /**
     * Save receipt payment record
     *
     * @param receiptPayment the receipt payment to save
     * @return the saved ReceiptPayment object
     */
    ReceiptPayment saveReceiptPayment(ReceiptPayment receiptPayment);

    /**
     * Save allocation record
     *
     * @param allocation the allocation to save
     * @return the saved Allocation object
     */
    Allocation saveAllocation(Allocation allocation);

    /**
     * Get all pending receivables for a loan account
     *
     * @param loanAccountNo the loan account number
     * @return list of pending receivables
     */
    List<Receivable> getPendingReceivables(String loanAccountNo);

    /**
     * Calculate total pending amount for a loan account
     *
     * @param loanAccountNo the loan account number
     * @return total pending amount
     */
    BigDecimal getTotalPendingAmount(String loanAccountNo);
}
