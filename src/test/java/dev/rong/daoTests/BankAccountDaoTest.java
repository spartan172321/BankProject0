package dev.rong.daoTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dev.rong.DAO.BankAccountDAO;
import dev.rong.DAO.BankAccountDAOMaria;
import entities.BankAccount;

public class BankAccountDaoTest {
	
	public static BankAccountDAO accountDao = new BankAccountDAOMaria();
	
	@Test
	public void createBankAccount() {
		BankAccount acct2 = new BankAccount();
		acct2.setBalance(10000.0);
		acct2.setName("checking");
		acct2.setUserId(3);
		accountDao.createBankAccount(acct2);
		
		BankAccount acct3 = new BankAccount();
		acct3.setBalance(10000.0);
		acct3.setName("saving");
		acct3.setUserId(3);
		accountDao.createBankAccount(acct3);
		
		BankAccount acct4 = new BankAccount();
		acct4.setBalance(10000.0);
		acct4.setName("checking");
		acct4.setUserId(4);
		accountDao.createBankAccount(acct4);
		
		BankAccount acct5 = new BankAccount();
		acct5.setBalance(10000.0);
		acct5.setName("saving");
		acct5.setUserId(4);
		accountDao.createBankAccount(acct5);
	}
	
	@Test
	public void getBankAccountById() {
		BankAccount account = accountDao.getBankAccountById(3);
		System.out.println(account);
	}
	
	@Test
	public void getBankAccountByName() {
		BankAccount account = accountDao.getBankAccountByName("checking");
		System.out.println(account);
	}
	
	@Test
	public void getAllBankAccountsByUserId() {
		List<BankAccount> allAccounts = accountDao.getAllBankAccountsByUserId(3);
		for(BankAccount account : allAccounts) {
			System.out.println(account);
		}
	}
	
	@Test
	public void getAllBankAccounts() {
		List<BankAccount> allAccounts = accountDao.getAllBankAccounts();
		for(BankAccount account : allAccounts) {
			System.out.println(account);
		}
	}
	
	@Test
	public void updateBankAccount() {
		BankAccount acct = accountDao.getBankAccountById(9);
		acct.setName("alpha");
		acct.setBalance(50.0);
		accountDao.updateBankAccount(acct);
	}
	
	@Test
	public void deleteBankAccount() {
		BankAccount acct = accountDao.getBankAccountById(9);
		boolean success = accountDao.deleteBankAccount(acct);
		System.out.println(success);
	}

}
