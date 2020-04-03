package dev.rong.ui;

import java.util.List;
import java.util.Scanner;

import dev.rong.services.AdminServices;
import dev.rong.services.AdminServicesImpl;
import entities.User;

public class AdminFunctionsUI {
	
	public static Scanner scanner = new Scanner(System.in);
	public static AdminServices adminServices = new AdminServicesImpl();
	
	public void adminFunctions() {
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
	
	public void createUser() {
		System.out.println("Enter a new username");
		String username = scanner.nextLine();
		System.out.println("Enter a new password");
		String password = scanner.nextLine();
		
		adminServices.addNewUser(username, password);
	}
	
	public void viewAllUsers() {
		List<User> allUsers = adminServices.viewAllUsers();
		for(User user : allUsers) {
			System.out.println("User Id: " + user.getUserId() + "    Username: " + user.getName() + "    Password: " + user.getPassword());
		}
	}
	
	public boolean checkUserId(int id) {
		boolean idCheck = false;
		List<User> allUsers = adminServices.viewAllUsers();
		for(User user : allUsers) {
			if(user.getUserId() == id) {
				idCheck = true;
			}
		}
		return idCheck;
	}
	
	public void updateUser() {
		System.out.println("Enter the id of the user being updated");
		int userId = scanner.nextInt();
		scanner.nextLine();
		
		if(this.checkUserId(userId)) {
			System.out.println("Enter the new username");
			String username = scanner.nextLine();
			System.out.println("Enter the new password");
			String password = scanner.nextLine();
			
			adminServices.updateUser(userId, username, password);
		}else {
			System.out.println("User Id does not exist");
		}
		
	}
	
	public void deleteUser() {
		System.out.println("Enter the id of the user being deleted");
		int userId = scanner.nextInt();
		if(this.checkUserId(userId)) {
			adminServices.deleteUser(userId);
		}else {
			System.out.println("User Id does not exist");
		}
		
	}
}
