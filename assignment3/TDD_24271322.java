import static org.junit.jupiter.api.Assertions.*; // Import static methods from JUnit for assertions like assertEquals

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test; // Import JUnit's basic testing functionality
/**
 * Test class for BankAccountManagementSystem.
 * This class contains unit tests for the createAccount method 
 * in the BankAccountManagementSystem class.
 */
class TDD_24271322 {

	/**
     * Test Case 1: Test the creation of a new bank account with a valid initial balance.
     * This test checks if the account is created correctly and added to the system.
     */
	@Test   
    void testCreateAccount() {
    	
    	// Inputs
        int accountNumber = 1;
        double initialBalance = 1000.0;

        // Expected result
        Map<Integer, Double> expectedAccounts = Map.of(accountNumber, initialBalance);
        
        // Create account
        BankAccountManagementSystem_24271322 bankSystem = new BankAccountManagementSystem_24271322();
        bankSystem.createAccount(accountNumber, initialBalance);
        
        //Actual results
        Map<Integer, Double> createdAccounts = bankSystem.getAccounts();
        assertEquals(expectedAccounts, createdAccounts); //Check if account looks like expected
    }
    
	/**
     * Test Case 2: Test the creation of a bank account with a negative initial balance.
     * This test verifies that negative balances are rejected and no account is created.
     */
    @Test
    void testNegativeInitialBalance() {
    	
    	// Inputs
    	int accountNumber = 1;
    	double initialBalance = -500.0;
        
        // Create account
        BankAccountManagementSystem_24271322 bankSystem = new BankAccountManagementSystem_24271322();
        bankSystem.createAccount(accountNumber, initialBalance); //Try to add negative value
        
        //Actual results
        Map<Integer, Double> createdAccounts = bankSystem.getAccounts();
        
        //Expected result
        Map<Integer, Double> expectedAccounts = new HashMap<>();
        
        //Test if actual result is like expected, null
        assertEquals(expectedAccounts, createdAccounts);
    }
    
    /**
     * Test Case 3: Test the creation of a duplicate account with the same account number.
     * This test checks if duplicate account creation is prevented and the system 
     * does not allow overwriting of existing accounts.
     */
    @Test
    void testDuplicateAccountCreation() {
    	
    	// Inputs
    	int accountNumber = 1;
    	double initialBalance = 1000.0;
    	
    	// Create account
        BankAccountManagementSystem_24271322 bankSystem = new BankAccountManagementSystem_24271322();
        bankSystem.createAccount(accountNumber, initialBalance);
        
        // Create account with same account number
        bankSystem.createAccount(accountNumber, initialBalance);
        
        // Expected result
        Map<Integer, Double> expectedAccounts = Map.of(accountNumber, initialBalance);
        
        // Actual result
        Map<Integer, Double> createdAccounts = bankSystem.getAccounts();
        		
        //Test if duplicate account creation were rejected
        assertEquals(expectedAccounts, createdAccounts);
    }
    
    /**
     * Test Case 4: Test the deposit operation for an existing bank account.
     * This test verifies if the deposit amount is correctly added to the account's balance.
     */
    @Test
    void testDepositOperation() {
    	
    	// Inputs
    	int accountNumber = 1;
    	double initialBalance = 1000.0;
    	double depositAmount = 100;
    	
    	// Create account
    	BankAccountManagementSystem_24271322 bankSystem = new BankAccountManagementSystem_24271322();
        bankSystem.createAccount(accountNumber, initialBalance);
         
        // Perform deposit operation
        bankSystem.deposit(accountNumber, depositAmount);
        
        // Expected result
        Map<Integer, Double> expectedAccounts = Map.of(accountNumber, initialBalance+depositAmount);
        
        // Actual result
        Map<Integer, Double> createdAccounts = bankSystem.getAccounts();
        
        // Test if the accounts is updated correctly
        assertEquals(createdAccounts, expectedAccounts);
    }

