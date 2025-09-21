package com.emi.service;

import com.emi.dao.LoanDao;
import com.emi.dao.LoanDaoImpl;
import com.emi.model.Receivable;
import com.emi.model.ReceiptPayment;
import com.emi.model.Allocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Implementation of EmiService interface
 * Contains business logic for EMI payment processing
 *
 * @author EMI Payment System
 * @version 1.0
 */
public class EmiServiceImpl implements EmiService {

    private LoanDao loanDao;

    public EmiServiceImpl() {
        this.loanDao = new LoanDaoImpl();
    }

    @Override
    public ReceiptPayment processPayment(String loanAccountNo, BigDecimal paymentAmount, String paymentMode) {
        // Validate loan account
        if (!validateLoanAccount(loanAccountNo)) {
            throw new IllegalArgumentException("Invalid loan account number: " + loanAccountNo);
        }

        // Validate payment amount
        if (paymentAmount == null || paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }

        // Get pending EMI details
        Receivable receivable = loanDao.findReceivableByLoanAccountNo(loanAccountNo);
        if (receivable == null) {
            throw new IllegalArgumentException("No pending EMI found for loan account: " + loanAccountNo);
        }

        // Check if payment amount is sufficient
        BigDecimal totalPending = receivable.getTotalAmount();
        if (paymentAmount.compareTo(totalPending) < 0) {
            throw new IllegalArgumentException("Payment amount is less than total pending amount");
        }

        // Calculate allocation
        Allocation allocation = calculateAllocation(loanAccountNo, paymentAmount);

        // Save allocation
        loanDao.saveAllocation(allocation);

        // Create receipt payment record
        String receiptNo = generateReceiptNumber();
        ReceiptPayment receiptPayment = new ReceiptPayment(loanAccountNo, paymentAmount, paymentMode, receiptNo);
        receiptPayment.setPaymentDate(LocalDateTime.now());

        // Save receipt payment
        return loanDao.saveReceiptPayment(receiptPayment);
    }

    @Override
    public Receivable getPendingEmiDetails(String loanAccountNo) {
        if (!validateLoanAccount(loanAccountNo)) {
            throw new IllegalArgumentException("Invalid loan account number: " + loanAccountNo);
        }

        return loanDao.findReceivableByLoanAccountNo(loanAccountNo);
    }

    @Override
    public Allocation calculateAllocation(String loanAccountNo, BigDecimal paymentAmount) {
        Receivable receivable = loanDao.findReceivableByLoanAccountNo(loanAccountNo);

        if (receivable == null) {
            throw new IllegalArgumentException("No receivable found for loan account: " + loanAccountNo);
        }

        BigDecimal penalty = receivable.getPenalty() != null ? receivable.getPenalty() : BigDecimal.ZERO;
        BigDecimal emiAmount = receivable.getEmiAmount() != null ? receivable.getEmiAmount() : BigDecimal.ZERO;

        // Allocate to penalty first, then to EMI
        BigDecimal allocatedPenalty = BigDecimal.ZERO;
        BigDecimal allocatedEmi = BigDecimal.ZERO;

        if (paymentAmount.compareTo(penalty) >= 0) {
            allocatedPenalty = penalty;
            BigDecimal remainingAmount = paymentAmount.subtract(penalty);

            if (remainingAmount.compareTo(emiAmount) >= 0) {
                allocatedEmi = emiAmount;
            } else {
                allocatedEmi = remainingAmount;
            }
        } else {
            allocatedPenalty = paymentAmount;
        }

        return new Allocation(loanAccountNo, allocatedPenalty, allocatedEmi);
    }

    @Override
    public boolean validateLoanAccount(String loanAccountNo) {
        // Basic validation - check if loan account number is not null or empty
        // In a real application, this would validate against the database
        return loanAccountNo != null && !loanAccountNo.trim().isEmpty() && loanAccountNo.matches("\\d+");
    }

    @Override
    public String generateReceiptNumber() {
        // Generate a simple receipt number with timestamp and random number
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String random = String.format("%03d", new Random().nextInt(1000));
        return "RCP" + timestamp + random;
    }
}
