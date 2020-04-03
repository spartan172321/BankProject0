package entities;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int userId;
	private String name;
	private String password;
	
	private List<BankAccount> accounts = new ArrayList<BankAccount>();

	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccount(List<BankAccount> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", password=" + password + ", accounts=" + accounts + "]";
	}
	
	
	
}