    /**
     * Test Case 5: Test the deposit operation with a negative deposit amount.
     * This test checks that a deposit with a negative amount is not allowed and
     * the account balance remains unchanged.
     */
    @Test
    void testDepositNegativeAmount() {
    	
    	// Inputs
    	int accountNumber = 1;
    	double initialBalance = 1000.0;
    	double depositAmount = -100;
    	
    	// Create account
    	BankAccountManagementSystem_24271322 bankSystem = new BankAccountManagementSystem_24271322();
        bankSystem.createAccount(accountNumber, initialBalance);
         
        // Perform deposit operation with negative amount
        bankSystem.deposit(accountNumber, depositAmount);
        
        // Expected result
        Map<Integer, Double> expectedAccounts = Map.of(accountNumber, initialBalance);
        
        // Actual result
        Map<Integer, Double> createdAccounts = bankSystem.getAccounts();
        
        // Test if the accounts is updated correctly
        assertEquals(createdAccounts, expectedAccounts);
    }
    
    /**
     * Test Case 6: Test the withdraw operation with a valid withdraw amount and that the system is updated correctly.
     */
    @Test
    void testWithdrawOperation() {
    	
    	// Inputs
    	int accountNumber = 1;
    	double initialBalance = 1000.0;
    	double withdrawAmount = 100;
    	
    	// Create account
    	BankAccountManagementSystem_24271322 bankSystem = new BankAccountManagementSystem_24271322();
        bankSystem.createAccount(accountNumber, initialBalance);
         
        // Perform withdraw operation with negative amount
        bankSystem.withdraw(accountNumber, withdrawAmount);
        
        // Expected result
        Map<Integer, Double> expectedAccounts = Map.of(accountNumber, initialBalance-withdrawAmount);
        
        // Actual result
        Map<Integer, Double> createdAccounts = bankSystem.getAccounts();
        
        // Test if the accounts is updated correctly
        assertEquals(createdAccounts, expectedAccounts);
    }
    
    /**
     * Test Case 7: Test the withdraw operation with a negative withdraw amount and that the system successfully rejects withdrawal.
     */
    @Test
    void testWithdrawNegativeAmount() {
    	
    	// Inputs
    	int accountNumber = 1;
    	double initialBalance = 1000.0;
    	double withdrawAmount = -100;
    	
    	// Create account
    	BankAccountManagementSystem_24271322 bankSystem = new BankAccountManagementSystem_24271322();
        bankSystem.createAccount(accountNumber, initialBalance);
         
        // Perform withdraw operation with negative amount
        bankSystem.withdraw(accountNumber, withdrawAmount);
        
        // Expected result
        Map<Integer, Double> expectedAccounts = Map.of(accountNumber, initialBalance);
        
        // Actual result
        Map<Integer, Double> createdAccounts = bankSystem.getAccounts();
        
        // Test if the accounts is updated correctly
        assertEquals(createdAccounts, expectedAccounts);
    }
    
    /**
     * Test Case 8: Test the withdraw operation is rejected if account balance would be negative after withdrawal operation.
     */
    @Test
    void testOverdraftProtection() {
    	
    	// Inputs
    	int accountNumber = 1;
    	double initialBalance = 1000.0;
    	double withdrawAmount = 2000;
    	
    	// Create account
    	BankAccountManagementSystem_24271322 bankSystem = new BankAccountManagementSystem_24271322();
        bankSystem.createAccount(accountNumber, initialBalance);
         
        // Perform withdraw operation with withdraw amount larger than account balance
        bankSystem.withdraw(accountNumber, withdrawAmount);
        
        // Expected result
        Map<Integer, Double> expectedAccounts = Map.of(accountNumber, initialBalance);
        
        // Actual result
        Map<Integer, Double> createdAccounts = bankSystem.getAccounts();
        
        // Test if the withdraw operation have been rejected as expected
        assertEquals(createdAccounts, expectedAccounts);
    }
    
    /**
     * Test Case 9: Test that system implements a successful balance inquiry operation.
     */
    @Test
    void testInqueryOperation() {
    	
    	// Inputs
    	int accountNumber = 1;
    	double initialBalance = 1000.0;
    	
    	// Create account
    	BankAccountManagementSystem_24271322 bankSystem = new BankAccountManagementSystem_24271322();
        bankSystem.createAccount(accountNumber, initialBalance);
         
        // Perform withdraw inquiry operation and retrieve actual result
        Double accountBalance = bankSystem.checkAccountBalance(accountNumber);
        
        // Expected result
        Double expectedBalance = 1000.0;
        
        // Test if the withdraw operation have been rejected as expected
        assertEquals(accountBalance, expectedBalance);
    }
}
