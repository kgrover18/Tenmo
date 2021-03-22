package com.techelevator.tenmo.services;


import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class AccountService {
	public static String AUTH_TOKEN = "";
	private final String BASE_URL;
	private RestTemplate restTemplate = new RestTemplate();
	
	
	public AccountService (String url) {
		this.BASE_URL = url;
	}
	
	public BigDecimal getBalance(int user_id, String authorization) {
		BigDecimal balance = null;
		balance = restTemplate.exchange(BASE_URL + "account/" + user_id + "/balance",
	    		HttpMethod.GET, makeAccountEntity(authorization), BigDecimal.class).getBody();
		return balance;
	}
	
	
	public User[] getAllUsers(String authorization){
		//String tester = BASE_URL + "account/findUser";
		User[] userList = restTemplate.exchange(BASE_URL + "account/findUsers",
	    		HttpMethod.GET, makeAccountEntity(authorization), User[].class).getBody();
		return userList;
	}
	
	// new transfer methods
	
	public Transfer getTransferDetails( int id, String authorization ) {
		Transfer transfer =  restTemplate.exchange(BASE_URL + "account/" + id + "/transfer",
	    		HttpMethod.GET, makeAccountEntity(authorization), Transfer.class).getBody();
		return transfer;
	}
	public Transfer[] getAllTransfersFromId( int id, String authorization ) {
		Transfer[] allTransfers =  restTemplate.exchange(BASE_URL + "account/" + id + "/allTransfers",
	    		HttpMethod.GET, makeAccountEntity(authorization), Transfer[].class).getBody();
		return allTransfers;
	}
	
	
	
private HttpEntity<Account> makeAccountEntity(String authorization){
	HttpHeaders headers = new HttpHeaders();
	headers.setBearerAuth(authorization);
	HttpEntity entity = new HttpEntity<>(headers);
	return entity;
}
}
