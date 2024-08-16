package cipherByte;

import java.util.Map;
import java.util.Scanner;

public class Bank {
	
	 private Map<String, Account> accounts;

	    public Bank() {
	        accounts = FilePersistence.loadAccounts();
	    }

	    public void createAccount(String accountNumber, String accountHolderName) {
	        if (!accounts.containsKey(accountNumber)) {
	            Account newAccount = new Account(accountNumber, accountHolderName);
	            accounts.put(accountNumber, newAccount);
	            System.out.println("Account created for " + accountHolderName);
	        } else {
	            System.out.println("Account number already exists.");
	        }
	    }

	    public Account getAccount(String accountNumber) {
	        return accounts.get(accountNumber);
	    }

	    public void saveAccounts() {
	        FilePersistence.saveAccounts(accounts);
	    }

	    public static void main(String[] args) {
	        Bank bank = new Bank();
	        Scanner scanner = new Scanner(System.in);
	        boolean exit = false;

	        while (!exit) {
	            System.out.println("\nBankY System:");
	            System.out.println("1. Create Account");
	            System.out.println("2. Deposit");
	            System.out.println("3. Withdraw");
	            System.out.println("4. Transfer");
	            System.out.println("5. Check Balance");
	            System.out.println("6. Exit");
	            System.out.print("Select an option: ");
	            int option = scanner.nextInt();

	            switch (option) {
	                case 1:
	                    System.out.print("Enter account number: ");
	                    String accountNumber = scanner.next();
	                    System.out.print("Enter account holder name: ");
	                    String accountHolderName = scanner.next();
	                    bank.createAccount(accountNumber, accountHolderName);
	                    break;
	                case 2:
	                    System.out.print("Enter account number: ");
	                    accountNumber = scanner.next();
	                    Account account = bank.getAccount(accountNumber);
	                    if (account != null) {
	                        System.out.print("Enter deposit amount: ");
	                        double depositAmount = scanner.nextDouble();
	                        account.deposit(depositAmount);
	                    } else {
	                        System.out.println("Account not found.");
	                    }
	                    break;
	                case 3:
	                    System.out.print("Enter account number: ");
	                    accountNumber = scanner.next();
	                    account = bank.getAccount(accountNumber);
	                    if (account != null) {
	                        System.out.print("Enter withdrawal amount: ");
	                        double withdrawalAmount = scanner.nextDouble();
	                        account.withdraw(withdrawalAmount);
	                    } else {
	                        System.out.println("Account not found.");
	                    }
	                    break;
	                case 4:
	                    System.out.print("Enter your account number: ");
	                    String fromAccountNumber = scanner.next();
	                    Account fromAccount = bank.getAccount(fromAccountNumber);
	                    if (fromAccount != null) {
	                        System.out.print("Enter target account number: ");
	                        String toAccountNumber = scanner.next();
	                        Account toAccount = bank.getAccount(toAccountNumber);
	                        if (toAccount != null) {
	                            System.out.print("Enter transfer amount: ");
	                            double transferAmount = scanner.nextDouble();
	                            fromAccount.transferTo(toAccount, transferAmount);
	                        } else {
	                            System.out.println("Target account not found.");
	                        }
	                    } else {
	                        System.out.println("Account not found.");
	                    }
	                    break;
	                case 5:
	                    System.out.print("Enter account number: ");
	                    accountNumber = scanner.next();
	                    account = bank.getAccount(accountNumber);
	                    if (account != null) {
	                        System.out.println("Current balance: $" + account.getBalance());
	                    } else {
	                        System.out.println("Account not found.");
	                    }
	                    break;
	                case 6:
	                    bank.saveAccounts();
	                    System.out.println("Goodbye!");
	                    exit = true;
	                    break;
	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }

	            if (!exit) {
	                System.out.print("Do you want to perform another operation? (yes/no): ");
	                String anotherOperation = scanner.next();
	                if (!anotherOperation.equalsIgnoreCase("yes") || !anotherOperation.equalsIgnoreCase("y") || !anotherOperation.equalsIgnoreCase("Y")) {
	                    exit = true;
	                    bank.saveAccounts();
	                    System.out.println("Thanks for using Banky System!");
	                }
	            }
	        }
	    }
}
