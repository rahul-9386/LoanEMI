package com.emi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity class representing LMS_RECEIPTPAYMENT_DTL table
 * Maps to the receipt/payment details in the loan management system
 *
 * @author EMI Payment System
 * @version 1.0
 */
@Entity
@Table(name = "LMS_RECEIPTPAYMENT_DTL_POOJA")
public class ReceiptPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipt_seq")
    @SequenceGenerator(name = "receipt_seq", sequenceName = "LMS_RECEIPTPAYMENT_SEQ_POOJA", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "loan_account_no", nullable = false, length = 50)
    private String loanAccountNo;

    @Column(name = "paid_amount", precision = 15, scale = 2)
    private BigDecimal paidAmount;

    @Column(name = "payment_mode", length = 20)
    private String paymentMode;

    @Column(name = "receipt_no", length = 50)
    private String receiptNo;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    // Default constructor
    public ReceiptPayment() {}

    // Constructor with parameters
    public ReceiptPayment(String loanAccountNo, BigDecimal paidAmount, String paymentMode, String receiptNo) {
        this.loanAccountNo = loanAccountNo;
        this.paidAmount = paidAmount;
        this.paymentMode = paymentMode;
        this.receiptNo = receiptNo;
        this.paymentDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanAccountNo() {
        return loanAccountNo;
    }

    public void setLoanAccountNo(String loanAccountNo) {
        this.loanAccountNo = loanAccountNo;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "ReceiptPayment{" +
                "id=" + id +
                ", loanAccountNo='" + loanAccountNo + '\'' +
                ", paidAmount=" + paidAmount +
                ", paymentMode='" + paymentMode + '\'' +
                ", receiptNo='" + receiptNo + '\'' +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
