package dev.rong.services;

import java.util.List;

import entities.User;

public interface AdminServices {
	User addNewUser(String username, String password);
	User viewUser(int userId);
	List<User> viewAllUsers();
	User updateUser(int userId, String username, String password);
	boolean deleteUser(int userId);
}
