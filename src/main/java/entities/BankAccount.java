package entities;

public class BankAccount {
	private int accountId;
	private String name;
	private double balance;
	private int userId;
	
	public BankAccount() {
		
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BankAccount [accountId=" + accountId + ", name=" + name + ", balance=" + balance + ", userId=" + userId
				+ "]";
	}
	
	
}
