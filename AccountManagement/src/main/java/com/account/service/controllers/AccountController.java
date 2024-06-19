package com.account.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.account.service.entity.Account;
import com.account.service.entity.Customer;
import com.account.service.exceptions.ResourceNotFoundException;
import com.account.service.external.services.CustomerService;
import com.account.service.services.AccountService;
import com.account.service.payload.ApiResponse;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService; 
	
	//ADD NEW ACCOUNT
	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody Account account){
		Account newAccount = accountService.addAccount(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
	}
		
	// GET ACCOUNT DETAILS BY ACCOUNT NUMBER
	@GetMapping("/{accountNumber}")
	public ResponseEntity<Account> getAccountDetail(@PathVariable int accountNumber) {
		Account existingAccount = accountService.getAccountDetails(accountNumber);
		return ResponseEntity.ok(existingAccount);
	}
	
	// GET ACCOUNT DETAILS BY CUSTOMER ID
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<Account>> getAccountsByCustomerId(@PathVariable String customerId) {
		List<Account> existingAccounts = accountService.getAccountsByCustomerId(customerId);
		return ResponseEntity.ok(existingAccounts);
	}
	
	//GET ALL ACCOUNTS
	@GetMapping
	public ResponseEntity<List<Account>> getAllCustomers(){
		List<Account> allAccounts=accountService.getAllAccounts();
		
		return ResponseEntity.ok(allAccounts);
	}
	
	
	
	// ADD DEPOSIT AMOUNT TO ACCOUNT
	@PutMapping("/depositMoney")
	public ResponseEntity<ApiResponse> depositMoney(@RequestParam int accountNumber, @RequestParam double depositAmount, @RequestParam String customerId){
		Account account=accountService.depositMoney(accountNumber, depositAmount,customerId);
		ApiResponse response = ApiResponse.builder()
                .message(depositAmount+" is deposited to " + accountNumber + " successfully.")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//WITHRAW MONEY FROM ACCOUNT
	@PutMapping("/withdrawMoney")
    public ResponseEntity<ApiResponse> withdrawMoney(@RequestParam int accountNumber, @RequestParam double withdrawAmount, @RequestParam String customerId) {
        Account account = accountService.withdrawMoney(accountNumber, withdrawAmount,customerId);
        ApiResponse response = ApiResponse.builder()
                .message(withdrawAmount+" is withdrawn from " + accountNumber + " successfully.")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	
	//DELETE ACCOUNT
	@DeleteMapping("/{accountNumber}")
	public ResponseEntity<ApiResponse> deleteAccount(@PathVariable int accountNumber){
		accountService.deleteAccount(accountNumber);
		ApiResponse response = ApiResponse.builder()
                .message("Customer with ID " + accountNumber + " has been deleted successfully.")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
	}
 
}
