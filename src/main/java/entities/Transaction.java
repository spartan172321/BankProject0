package entities;

public class Transaction {
	private int transactionId;
	private double amount;
	private String timestamp;
	private int accountId;
	
	public Transaction() {
		
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	
	@Override
	public String toString() {
		return "Transcation [transactionId=" + transactionId + ", amount=" + amount + ", timestamp=" + timestamp
				+ ", accountId=" + accountId + "]";
	}
	
	
}
