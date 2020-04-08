package dev.rong.DAO;
import java.util.List;

import entities.Transaction;

public interface TransactionDAO {
	
	Transaction createTransaction(Transaction trans);
	
	Transaction getTransactionById(int id);
	List<Transaction> getAllTransactionByAccountId(int id);

	Transaction updateTransaction(Transaction trans);
	
	boolean deleteTransaction(Transaction trans);
	
	boolean deleteAllTransactionsByAccountId(int id);
}
