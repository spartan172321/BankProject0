package dev.rong.services;

import java.util.ArrayList;
import java.util.List;

import dev.rong.DAO.BankAccountDAO;
import dev.rong.DAO.BankAccountDAOMaria;
import dev.rong.DAO.BankAccountDAOlocal;
import dev.rong.DAO.UserDAO;
import dev.rong.DAO.UserDAOMaria;
import dev.rong.DAO.UserDAOlocal;
import entities.BankAccount;
import entities.User;

public class UserServicesImpl implements UserServices{
	
	private UserDAO userdao = new UserDAOMaria();
	private BankAccountDAO accoutdao = new BankAccountDAOMaria();
	
	public User enrollUser(String username, String password) {
		List<User> allUsers = userdao.getAllUsers();
		boolean userExists = false;
		
		if(username == "admin") {
			userExists = true;
		}
		
		for(User x : allUsers) {
			if(x.getName().equals(username)) {
				userExists = true;
			}
		}
		
		if(!userExists) {
			User user = new User();
			user.setName(username);
			user.setPassword(password);
			userdao.createUser(user);
			System.out.println("Your new username and password has been created");
			return user;
		}else {
			System.out.println("The username is already taken. Please try again");
			return null;
		}
	}
	
	public User loginAsUser(String username, String password) {
		User user = userdao.getUserByNameAndPassword(username, password);
		if(user == null) {
			System.out.println("Inncorrect username or password");
		}else {
			System.out.println("Welcome " + user.getName());
		}
		return user;
	}
	
}
