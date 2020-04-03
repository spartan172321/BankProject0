package dev.rong.services;

import java.util.List;

import entities.BankAccount;
import entities.User;

public interface BankAccountServices {
	
	BankAccount makeBankAccount(User user, String acctName, double initialDeposit);
	
	List<BankAccount> getAllAccounts(User user);
	BankAccount viewBalance(User user, BankAccount account);
	
	BankAccount withdrawl(User user, BankAccount account, double amount);
	BankAccount deposit(User user, BankAccount account, double amount);
	
	BankAccount deleteBankAccount(User user, BankAccount account);
	
}
