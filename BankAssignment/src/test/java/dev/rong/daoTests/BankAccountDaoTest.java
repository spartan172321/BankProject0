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
		acct2.setName("lol");
		acct2.setUserId(1);
		accountDao.createBankAccount(acct2);
		System.out.println(acct2);
		
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
		accountDao.updateBankAccount(acct);
	}
	
	@Test
	public void deleteBankAccount() {
		BankAccount acct = accountDao.getBankAccountById(9);
		boolean success = accountDao.deleteBankAccount(acct);
		System.out.println(success);
	}

}
