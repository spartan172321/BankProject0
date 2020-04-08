package dev.rong.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.rong.utils.ConnectionUtil;
import entities.User;

public class UserDAOMaria implements UserDAO{

	public User createUser(User user) {
		
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "INSERT INTO bankdb.CUSTOMER VALUES (?,?,?)";
			// when you first save an object it has an id of 0
			// please return the primary key of the object we just saved
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2,user.getName());
			ps.setString(3, user.getPassword());
			ps.execute(); // execute the insert statement
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("USER_ID");
			
			user.setUserId(key);
			return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public User getUserById(int id) {
		try(Connection conn = ConnectionUtil.createConnection()){
			
			String sql = "SELECT * FROM bankdb.CUSTOMER WHERE USER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			User user = new User();
			user.setUserId(rs.getInt("USER_ID"));
			user.setName(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PASSWORD"));
			
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public User getUserByName(String username) {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM bankdb.CUSTOMER WHERE USERNAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			User user = new User();
			user.setUserId(rs.getInt("USER_ID"));
			user.setName(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PASSWORD"));
			
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public User getUserByNameAndPassword(String username, String password) {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM bankdb.CUSTOMER WHERE USERNAME = ? AND PASSWORD = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			User user = new User();
			user.setUserId(rs.getInt("USER_ID"));
			user.setName(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PASSWORD"));
			
			return user;
		}catch(SQLException e) {
			return null;
		}
	}

	public List<User> getAllUsers() {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM bankdb.CUSTOMER";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			
			while(rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("USER_ID"));
				user.setName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				users.add(user);
			}
			
			return users;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public User updateUser(User user) {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "UPDATE bankdb.CUSTOMER SET USERNAME = ?, PASSWORD = ? WHERE USER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getUserId());
			boolean success = ps.execute();
			
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteUser(User user) {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "DELETE FROM bankdb.CUSTOMER WHERE USER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			boolean success = ps.execute();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
