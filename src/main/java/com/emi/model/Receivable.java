package com.emi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity class representing LMS_RECEIVABLEPAYBLE_DTL table
 * Maps to the receivables/payables details in the loan management system
 *
 * @author EMI Payment System
 * @version 1.0
 */
@Entity
@Table(name = "LMS_RECEIVABLEPAYBLE_DTL")
public class Receivable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "loan_account_no", nullable = false, length = 50)
    private String loanAccountNo;

    @Column(name = "emi_amount", precision = 15, scale = 2)
    private BigDecimal emiAmount;

    @Column(name = "penalty", precision = 15, scale = 2)
    private BigDecimal penalty;

    @Column(name = "total_amount", precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    // Default constructor
    public Receivable() {}

    // Constructor with parameters
    public Receivable(String loanAccountNo, BigDecimal emiAmount, BigDecimal penalty, BigDecimal totalAmount) {
        this.loanAccountNo = loanAccountNo;
        this.emiAmount = emiAmount;
        this.penalty = penalty;
        this.totalAmount = totalAmount;
        this.createdDate = LocalDateTime.now();
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

    public BigDecimal getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(BigDecimal emiAmount) {
        this.emiAmount = emiAmount;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Receivable{" +
                "id=" + id +
                ", loanAccountNo='" + loanAccountNo + '\'' +
                ", emiAmount=" + emiAmount +
                ", penalty=" + penalty +
                ", totalAmount=" + totalAmount +
                ", createdDate=" + createdDate +
                '}';
    }
}
