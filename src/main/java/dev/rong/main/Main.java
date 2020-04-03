package dev.rong.main;

import java.util.List;
import java.util.Scanner;

import dev.rong.services.AdminServicesImpl;
import dev.rong.services.BankAccountServicesImpl;
import dev.rong.services.UserServicesImpl;
import dev.rong.ui.AdminFunctionsUI;
import dev.rong.ui.CustomerFunctionUI;
import entities.Admin;
import entities.BankAccount;
import entities.User;

public class Main {
	
	public static Scanner scanner = new Scanner(System.in);
	public static UserServicesImpl userServices = new UserServicesImpl();
	public static BankAccountServicesImpl accountServices = new BankAccountServicesImpl();
	public static AdminServicesImpl adminServices = new AdminServicesImpl();
	public static AdminFunctionsUI adminFunctions = new AdminFunctionsUI();
	public static CustomerFunctionUI customerFunctions = new CustomerFunctionUI();
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
			adminFunctions.adminFunctions();
		}else {
			User user = userServices.loginAsUser(username, password);
			if(user != null) {
				customerFunctions.customerFunctions(user);
			}
		}
	}
	
}
