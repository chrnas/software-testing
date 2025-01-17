import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


/**
 * @author Thelma Ofoegbu
 * Megan Katete
 * Emma O'Reily
 * Christoffer Nas
 * 
 *
 */
class Grp31_BankAccountManagementSystemTest {
	
	 private BankAccountManagementSystem testSystem;

	 /**
		 * Sets up the test environment before each test.
	     * Creates an instance of BankAccountManagementSystem and an account with a balance of 5000.0.
		 * @throws java.lang.Exception if an error occurs
		 */
		@BeforeEach
		void setUp() throws Exception {
	        testSystem = new BankAccountManagementSystem();
	        testSystem.createAccount(101, 5000.0);
	    }
		/**
	     * Parameterized test for the createAccount method.
	     * This test checks various scenarios of account creation, including:
	     * - Valid account creation
	     * - Duplicate account creation
	     * - Attempt to create an account with invalid initial balance
	     *
	     * @param accountNumber    The account number to be created
	     * @param initialBalance   The initial balance for the account
	     * @param expectedResult   The expected result of the createAccount method (true/false)
	     */
	    @ParameterizedTest(name = "Test case :{index} accountNumber:{0}, initialBalance:{1} returns {2}")
	    @CsvSource({
	    	//Test case 1: Valid account creation
	        "2, 1, true",
	        //Test case 2: Duplicate account creation
	        "1, 1, false",
	        //Test case 3: Negative and invalid initial balance
	        "2, -1, false"
	    })
	    void testCreateAccount(int accountNumber, double initialBalance, boolean expectedResult) {
	        BankAccountManagementSystem bankSystem = new BankAccountManagementSystem();
	        bankSystem.createAccount(1, 1); // Pre-create an account to be able to perform duplicate scenario

	        boolean actualResult = bankSystem.createAccount(accountNumber, initialBalance); // Attempt to create an account with the provided parameters

	        assertEquals(expectedResult, actualResult); //Verify that the result matches the expected outcome
	    }
		
		@ParameterizedTest
	    @CsvSource({
	            "12345, 2000.0, -1.0", //account doesnt existed, should return -1.0
	            "54321, 2500.0, -1.0", //account doesnt exist, should return -1.0
	            "12345, 2000.0, 2100.0", //accounts does exist with a balance of 100
	            "54321, 2500.0, 2600.0" //account does exist with a balance of 50
	    })
	    void testDeposit(int accountNumber, double amount, double expectedResult){
	        if(expectedResult == -1.0){
	            assertEquals(-1.0, testSystem.deposit(accountNumber, amount));
	        } else {
	            testSystem.createAccount(accountNumber,100.0);
	        }

	        double balanceAfterDeposit = testSystem.deposit(accountNumber, amount);

	        assertEquals(expectedResult, balanceAfterDeposit);
	    }

	    /**
	     * Parameterized test for the withdraw method in various scenarios.
	     * The test covers cases where the account does not exist, amount is zero or negative,
	     * amount is greater or less than balance, amount equals balance, balance is zero, and valid withdrawals.
	     *
	     * @param accountNumber     The account number to use for withdrawal.
	     * @param amount            The amount to withdraw.
	     * @param expectedResult    The expected result from the withdraw method.
	     * @param description       A description of the test case for reference.
	     */
	    @ParameterizedTest
	    @CsvSource({
	            // Test Case 1: Account does not exist
	            "100, 50.0, 0.0, 'Account does not exist, expected 0.0'",

	            // Test Case 2: Account exists and a valid withdrawal is made.
	            "101, 50.0, 4950.0, 'Valid withdrawal, expect updated balance of 4950.0'",

	            // Test Case 3: Amount is zero, which should not be allowed
	            "101, 0.0, -1.0, 'Amount is zero, expect -1.0'",

	            // Test Case 4: Valid withdrawal where amount is positive and less than balance
	            "101, 1000.0, 4000.0, 'Valid withdrawal: amount is positive and less than balance, expect 4000.0'",

	            // Test Case 5: Amount is negative, which should not be allowed
	            "101, -50.0, -1.0, 'Amount is negative, expect -1.0'",

	            // Test Case 6: Amount greater than balance, not permitted as overdraft is not allowed
	            "101, 10000.0, -2.0, 'Amount greater than balance, expect -2.0'",

	            // Test Case 7: Amount exactly equal to the balance, not allowed
	            "101, 5000.0, -2.0, 'Amount equals balance, expect -2.0'",

	            // Test Case 8: Balance is zero, but the withdrawal amount is non-zero 
	            "999, 50.0, -3.0, 'Balance is zero, amount is non-zero, expect -3.0'",

	            // Test Case 9: Balance is non-zero, but the withdrawal amount is zero
	            "101, 0.0, -1.0, 'Balance is non-zero, amount is zero, expect -1.0'",

	            // Test Case 10: Both balance and withdrawal amount are zero
	            "999, 0.0, -3.0, 'Balance is zero, amount is zero, expect -3.0'",

	            // Test Case 11: Valid withdrawal where both amount and balance are non-zero
	            "101, 500.0, 4500.0, 'Amount is non-zero and balance is non-zero, expect updated balance of 4500.0'"
	    })
	    void testWithdraw(int accountNumber, double amount, double expectedResult, String description) {
	        // Special case handling for zero balance test cases
	        if (description.contains("Balance is zero")) {
	            testSystem.createAccount(accountNumber, 0.0);
	        }

	        // Perform the withdrawal
	        double actualResult = testSystem.withdraw(accountNumber, amount);

	        // Assert the result
	        assertEquals(expectedResult, actualResult, description);
	    }
	    /**
		 * Tests `getAccountBalance` for an existing account.
		 * Verifies that the method returns the correct balance (300.0)
		 * when the account exists in the system.
		 */
		@Test
		void testGetAccountBalanceForExistingAccount() {
			//adds new testSystem 
			testSystem.createAccount(12121, 300.0);
		
			//check the balance to verify if it returns the correct amount
			double actualResult = testSystem.getAccountBalance(12121);
			double expectedResult = 300;
			assertEquals(expectedResult, actualResult, "Return the correct balance for existing Account");
		}
		
		/**
		 * Tests `getAccountBalance` for a non-existent account.
		 * Expects a `NullPointerException` when accessing an account that is not in the system.
		 */
		@Test
		void testGetAccountBalanceForNonExistingAccount() {
		    try {
		        double actualResult = testSystem.getAccountBalance(778899);
		        //expectedResult = a null pointer exception because accessing "balance = accounts.get(accountNumber);" should throw that exception because the account is not in the accounts HashMap.
		  
		        fail("Expected a NullPointerException when retrieving the balance of a non-existent Account");// If no exception is thrown, the test fails
		    } catch (NullPointerException e) {
		    	// Exception caught
		    }
		}
	}
