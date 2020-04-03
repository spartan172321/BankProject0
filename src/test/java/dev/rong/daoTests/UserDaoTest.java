package dev.rong.daoTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dev.rong.DAO.UserDAO;
import dev.rong.DAO.UserDAOMaria;
import entities.User;

public class UserDaoTest {
	
	public static UserDAO userDao = new UserDAOMaria();
	
	@Test
	public void createUser() {
		User user = new User();
		
		user.setName("Rong");
		user.setPassword("12345");
		userDao.createUser(user);
		
		user.setName("Phil");
		user.setPassword("12345");
		userDao.createUser(user);
		
		user.setName("Bob");
		user.setPassword("12345");
		userDao.createUser(user);
		
	}
	
	@Test
	public void viewUsers() {
		List<User> users = userDao.getAllUsers();
		for(User user : users) {
			System.out.println(user.toString());
		}
	}
	
	@Test
	public void viewUserById() {
		User user = userDao.getUserById(4);
		System.out.println(user);
	}
	
	@Test
	public void viewUserByName() {
		User user = userDao.getUserByName("Cheng");
		System.out.println(user);
	}
	
	@Test
	public void viewUserByUsernameAndPassword() {
		User user = userDao.getUserByNameAndPassword("Cheng", "123");
		System.out.println(user);
	}
	
	@Test
	public void updateUser() {
		User user = userDao.getUserById(2);
		user.setName("Ted");
		user.setPassword("1234");
		user = userDao.updateUser(user);
		System.out.println(user);
	}
	
	@Test
	public void deleteUser() {
		User user = userDao.getUserById(1);
		userDao.deleteUser(user);
		List<User> allUsers = userDao.getAllUsers();
		for(User x : allUsers) {
			System.out.println(x);
		}
	}
	

}
