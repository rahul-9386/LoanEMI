# EMI Payment System

A console-based EMI payment system built with Java, Maven, Hibernate, and Oracle Database.

## Project Structure

```
emi-payment-system/
├── pom.xml                          # Maven configuration
├── README.md                        # Project documentation
├── src/
│   ├── main/
│   │   ├── java/com/emi/
│   │   │   ├── MainApp.java         # Application entry point
│   │   │   ├── controller/
│   │   │   │   └── EmiController.java
│   │   │   ├── service/
│   │   │   │   ├── EmiService.java
│   │   │   │   └── EmiServiceImpl.java
│   │   │   ├── dao/
│   │   │   │   ├── LoanDao.java
│   │   │   │   └── LoanDaoImpl.java
│   │   │   ├── model/
│   │   │   │   ├── Receivable.java
│   │   │   │   ├── ReceiptPayment.java
│   │   │   │   └── Allocation.java
│   │   │   └── util/
│   │   │       └── HibernateUtil.java
│   │   └── resources/
│   │       └── hibernate.cfg.xml    # Database configuration
│   └── test/
│       └── java/com/emi/
│           └── EmiServiceTest.java  # Unit tests
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Oracle Database (with existing tables)
- Oracle JDBC Driver (included in pom.xml)

## Database Setup

The application expects the following Oracle tables to exist:

1. **LMS_RECEIVABLEPAYBLE_DTL**
   - id (Primary Key)
   - loan_account_no
   - emi_amount
   - penalty
   - total_amount
   - created_date

2. **LMS_RECEIPTPAYMENT_DTL**
   - id (Primary Key)
   - loan_account_no
   - paid_amount
   - payment_mode
   - receipt_no
   - payment_date

3. **LMS_ALLOCATION_DTL**
   - id (Primary Key)
   - loan_account_no
   - allocated_penalty
   - allocated_emi
   - allocation_date

## Configuration

1. **Update Database Connection**: Edit `src/main/resources/hibernate.cfg.xml`
   ```xml
   <property name="hibernate.connection.username">your_oracle_username</property>
   <property name="hibernate.connection.password">your_oracle_password</property>
   ```

2. **Verify Oracle JDBC URL**: Default is `jdbc:oracle:thin:@localhost:1521:xe`

## Building the Project

```bash
# Compile the project
mvn clean compile

# Run tests
mvn test

# Create JAR file
mvn package

# Run the application
java -cp target/classes com.emi.MainApp
```

## Usage

### Running the Application

```bash
mvn exec:java -Dexec.mainClass="com.emi.MainApp"
```

Or compile and run manually:

```bash
mvn clean compile
java -cp target/classes com.emi.MainApp
```

### Console Interface

The application provides a simple console-based interface:

```
=== EMI PAYMENT SYSTEM ===
1. Make Payment
2. Check Pending EMI
3. Exit

Choose an option (1-3): 1

=== PAYMENT PROCESSING ===
Enter Loan Account Number: 12345
Pending EMI: 5000.00
Penalty Charges: 200.00
Total Amount: 5200.00

Enter Payment Amount: 5200
Enter Payment Mode: CASH

=== PAYMENT SUCCESSFUL ===
Receipt No: RCP20231201123045001
Amount Paid: 5200.00
Payment Mode: CASH
Payment Date: 2023-12-01T12:30:45

=== ALLOCATION DETAILS ===
Amount allocated to Penalty: 200.00
Amount allocated to EMI: 5000.00
```

## Features

- **Multi-layered Architecture**: Controller → Service → DAO → Database
- **Console-based Interface**: User-friendly command-line interaction
- **Payment Processing**: Handle EMI payments with penalty calculation
- **Receipt Generation**: Automatic receipt number generation
- **Payment Allocation**: Smart allocation of payments to penalty and EMI
- **Input Validation**: Comprehensive validation for loan accounts and payments
- **Error Handling**: Robust error handling and user feedback
- **Unit Testing**: JUnit 5 test coverage for business logic

## Dependencies

- **Hibernate Core 6.1.6.Final**: ORM framework
- **Oracle JDBC Driver 21.9.0.0**: Database connectivity
- **Jakarta Persistence API 3.1.0**: JPA annotations
- **JUnit 5.9.2**: Unit testing framework

## Architecture

### Controller Layer
- `EmiController`: Handles console I/O and user interactions

### Service Layer
- `EmiService`: Business logic interface
- `EmiServiceImpl`: Business logic implementation with payment processing and validation

### DAO Layer
- `LoanDao`: Data access interface
- `LoanDaoImpl`: Hibernate-based database operations

### Model Layer
- JPA entities mapped to Oracle tables
- Clean entity design with proper relationships

### Utility Layer
- `HibernateUtil`: SessionFactory management

## Error Handling

The application includes comprehensive error handling for:
- Invalid loan account numbers
- Insufficient payment amounts
- Database connection issues
- Invalid payment modes
- Unexpected runtime errors

## Testing

Run unit tests with:

```bash
mvn test
```

The test suite includes:
- Loan account validation tests
- Receipt number generation tests
- Payment processing validation
- Error handling verification

## Notes

1. **Database Tables**: Ensure the Oracle tables exist before running the application
2. **JDBC Driver**: Oracle JDBC driver is included as a dependency
3. **Hibernate Settings**: `hbm2ddl.auto` is set to `validate` to work with existing tables
4. **Payment Modes**: Supported modes are CASH, CHEQUE, and ONLINE
5. **Receipt Numbers**: Auto-generated with timestamp and random number

## Troubleshooting

1. **Database Connection Issues**: Verify Oracle connection details in `hibernate.cfg.xml`
2. **Table Not Found Errors**: Ensure all required tables exist in the database
3. **Compilation Errors**: Run `mvn clean compile` to rebuild the project
4. **Test Failures**: Some tests may fail if database is not properly configured

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License.
# LoanEMI
