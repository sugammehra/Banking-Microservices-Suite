package com.account.service.services;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.account.service.entity.Account;

@Repository
public interface AccountService {
	
	//ADD ACCOUNT 
	Account addAccount(Account account) ;
	
	//DEPOSIT MONEY
	Account depositMoney(int accountNumber,double depositAmount, String customerId);
	
	//WITHDRAW MONEY
	Account withdrawMoney(int accountNumber, double withdrawAmount, String customerId);
	
	
	//GET SINGLE ACCOUNT DETAIL
	Account getAccountDetails(Integer accountNumber);
	
	//GET ALL ACCOUNTS
	List<Account> getAllAccounts();
	
	//GET ACCOUNT BY CUSTOMER ID
	List<Account> getAccountsByCustomerId(String customerId);
	
	//DELETE ACCOUNT 
	Account deleteAccount(Integer accountNumber);
	
	// CHECK CUSTOMER ID IS VALID OR NOT
	Boolean isValidCustomer(String customerId);
	
	// we will add getCustomerDetail from customerId
	
	

}
