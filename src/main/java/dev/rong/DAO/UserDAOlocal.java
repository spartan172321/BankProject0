package dev.rong.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.User;

public class UserDAOlocal implements UserDAO{
	
	private static Map<Integer,User> user_table = new HashMap<Integer,User>();
	private int idGenerator = 101;

	public User createUser(User user) {
		user.setUserId(idGenerator);
		idGenerator++;
		user_table.put(user.getUserId(), user);	
		
		return user;
	}

	
	public User getUserById(int id) {
		return user_table.get(id);
	}

	
	public User getUserByName(String name) {
		for(User user : this.getAllUsers()) {
			if(user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}
	
	public User getUserByNameAndPassword(String name, String password) {
		for(User user : this.getAllUsers()) {
			if(user.getName().equals(name) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>(user_table.values());
		return users;
	}

	public User updateUser(User user) {
		user_table.put(user.getUserId(),user);
		return user;
	}

	public boolean deleteUser(User user) {
		user_table.remove(user.getUserId());
		return true;
	}
	
}
