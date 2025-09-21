package com.emi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity class representing LMS_ALLOCATION_DTL table
 * Maps to the allocation details in the loan management system
 *
 * @author EMI Payment System
 * @version 1.0
 */
@Entity
@Table(name = "LMS_ALLOCATION_DTL")
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "loan_account_no", nullable = false, length = 50)
    private String loanAccountNo;

    @Column(name = "allocated_penalty", precision = 15, scale = 2)
    private BigDecimal allocatedPenalty;

    @Column(name = "allocated_emi", precision = 15, scale = 2)
    private BigDecimal allocatedEmi;

    @Column(name = "allocation_date")
    private LocalDateTime allocationDate;

    // Default constructor
    public Allocation() {}

    // Constructor with parameters
    public Allocation(String loanAccountNo, BigDecimal allocatedPenalty, BigDecimal allocatedEmi) {
        this.loanAccountNo = loanAccountNo;
        this.allocatedPenalty = allocatedPenalty;
        this.allocatedEmi = allocatedEmi;
        this.allocationDate = LocalDateTime.now();
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

    public BigDecimal getAllocatedPenalty() {
        return allocatedPenalty;
    }

    public void setAllocatedPenalty(BigDecimal allocatedPenalty) {
        this.allocatedPenalty = allocatedPenalty;
    }

    public BigDecimal getAllocatedEmi() {
        return allocatedEmi;
    }

    public void setAllocatedEmi(BigDecimal allocatedEmi) {
        this.allocatedEmi = allocatedEmi;
    }

    public LocalDateTime getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(LocalDateTime allocationDate) {
        this.allocationDate = allocationDate;
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "id=" + id +
                ", loanAccountNo='" + loanAccountNo + '\'' +
                ", allocatedPenalty=" + allocatedPenalty +
                ", allocatedEmi=" + allocatedEmi +
                ", allocationDate=" + allocationDate +
                '}';
    }
}
