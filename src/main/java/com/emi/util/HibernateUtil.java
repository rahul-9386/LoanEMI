package com.emi.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.emi.model.Receivable;
import com.emi.model.ReceiptPayment;
import com.emi.model.Allocation;

/**
 * Hibernate utility class for managing SessionFactory
 * Provides centralized database connection management
 *
 * @author EMI Payment System
 * @version 1.0
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Get Hibernate SessionFactory instance
     * Creates SessionFactory if it doesn't exist
     *
     * @return SessionFactory instance
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create configuration
                Configuration configuration = new Configuration();

                // Add annotated classes
                configuration.addAnnotatedClass(Receivable.class);
                configuration.addAnnotatedClass(ReceiptPayment.class);
                configuration.addAnnotatedClass(Allocation.class);

                // Build SessionFactory
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.err.println("SessionFactory creation failed: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Failed to create SessionFactory", e);
            }
        }
        return sessionFactory;
    }

    /**
     * Shutdown Hibernate SessionFactory
     * Should be called when application is shutting down
     */
    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
