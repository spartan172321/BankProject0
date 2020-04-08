package dev.rong.services;

import java.util.List;

import entities.BankAccount;
import entities.Transaction;

public interface TransactionServices {
	
	Transaction createNewTranscation(BankAccount account, double amount);
	
	Transaction getTransactionByTranscationId(int id);
	
	List<Transaction> getTransactionsByAccountId(int id);
	
	boolean deleteAllTranscationsByAccountId(int id);
}
