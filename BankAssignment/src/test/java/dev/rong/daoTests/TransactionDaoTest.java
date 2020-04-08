package dev.rong.daoTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dev.rong.DAO.TransactionDAO;
import dev.rong.DAO.TransactionDAOMaria;
import entities.Transaction;

public class TransactionDaoTest {
	
	public static TransactionDAO transdao = new TransactionDAOMaria();
	@Test
	public void createTransaction() {
		Transaction trans = new Transaction();
		trans.setAmount(-1000);
		trans.setAccountId(1);
		transdao.createTransaction(trans);
	}
	
	@Test
	public void getTransactionById() {
		Transaction trans = transdao.getTransactionById(1);
		System.out.println(trans);
	}
	
	@Test
	public void getAllTransactionsByAccountId() {
		List<Transaction> allTransByAcct = transdao.getAllTransactionByAccountId(1);
		for(Transaction tran : allTransByAcct) {
			System.out.println(tran);
		}
	}
	
	@Test
	public void updateTransaction() {
		Transaction tran = transdao.getTransactionById(1);
		tran.setAmount(2000);
		transdao.updateTransaction(tran);
	}
	
	@Test
	public void deleteTranscation() {
		Transaction tran = transdao.getTransactionById(1);
		transdao.deleteTransaction(tran);
	}

}
