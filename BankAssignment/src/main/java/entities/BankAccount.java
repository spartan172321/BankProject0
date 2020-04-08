package entities;

public class BankAccount {
	private int accountId;
	private String name;
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
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BankAccount [accountId=" + accountId + ", name=" + name + ", userId=" + userId
				+ "]";
	}
	
	
}
