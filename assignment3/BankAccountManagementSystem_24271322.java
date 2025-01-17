import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
/**
 * The BankAccountManagementSystem class provides basic functionality for managing
 * bank accounts. It supports creating accounts, depositing funds, and retrieving
 * account details.
 */
public class BankAccountManagementSystem_24271322 {

    private Map<Integer, Double> accounts;

    public BankAccountManagementSystem_24271322() {
        this.accounts = new HashMap<>();
    }
    
    /**
     * Creates a new bank account with an initial balance.
     * 
     * @param accountNumber The unique identifier for the account.
     * @param initialBalance The starting balance for the account. Must be positive.
     */
    public void createAccount(int accountNumber, double initialBalance) {
    	if (!accounts.containsKey(accountNumber) && initialBalance > 0) {
    		accounts.put(accountNumber, initialBalance);
    	}
    }
    
    /**
     * Deposits a specified amount into an existing bank account.
     * 
     * @param accountNumber The unique identifier of the account to deposit into.
     * @param amount The amount to deposit. Must be positive.
     */
    public void deposit(int accountNumber, double amount) {
    	if (amount > 0) {
    		Double balance =  accounts.get(accountNumber);
        	double newBalance = balance + amount;
        	accounts.put(accountNumber, newBalance);
    	}
    }
    
    /**
     * Withdraws a specified amount from the account identified by the provided account number.
     * 
     * @param accountNumber The unique identifier for the account from which funds will be withdrawn.
     * @param amount The amount to withdraw from the account.
     */
    public void withdraw(int accountNumber, double amount) {
    	
    	Double balance = accounts.get(accountNumber);
    	
    	if (amount > 0 && balance >= amount) {
        	double newBalance = balance - amount;
        	accounts.put(accountNumber, newBalance);
    	}
    }
    
    /**
     * Return the account balance for an account.
     * 
     * @param accountNumber The unique identifier for the account from which balance will be returned.
     */
    public Double checkAccountBalance(int accountNumber) {
    	Double balance = accounts.get(accountNumber);
    	return balance;
    }
    
    /**
     * Retrieves a copy of the systems accounts in the system. The function is only used for testing purposes.
     * 
     * @return A map where keys are account numbers and values are account balances.
     */
    Map<Integer, Double> getAccounts() {
    	return Collections.unmodifiableMap(new HashMap<>(accounts));
    }
}



