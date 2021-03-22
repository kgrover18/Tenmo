package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {
	
	
	// void performTransfer(Transfer transfer);
	
	Double viewBalance(int userId);
	
	void sendBucks(int userId, int toUserId, BigDecimal amount);
	
	List<Transfer> viewTransfers(int userId);
	
	Transfer viewTransferDetails(int transferId);

	List<Transfer> findAll();
}


