package dev.rong.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.rong.utils.ConnectionUtil;
import entities.BankAccount;

public class BankAccountDAOMaria implements BankAccountDAO{

	@Override
	public BankAccount createBankAccount(BankAccount account) {
		
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "INSERT INTO bankdb.ACCOUNT VALUES (0,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, account.getName());
			ps.setDouble(2, account.getBalance());
			ps.setInt(3, account.getUserId());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("ACCOUNT_ID");
			
			account.setUserId(key);
			return account;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public BankAccount getBankAccountById(int id) {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM bankdb.ACCOUNT WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			BankAccount account = new BankAccount();
			account.setAccountId(rs.getInt("ACCOUNT_ID"));
			account.setName(rs.getString("NAME"));
			account.setBalance(rs.getDouble("BALANCE"));
			account.setUserId(rs.getInt("USER_ID"));
			
			return account;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BankAccount getBankAccountByName(String name) {
		
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM bankdb.ACCOUNT WHERE NAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			BankAccount account = new BankAccount();
			account.setAccountId(rs.getInt("ACCOUNT_ID"));
			account.setName(rs.getString("NAME"));
			account.setBalance(rs.getDouble("BALANCE"));
			account.setUserId(rs.getInt("USER_ID"));
			
			return account;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BankAccount> getAllBankAccountsByUserId(int id) {
		
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM bankdb.ACCOUNT WHERE USER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			List<BankAccount> allAccounts = new ArrayList<BankAccount>();
			
			while(rs.next()) {
				BankAccount account = new BankAccount();
				account.setAccountId(rs.getInt("ACCOUNT_ID"));
				account.setName(rs.getString("NAME"));
				account.setBalance(rs.getDouble("BALANCE"));
				account.setUserId(rs.getInt("USER_ID"));
				allAccounts.add(account);
			}
			
			return allAccounts;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BankAccount> getAllBankAccounts() {
		
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM bankdb.ACCOUNT";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<BankAccount> allAccounts = new ArrayList<BankAccount>();
			
			while(rs.next()) {
				BankAccount account = new BankAccount();
				account.setAccountId(rs.getInt("ACCOUNT_ID"));
				account.setName(rs.getString("NAME"));
				account.setBalance(rs.getDouble("BALANCE"));
				account.setUserId(rs.getInt("USER_ID"));
				allAccounts.add(account);
			}
			
			return allAccounts;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "UPDATE bankdb.ACCOUNT SET NAME = ?, BALANCE = ? WHERE ACCOUNT_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getName());
			ps.setDouble(2, account.getBalance());
			ps.setInt(3, account.getAccountId());
			
			boolean success = ps.execute();
			
			return account;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteBankAccount(BankAccount account) {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "DELETE FROM bankdb.ACCOUNT WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getAccountId());
			boolean success = ps.execute();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
