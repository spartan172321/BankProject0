package dev.rong.DAO;

import java.util.List;
import entities.BankAccount;

public interface BankAccountDAO {
	BankAccount createBankAccount(BankAccount account);
	
	BankAccount getBankAccountById(int id);
	BankAccount getBankAccountByName(String name);
	List<BankAccount> getAllBankAccountsByUserId(int id);
	List<BankAccount> getAllBankAccounts();
	
	BankAccount updateBankAccount(BankAccount account);
	
	boolean deleteBankAccount(BankAccount account);
}
