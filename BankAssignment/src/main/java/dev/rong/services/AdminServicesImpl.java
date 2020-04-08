package dev.rong.services;

import java.util.List;

import dev.rong.DAO.BankAccountDAO;
import dev.rong.DAO.BankAccountDAOMaria;
import dev.rong.DAO.UserDAO;
import dev.rong.DAO.UserDAOMaria;
import entities.BankAccount;
import entities.User;

public class AdminServicesImpl implements AdminServices{
	
	private UserDAO userdao = new UserDAOMaria();
	private BankAccountDAO accountdoa = new BankAccountDAOMaria();

	public User addNewUser(String username, String password) {
		for(User user : this.viewAllUsers()) {
			if(user.getName().equals(username)) {
				System.out.println("Username is already taken");
				return null;
			}
		}
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		userdao.createUser(user);
		return user;
	}

	
	public User viewUser(int userId) {
		User user = userdao.getUserById(userId);
		return user;
	}

	public List<User> viewAllUsers() {
		List<User> allUsers = userdao.getAllUsers();
		return allUsers;
	}

	
	public User updateUser(int userId, String username, String password) {
		User user = userdao.getUserById(userId);
		if(user == null) {
			System.out.println("This User Id does not exist");
			return null;
		}else {
			
			List<User> allUsers = this.viewAllUsers();
			boolean usernameExists = false;
			for(User eachUser : allUsers) {
				if(eachUser.getName().equals(username)) {
					usernameExists = true;
					System.out.println(usernameExists);
				}
			}
			
			if(!usernameExists) {
				user.setName(username);
				user.setPassword(password);
				userdao.updateUser(user);
				System.out.println("Account information updated");
				return user;
			}else {
				System.out.println("The username is already taken");
				return null;
			}
			
		}
	}

	
	public boolean deleteUser(int userId) {
		User user = userdao.getUserById(userId);
		if(user == null) {
			System.out.println("This User Id does not exist");
			return false;
		}
		
		List<BankAccount> userAccounts = accountdoa.getAllBankAccountsByUserId(userId);
		boolean acctEmpty =  false;
		if(userAccounts.isEmpty()) {
			acctEmpty = true;
		}
		
		if(acctEmpty) {
			userdao.deleteUser(user);
			System.out.println("User deleted");
			return true;
		}else {
			System.out.println("The user still has existing bank accounts");
			return false;
		}
	}
}
