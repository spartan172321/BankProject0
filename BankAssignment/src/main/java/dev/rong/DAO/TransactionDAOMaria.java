package dev.rong.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.rong.utils.ConnectionUtil;
import entities.Transaction;

public class TransactionDAOMaria implements TransactionDAO{

	@Override
	public Transaction createTransaction(Transaction trans) {
		
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "INSERT INTO bankdb.TRANSACTION(AMOUNT,ACCOUNT_ID) VALUES(?,?);";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, trans.getAmount());
			ps.setInt(2, trans.getAccountId());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("TRANSACTION_ID");
			
			trans.setTransactionId(key);
			return trans;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Transaction getTransactionById(int id) {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM bankdb.TRANSACTION WHERE TRANSACTION_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Transaction tran = new Transaction();
			tran.setTransactionId(rs.getInt("TRANSACTION_ID"));
			tran.setAmount(rs.getDouble("AMOUNT"));
			tran.setTimestamp(rs.getTimestamp("TRANSACTION_DATE").toString());
			tran.setAccountId(rs.getInt("ACCOUNT_ID"));
			
			return tran;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Transaction> getAllTransactionByAccountId(int id) {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM bankdb.TRANSACTION WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			List<Transaction> allTrans = new ArrayList<Transaction>();
			
			while(rs.next()) {
				Transaction tran = new Transaction();
				tran.setTransactionId(rs.getInt("TRANSACTION_ID"));
				tran.setAmount(rs.getDouble("AMOUNT"));
				tran.setTimestamp(rs.getTimestamp("TRANSACTION_DATE").toString());
				tran.setAccountId(rs.getInt("ACCOUNT_ID"));
				
				allTrans.add(tran);
			}
			
			return allTrans;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Transaction updateTransaction(Transaction trans) {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "UPDATE bankdb.TRANSACTION SET AMOUNT = ? WHERE TRANSACTION_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, trans.getAmount());
			ps.setInt(2, trans.getTransactionId());
			
			boolean success = ps.execute();
			
			return trans;
			
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public boolean deleteTransaction(Transaction trans) {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "DELETE FROM bankdb.TRANSACTION WHERE TRANSACTION_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, trans.getTransactionId());
			
			boolean success = ps.execute();
			
			return true;
			
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
	}
	
	@Override
	public boolean deleteAllTransactionsByAccountId(int id) {
		try(Connection conn = ConnectionUtil.createConnection()){
			String sql = "DELETE FROM bankdb.TRANSACTION WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			boolean success = ps.execute();
			
			return true;
			
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
	}

}
