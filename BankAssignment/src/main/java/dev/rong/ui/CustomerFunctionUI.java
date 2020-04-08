package dev.rong.ui;

import java.util.List;
import java.util.Scanner;

import dev.rong.services.BankAccountServices;
import dev.rong.services.BankAccountServicesImpl;
import dev.rong.services.TransactionServices;
import dev.rong.services.TransactionServicesImpl;
import entities.BankAccount;
import entities.Transaction;
import entities.User;

public class CustomerFunctionUI {
	public static Scanner scanner = new Scanner(System.in);
	public static BankAccountServices accountServices = new BankAccountServicesImpl();
	public static TransactionServices tranService = new TransactionServicesImpl();
	
	public void customerFunctions(User user) {
		boolean quit = false;
		while(!quit) {
			System.out.println("                             ");
			System.out.println("Enter 1 to select an account");
			System.out.println("Enter 2 to view accounts");
			System.out.println("Enter 3 to create new account");
			System.out.println("Enter 4 to logout");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
				case 1:
					selectAccount(user);
					break;
				case 2:
					viewAccounts(user);
					break;
				case 3:
					createAccount(user);
					break;
				case 4:
					quit = true;
					break;
				default:
					break;
			}
		}
	}
	
	public void selectAccount(User user) {
		List<BankAccount> userAccts = accountServices.getAllAccounts(user);
		System.out.println("Please enter your account id");
		int acctId = scanner.nextInt();
		boolean acctExists = false;
		for(BankAccount thisAcct : userAccts) {
			if(thisAcct.getAccountId() == acctId) {
				
				BankAccount acct = thisAcct;
				acctExists = true;
				boolean quit = false;
				while(!quit) {
					System.out.println("                         ");
					System.out.println("Enter 1 to make a deposit");
					System.out.println("Enter 2 to make a withdrwal");
					System.out.println("Enter 3 to view account balance");
					System.out.println("Enter 4 to view transactions");
					System.out.println("Enter 5 to delete this accout");
					System.out.println("Enter 6 to go back");
					int choice = scanner.nextInt();
					scanner.nextLine();
					switch(choice) {
						case 1:
							makeDeposit(user,acct);
							break;
						case 2:
							makeWithdrawl(user,acct);
							break;
						case 3:
							viewAccountBalance(user,acct);
							break;
						case 4:
							viewAllTranactionsInAccount(user,acct);
							break;
						case 5:
							quit = deleteAccount(user,acct);
							break;
						case 6:
							quit = true;
							break;
						default:
							break;
					}
				}
			}
		}
		if(!acctExists) {
			System.out.println("Account is not avalible");
		}
	}
	
	
	public void makeDeposit(User user, BankAccount acct) {
		System.out.println("Enter the amount you wish to deposit");
		double amount = scanner.nextDouble();
		accountServices.deposit(user, acct, amount);
	}
	
	public void makeWithdrawl(User user, BankAccount acct) {
		System.out.println("Enter the amount you wish to withdrawl");
		double amount = scanner.nextDouble();
		accountServices.withdrawl(user, acct, amount);
	}
	
	
	public void viewAccounts(User user) {
		List<BankAccount> userAccts = accountServices.getAllAccounts(user);
		System.out.println("Your bank accounts are: ");
		
		for(BankAccount acct : userAccts) {
			double balance = 0;
			List<Transaction> allTrans = tranService.getTransactionsByAccountId(acct.getAccountId());
			for(Transaction tran: allTrans) {
				balance += tran.getAmount();
			}
			System.out.println("AcctId: " + acct.getAccountId()+"    "+"Name: "+acct.getName()+"    "+"Balance: "+balance);
		}
	}
	
	public void viewAccountBalance(User user,BankAccount acct) {
		List<Transaction> acctTrans = tranService.getTransactionsByAccountId(acct.getAccountId());
		double balance = 0;
		for(Transaction tran : acctTrans) {
			balance += tran.getAmount();
		}
		System.out.println("Account "+acct.getAccountId()+": "+ balance);
	}
	
	public void viewAllTranactionsInAccount(User user, BankAccount acct){
		List<Transaction> acctTrans = tranService.getTransactionsByAccountId(acct.getAccountId());
		System.out.println("For Account:" + acct.getAccountId());
		for(Transaction tran: acctTrans) {
			System.out.println("Timestamp: "+tran.getTimestamp() + "________" + tran.getAmount());
		}
	}
	
	
	public void createAccount(User user) {
		System.out.println("Enter a name for the new account");
		String acctName = scanner.nextLine();
		System.out.println("Enter the amount for an initial deposit");
		double initialDeposit = scanner.nextDouble();
		accountServices.makeBankAccount(user, acctName, initialDeposit);
	}
	
	public boolean deleteAccount(User user, BankAccount acct) {
	
		return accountServices.deleteBankAccount(user, acct);
			
	}
	
}
