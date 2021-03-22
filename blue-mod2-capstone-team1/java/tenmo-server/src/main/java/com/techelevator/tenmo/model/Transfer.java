package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
	
	private int transferId;
	//transfer_id int
	//transfer_type_id int
	//transfer_type_desc String
	private String transferStatusId;
	//transfer_status_id int
	//transfer_status_desc String
	private String transferType;
	
	//account_from
	//account_to
	
	private int fromUserId;;
	private int toUserId;
	private BigDecimal amount;
	private int userId;
	private String fromUserName;
	private String toUserName;
	private int fromAccountId;
	private int toAccountId;
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
		
	public String getTransferType() {
		return transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	public String getTransferStatusId() {
		return transferStatusId;
	}
	public void setTransferStatusId(String id) {
		this.transferStatusId = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public int getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}
	public int getToUserId() {
		return toUserId;
	}
	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
	public int getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public int getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	

}
