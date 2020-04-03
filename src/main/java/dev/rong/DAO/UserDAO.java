package dev.rong.DAO;

import java.util.List;

import entities.User;

public interface UserDAO {
	
	User createUser(User user);
	
	User getUserById(int id);
	User getUserByName(String name);
	User getUserByNameAndPassword(String name, String password);
	List<User> getAllUsers();
	
	User updateUser(User user);
	
	boolean deleteUser(User user);
	
}
