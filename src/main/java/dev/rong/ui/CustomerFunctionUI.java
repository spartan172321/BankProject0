package dev.rong.ui;

import java.util.List;
import java.util.Scanner;

import dev.rong.services.BankAccountServices;
import dev.rong.services.BankAccountServicesImpl;
import entities.BankAccount;
import entities.User;

public class CustomerFunctionUI {
	public static Scanner scanner = new Scanner(System.in);
	public static BankAccountServices accountServices = new BankAccountServicesImpl();
	
	public void customerFunctions(User user) {
		boolean quit = false;
		while(!quit) {
			System.out.println("                             ");
			System.out.println("Enter 1 to make a transaction");
			System.out.println("Enter 2 to view accounts");
			System.out.println("Enter 3 to create new account");
			System.out.println("Enter 4 to delete an account");
			System.out.println("Enter 5 to logout");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
				case 1:
					makeTransaction(user);
					break;
				case 2:
					viewAccounts(user);
					break;
				case 3:
					createAccount(user);
					break;
				case 4:
					deleteAccount(user);
					break;
				case 5:
					quit = true;
					break;
				default:
					break;
			}
		}
	}
	
	
	public void makeTransaction(User user) {
		List<BankAccount> userAccts = accountServices.getAllAccounts(user);
		System.out.println("Please enter your account id");
		int acctId = scanner.nextInt();
		boolean acctExists = false;
		scanner.nextLine();
		for(BankAccount acct : userAccts) {
			if(acct.getAccountId() == acctId) {
				System.out.println("Enter 1 to make deposit");
				System.out.println("Enter 2 to make withdrawl");
				acctExists = true;
				int choice = scanner.nextInt();
				if(choice == 1) {
					System.out.println("Enter the amount you wish to deposit");
					double amount = scanner.nextDouble();
					accountServices.deposit(user, acct, amount);
					System.out.println("New balance: " + acct.getBalance());
				}else if(choice == 2) {
					System.out.println("Enter the amount you wish to withdrawl");
					double amount = scanner.nextDouble();
					accountServices.withdrawl(user, acct, amount);
					System.out.println("New balance: " + acct.getBalance());
				}else {
					break;
				}
			}
		}
		if(acctExists == false) {
			System.out.println("Account Id not found");
		}
	}
	
	
	public void viewAccounts(User user) {
		List<BankAccount> userAccts = accountServices.getAllAccounts(user);
		System.out.println("Your bank accounts are: ");
		for(BankAccount acct : userAccts) {
			System.out.println("AcctId: " + acct.getAccountId()+"    "+"Name: "+acct.getName()+"    "+"Balance: "+acct.getBalance());
		}
	}
	
	
	public void createAccount(User user) {
		System.out.println("Enter a name for the new account");
		String acctName = scanner.nextLine();
		System.out.println("Enter the amount for an initial deposit");
		double initialDeposit = scanner.nextDouble();
		accountServices.makeBankAccount(user, acctName, initialDeposit);
	}
	
	public void deleteAccount(User user) {
		List<BankAccount> userAccts = accountServices.getAllAccounts(user);
		System.out.println("Enter the id of the account you wish to delete");
		int acctId = scanner.nextInt();
		scanner.nextLine();
		boolean acctExists = false;
		for(BankAccount acct : userAccts) {
			if(acct.getAccountId() == acctId) {
				accountServices.deleteBankAccount(user, acct);
				acctExists = true;
			}
		}
		if(acctExists == false) {
			System.out.println("Account Id not found");
		}
		
	}
	
}
