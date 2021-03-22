package com.techelevator.tenmo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.DoubleDTO;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

//@PreAuthorize("isAuthenticated()")
@RequestMapping("/account")
@RestController
public class AccountController {
	
	private AccountDAO accountDAO;
	private UserDAO userDAO;
	private TransferDAO transferDAO;
	public AccountController (AccountDAO accountDAO, UserDAO userDAO, TransferDAO transferDAO) {	
		this.accountDAO = accountDAO;
		this.userDAO = userDAO;
		this.transferDAO = transferDAO;
	
	}
	@RequestMapping (path = "/{id}/balance", method = RequestMethod.GET)
	public BigDecimal getBalance (@PathVariable int id) {
		return accountDAO.getAccountBalance(id);
	}
	@RequestMapping (path = "/findUsers", method = RequestMethod.GET)
	public User[] getUsers (){
		return userDAO.findAll().toArray(new User[0]);
	}
	@RequestMapping (path = "/{id}", method = RequestMethod.GET)
	public Account getAccount(@PathVariable int id) {
		//System.out.println("in getAccount");
		return accountDAO.getAccountById(id);
	}
	@RequestMapping (path = "/{id}/update", method = RequestMethod.PUT)
	public void updateAccountBalance(@PathVariable int id, @RequestBody DoubleDTO balance) {
		accountDAO.updateAccountBalance(id, balance.getNumber());
	}
	@RequestMapping(path = "/{id}/transfer", method = RequestMethod.GET)
	public Transfer transferDetails(@PathVariable int id) {
		//System.out.println("in transfer details");
		return transferDAO.viewTransferDetails(id);
	}
	@RequestMapping(path = "{id}/allTransfers", method = RequestMethod.GET)
	public Transfer[] getTransferList (@PathVariable int id) {
		//System.out.println("in all transfers");
		return transferDAO.viewTransfers(id).toArray(new Transfer[0]);
	}
	@RequestMapping(path = "", method = RequestMethod.POST)
	public void sendBucks
	

}
