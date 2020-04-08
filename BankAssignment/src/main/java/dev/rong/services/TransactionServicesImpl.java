package dev.rong.services;

import java.util.ArrayList;
import java.util.List;

import dev.rong.DAO.TransactionDAO;
import dev.rong.DAO.TransactionDAOMaria;
import entities.BankAccount;
import entities.Transaction;

public class TransactionServicesImpl implements TransactionServices{
	
	public static TransactionDAO trandao = new TransactionDAOMaria();

	@Override
	public Transaction createNewTranscation(BankAccount account, double amount) {
		Transaction tran = new Transaction();
		tran.setAmount(amount);
		tran.setAccountId(account.getAccountId());
		
		tran = trandao.createTransaction(tran);
		return tran;
	}

	@Override
	public Transaction getTransactionByTranscationId(int id) {
		Transaction tran = trandao.getTransactionById(id);
		return tran;
	}

	@Override
	public List<Transaction> getTransactionsByAccountId(int id) {
		List<Transaction> allTrans = trandao.getAllTransactionByAccountId(id);
		return allTrans;
	}

	@Override
	public boolean deleteAllTranscationsByAccountId(int id) {
		trandao.deleteAllTransactionsByAccountId(id);
		return true;
	}

}
