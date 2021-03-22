package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

@Component
public class JdbcTransferDAO implements TransferDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTransferDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



	@Override
	public Double viewBalance(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	public void sendBucks(int userId, int toUserId, BigDecimal amount) {
		
		String sqlFrom = "UPDATE accounts SET balance = (balance - ?) WHERE user_id = ?";
		jdbcTemplate.update(sqlFrom, userId, amount);
		
		String sqlTo = "UPDATE accounts SET balance = (balance + ?) WHERE user_id = ?";
		jdbcTemplate.update(sqlTo, userId, amount);
	}

	@Override
	public List<Transfer> viewTransfers(int userId) {
		 List<Transfer> transfers = new ArrayList<>();
		 //System.out.println("top");
		String sql = "SELECT transfer_id, amount, transfer_type_desc, username AS From, " +
				"(SELECT username FROM users WHERE user_id = " +
				"(SELECT user_id FROM accounts WHERE account_id=t.account_to)) AS To FROM  users " +
				"JOIN accounts a ON a.user_id = users.user_id " +
				"JOIN transfers t ON t.account_from = a.account_id " +
				"JOIN transfer_types ty ON ty.transfer_type_id = t.transfer_type_id " +
				"WHERE users.user_id= ?";
		//System.out.println("query done");
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql,userId);
		
		while(result.next()) {
			//System.out.println("rowset");
			Transfer transfer = mapRowToTransfer(result);
			transfers.add(transfer);
		}
		return transfers;
	}

	@Override
	public Transfer viewTransferDetails(int transferId) {
		
	String sql ="SELECT transfer_id, amount, transfer_type_desc, username AS From, " +
				"(SELECT username FROM users WHERE user_id = " +
				"(SELECT user_id FROM accounts WHERE account_id = t.account_to)) AS To FROM  users u " +
				"JOIN accounts a ON a.user_id = u.user_id " +
				"JOIN transfers t ON t.account_from = a.account_id " +
				"JOIN transfer_types ty ON ty.transfer_type_id = t.transfer_type_id " +
				"JOIN transfer_statuses ts ON ts.transfer_status_id = t.transfer_status_id " +
				"WHERE t.transfer_id= ?";
	System.out.println("query done");
	SqlRowSet result = jdbcTemplate.queryForRowSet(sql,transferId);
	
	if (result != null) {
	//System.out.println("result not null");	
		if(result.next()) {
			//System.out.println("in next");
			Transfer transfer = mapRowToTransfer(result);
			return transfer;
		}
		
		
	}
		return null;
	}
	
	// KESHA *********
	// insert new transfer into transfers table
	//INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES ( 2,2,2002,2004,700);
	
	
    @Override
    public List<Transfer> findAll() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash FROM transfer;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }
	
	
	private Transfer mapRowToTransfer(SqlRowSet results) {		
	
	Transfer transfer = new Transfer();
	// WE WANT TO HANDLE THE DATA FROM OUR QUERY 
	
	//these were uncommented
	//transfer.setTransferStatusId(results.getString("transferStatusId"));
	
	//transfer.setFromUserId(results.getInt("fromUser_id"));
	//transfer.setToUserId(results.getInt("toUser_id"));
	
	//transfer.setFromAccountId(results.getInt("account_from"));
	//transfer.setToAccountId(results.getInt("account_to"));
	
	//these work
	transfer.setTransferId(results.getInt("transfer_id"));
	transfer.setAmount(results.getBigDecimal("amount"));	
	transfer.setFromUserName(results.getString("from"));	
	transfer.setToUserName(results.getString("to"));
	transfer.setTransferType(results.getString("transfer_type_desc"));

	
	return transfer;
}

	

	
}