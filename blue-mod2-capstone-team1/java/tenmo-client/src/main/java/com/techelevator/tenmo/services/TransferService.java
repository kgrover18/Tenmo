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


public class TransferService {
	public static String AUTH_TOKEN = "";
	private final String BASE_URL;
	private RestTemplate restTemplate = new RestTemplate();
	
	
	public TransferService (String url) {
		this.BASE_URL = url;
	}	
	public void sendBucks(Transfer transfer) {
		HttpEntity<Transfer> entity = makeTransferEntity(transfer);	
		restTemplate.postForObject(BASE_URL + "account/transfer/" + transfer.getFromUserId(),
	    	 entity, Transfer.class);		
	}	
	public Transfer viewTransferDetails (int userId) {
		Transfer transfer = new Transfer();
		transfer = restTemplate.exchange(BASE_URL + "account/transfer/" + userId,
	    		HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();
				return transfer;
	}
	public Transfer[] viewTransfers (int transferId) {
		Transfer[] transferList = restTemplate.exchange(BASE_URL + "account/findUsers",
	    		HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
		return transferList;
	}
	private HttpEntity makeAuthEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	}
	private HttpEntity<Account> makeAccountEntity(String authorization){
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(authorization);
		HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	}
	private HttpEntity<Transfer> makeTransferEntity(Transfer transfer){
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(AUTH_TOKEN);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
		return entity;
	}
	
	
}
