package cipherByte;

import java.io.Serializable;

public class Account  implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	    private String accountNumber;
	    private String accountHolderName;
	    private double balance;

	    public Account(String accountNumber, String accountHolderName) {
	        this.accountNumber = accountNumber;
	        this.accountHolderName = accountHolderName;
	        this.balance = 0.0;
	    }

	    public String getAccountNumber() {
	        return accountNumber;
	    }

	    public String getAccountHolderName() {
	        return accountHolderName;
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public void deposit(double amount) {
	        if (amount > 0) {
	            balance += amount;
	            System.out.println("Deposited: $" + amount);
	        } else {
	            System.out.println("Invalid deposit amount.");
	        }
	    }

	    public boolean withdraw(double amount) {
	        if (amount > 0 && amount <= balance) {
	            balance -= amount;
	            System.out.println("Withdrawn: $" + amount);
	            return true;
	        } else {
	            System.out.println("Invalid withdrawal amount or insufficient funds.");
	            return false;
	        }
	    }

	    public boolean transferTo(Account targetAccount, double amount) {
	        if (withdraw(amount)) {
	            targetAccount.deposit(amount);
	            System.out.println("Transferred: $" + amount + " to " + targetAccount.getAccountNumber());
	            return true;
	        }
	        return false;
	    }

}
