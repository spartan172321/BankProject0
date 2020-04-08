package dev.rong.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.BankAccount;


public class BankAccountDAOlocal implements BankAccountDAO{
	
	private static Map<Integer, BankAccount> account_table = new HashMap<Integer, BankAccount>();
	private static int idGenerator = 1001;

	public BankAccount createBankAccount(BankAccount account) {
		account.setAccountId(idGenerator);
		idGenerator++;
		account_table.put(account.getAccountId(), account);
		return account;
	}

	public BankAccount getBankAccountById(int id) {
		return account_table.get(id);
	}

	public BankAccount getBankAccountByName(String name) {
		for(BankAccount account : this.getAllBankAccounts()) {
			if(account.getName().equals(name)) {
				return account;
			}
		}
		return null;
	}

	public List<BankAccount> getAllBankAccounts() {
		List<BankAccount> allBankAccounts = new ArrayList<BankAccount>(account_table.values());
		return allBankAccounts;
	}
	
	public List<BankAccount> getAllBankAccountsByUserId(int id) {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		for(BankAccount account : this.getAllBankAccounts()) {
			if(account.getUserId() == id) {
				bankAccounts.add(account);
			}
		}
		return bankAccounts;
	}

	public BankAccount updateBankAccount(BankAccount account) {
		account_table.put(account.getAccountId(), account);
		return account;
	}

	public boolean deleteBankAccount(BankAccount account) {
		account_table.remove(account.getAccountId());
		return true;
	}





}
