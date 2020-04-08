package dev.rong.services;

import java.util.List;

import dev.rong.DAO.BankAccountDAO;
import dev.rong.DAO.BankAccountDAOMaria;
import dev.rong.DAO.BankAccountDAOlocal;
import dev.rong.DAO.TransactionDAO;
import dev.rong.DAO.TransactionDAOMaria;
import dev.rong.DAO.UserDAO;
import dev.rong.DAO.UserDAOMaria;
import dev.rong.DAO.UserDAOlocal;
import entities.BankAccount;
import entities.Transaction;
import entities.User;

public class BankAccountServicesImpl implements BankAccountServices{
	
	private BankAccountDAO bankdao = new BankAccountDAOMaria();
	private TransactionDAO transdao = new TransactionDAOMaria();
	
	public BankAccount makeBankAccount(User user, String acctName, double initialDeposit) {
		List<BankAccount> userAcctList = bankdao.getAllBankAccountsByUserId(user.getUserId());
		
		for(BankAccount acct : userAcctList) {
			if(acct.getName().equals(acctName)) {
				System.out.println("An account with this name already exists");
				return null;
			}
		}
		
		BankAccount account = new BankAccount();
		account.setName(acctName);
		account.setUserId(user.getUserId());
		account = this.bankdao.createBankAccount(account);
		
		if(initialDeposit<0) {
			initialDeposit = 0;
		}
		Transaction trans = new Transaction();
		trans.setAmount(initialDeposit);
		trans.setAccountId(account.getAccountId());
		transdao.createTransaction(trans);
		
		return account;
	}
	
	
	public List<BankAccount> getAllAccounts(User user) {
		List<BankAccount> userAcctList = bankdao.getAllBankAccountsByUserId(user.getUserId());
		return userAcctList;
	}
	
	
	public BankAccount viewBalance(User user, BankAccount account) {
		bankdao.getAllBankAccounts();
		return null;
	}

	
	
	public BankAccount withdrawl(User user, BankAccount account, double amount) {
		List<Transaction> allTrans = transdao.getAllTransactionByAccountId(account.getAccountId());
		double balance = 0;
		for(Transaction tran : allTrans) {
			balance += tran.getAmount();
		}
		if(balance >= amount && amount > 0) {
			Transaction newTran = new Transaction();
			newTran.setAmount(-amount);
			newTran.setAccountId(account.getAccountId());
			transdao.createTransaction(newTran);
			balance -= amount;
			System.out.println("Account "+account.getAccountId()+ " new balance: "+ balance);
		}else {
			System.out.println("The amount requested is not valid");
		}
		return account;
	}

	public BankAccount deposit(User user, BankAccount account, double amount) {
		List<Transaction> allTrans = transdao.getAllTransactionByAccountId(account.getAccountId());
		double balance = 0;
		for(Transaction tran : allTrans) {
			balance += tran.getAmount();
		}
		if(amount>0) {
			Transaction newTran = new Transaction();
			newTran.setAmount(amount);
			newTran.setAccountId(account.getAccountId());
			transdao.createTransaction(newTran);
			balance += amount;
			System.out.println("Account "+account.getAccountId() + " new balance: "+ balance);
		}else {
			System.out.println("The amount requested is not valid");
		}
		return account;
	}

	public boolean deleteBankAccount(User user, BankAccount account) {
		List<Transaction> allTrans = transdao.getAllTransactionByAccountId(account.getAccountId());
		double balance = 0;
		for(Transaction tran : allTrans) {
			balance += tran.getAmount();
		}
		if(balance == 0) {
			transdao.deleteAllTransactionsByAccountId(account.getAccountId());
			bankdao.deleteBankAccount(account);
			user.getAccounts().remove(account);
			return true;
		}else {
			System.out.println("You can only delete accounts with a 0 balance");
			return false;
		}
	}

}
