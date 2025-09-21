package com.emi.dao;

import com.emi.model.Receivable;
import com.emi.model.ReceiptPayment;
import com.emi.model.Allocation;
import com.emi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation of LoanDao interface
 * Contains Hibernate-based database operations for EMI payment system
 *
 * @author EMI Payment System
 * @version 1.0
 */
public class LoanDaoImpl implements LoanDao {

    @Override
    public Receivable findReceivableByLoanAccountNo(String loanAccountNo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Receivable> query = session.createQuery(
                "FROM Receivable WHERE loanAccountNo = :loanAccountNo", Receivable.class);
            query.setParameter("loanAccountNo", loanAccountNo);
            return query.uniqueResult();
        } catch (Exception e) {
            System.err.println("Error finding receivable by loan account number: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ReceiptPayment saveReceiptPayment(ReceiptPayment receiptPayment) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(receiptPayment);

            transaction.commit();
            return receiptPayment;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error saving receipt payment: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to save receipt payment", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Allocation saveAllocation(Allocation allocation) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(allocation);

            transaction.commit();
            return allocation;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error saving allocation: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to save allocation", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Receivable> getPendingReceivables(String loanAccountNo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Receivable> query = session.createQuery(
                "FROM Receivable WHERE loanAccountNo = :loanAccountNo AND totalAmount > 0", Receivable.class);
            query.setParameter("loanAccountNo", loanAccountNo);
            return query.list();
        } catch (Exception e) {
            System.err.println("Error getting pending receivables: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public BigDecimal getTotalPendingAmount(String loanAccountNo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<BigDecimal> query = session.createQuery(
                "SELECT COALESCE(SUM(totalAmount), 0) FROM Receivable WHERE loanAccountNo = :loanAccountNo",
                BigDecimal.class);
            query.setParameter("loanAccountNo", loanAccountNo);
            return query.uniqueResult();
        } catch (Exception e) {
            System.err.println("Error calculating total pending amount: " + e.getMessage());
            e.printStackTrace();
            return BigDecimal.ZERO;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
