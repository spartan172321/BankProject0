package dev.rong.services;

import java.util.List;

import dev.rong.DAO.BankAccountDAO;
import dev.rong.DAO.BankAccountDAOMaria;
import dev.rong.DAO.BankAccountDAOlocal;
import dev.rong.DAO.UserDAO;
import dev.rong.DAO.UserDAOMaria;
import dev.rong.DAO.UserDAOlocal;
import entities.BankAccount;
import entities.User;

public class BankAccountServicesImpl implements BankAccountServices{
	
	private BankAccountDAO bankdao = new BankAccountDAOMaria();
	private UserDAO userdao = new UserDAOMaria();
	
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
		if(initialDeposit<0) {
			initialDeposit = 0;
		}
		account.setBalance(initialDeposit);
		account.setUserId(user.getUserId());
		this.bankdao.createBankAccount(account);
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
		if(account.getBalance() >= amount && amount > 0) {
			account.setBalance(account.getBalance() - amount);
			bankdao.updateBankAccount(account);
		}else {
			System.out.println("The amount requested is not valid");
		}
		return account;
	}

	public BankAccount deposit(User user, BankAccount account, double amount) {
		if(amount>0) {
			account.setBalance(account.getBalance() + amount);
			bankdao.updateBankAccount(account);
		}else {
			System.out.println("The amount requested is not valid");
		}
		return account;
	}

	public BankAccount deleteBankAccount(User user, BankAccount account) {
		if(account.getBalance() <= 0) {
			bankdao.deleteBankAccount(account);
			user.getAccounts().remove(account);
		}else {
			System.out.println("You can only delete accounts with a 0 balance");
		}
		return account;
	}



}
