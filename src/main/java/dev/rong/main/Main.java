package dev.rong.main;

import java.util.List;
import java.util.Scanner;

import dev.rong.services.AdminServicesImpl;
import dev.rong.services.BankAccountServicesImpl;
import dev.rong.services.UserServicesImpl;
import entities.Admin;
import entities.BankAccount;
import entities.User;

public class Main {
	
	public static Scanner scanner = new Scanner(System.in);
	public static UserServicesImpl userServices = new UserServicesImpl();
	public static BankAccountServicesImpl accountServices = new BankAccountServicesImpl();
	public static AdminServicesImpl adminServices = new AdminServicesImpl();
	Admin admin = new Admin();
	
	public static void main(String[] args) {
		boolean quit = false;
		while(!quit) {
			System.out.println("                             ");
			System.out.println("Enter 1 to login");
			System.out.println("Enter 2 to enroll");
			System.out.println("Enter any other value to exit");
			int choice = scanner.nextInt();
			scanner.nextLine();
			if(choice == 1) {
				System.out.println("Enter your username");
				String username = scanner.nextLine();
				System.out.println("Enter your password");
				String password = scanner.nextLine();
				login(username,password);
			}else if(choice == 2) {
				enroll();
			}else {
				quit = true;
			}
		}
		
	}
	
	public static void enroll() {
		System.out.println("Enter your new username");
		String username = scanner.nextLine();
		System.out.println("Enter your new password");
		String password = scanner.nextLine();
		
		userServices.enrollUser(username, password);
	}
	
	public static void login(String username, String password) {
		if(username.equals("admin") && password.equals("password")) {
			adminFunctions();
		}else {
			User user = userServices.loginAsUser(username, password);
			if(user != null) {
				customerFunctions(user);
			}
		}
	}
	
	public static void adminFunctions() {
		boolean quit = false;
		while(!quit) {
			System.out.println("                        ");
			System.out.println("Enter 1 to create a user");
			System.out.println("Enter 2 to view a user");
			System.out.println("Enter 3 to update a user");
			System.out.println("Enter 4 to delete a user");
			System.out.println("Enter 5 to logout");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
				case 1:
					createUser();
					break;
				case 2:
					viewAllUsers();
					break;
				case 3:
					updateUser();
					break;
				case 4:
					deleteUser();
					break;
				case 5:
					quit = true;
					break;
				default:
					break;
			}
		}
	}
	
	public static void createUser() {
		System.out.println("Enter a new username");
		String username = scanner.nextLine();
		System.out.println("Enter a new password");
		String password = scanner.nextLine();
		
		adminServices.addNewUser(username, password);
	}
	
	public static void viewAllUsers() {
		List<User> allUsers = adminServices.viewAllUsers();
		for(User user : allUsers) {
			System.out.println("User Id: " + user.getUserId() + "    Username: " + user.getName() + "    Password: " + user.getPassword());
		}
	}
	
	public static boolean checkUserId(int id) {
		boolean idCheck = false;
		List<User> allUsers = adminServices.viewAllUsers();
		for(User user : allUsers) {
			if(user.getUserId() == id) {
				idCheck = true;
			}
		}
		return idCheck;
	}
	
	public static void updateUser() {
		System.out.println("Enter the id of the user being updated");
		int userId = scanner.nextInt();
		scanner.nextLine();
		
		if(checkUserId(userId)) {
			System.out.println("Enter the new username");
			String username = scanner.nextLine();
			System.out.println("Enter the new password");
			String password = scanner.nextLine();
			
			adminServices.updateUser(userId, username, password);
		}else {
			System.out.println("User Id does not exist");
		}
		
	}
	
	public static void deleteUser() {
		System.out.println("Enter the id of the user being deleted");
		int userId = scanner.nextInt();
		if(checkUserId(userId)) {
			adminServices.deleteUser(userId);
		}else {
			System.out.println("User Id does not exist");
		}
		
	}
	
	public static void customerFunctions(User user) {
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
	
	
	public static void makeTransaction(User user) {
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
	
	
	public static void viewAccounts(User user) {
		List<BankAccount> userAccts = accountServices.getAllAccounts(user);
		System.out.println("Your bank accounts are: ");
		for(BankAccount acct : userAccts) {
			System.out.println("AcctId: " + acct.getAccountId()+"    "+"Name: "+acct.getName()+"    "+"Balance: "+acct.getBalance());
		}
	}
	
	
	public static void createAccount(User user) {
		System.out.println("Enter a name for the new account");
		String acctName = scanner.nextLine();
		System.out.println("Enter the amount for an initial deposit");
		double initialDeposit = scanner.nextDouble();
		accountServices.makeBankAccount(user, acctName, initialDeposit);
	}
	
	public static void deleteAccount(User user) {
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
