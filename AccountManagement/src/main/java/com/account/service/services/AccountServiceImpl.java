package com.account.service.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.account.service.entity.Account;
import com.account.service.entity.Customer;
import com.account.service.repository.AccountRepository;
import com.account.service.exceptions.ResourceNotFoundException;
import com.account.service.external.services.CustomerService;
import com.account.service.exceptions.CustomDatabaseException;

@Service
public class AccountServiceImpl implements AccountService{
	
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerService customerService;
	
	// CREATE NEW ACCOUNT
	@Override
	public Account addAccount(Account account) {
		try {
            String customerId = account.getCustomerId();
            logger.debug("Fetching customer details for ID: {}", customerId);

            Customer customer = customerService.getCustomerDetails(customerId);
            logger.debug("Customer details retrieved: {}", customer);

            if (customer != null) {
                account.setCustomerDetails(customer);
                return accountRepository.save(account);
            } else {
                throw new CustomDatabaseException("Customer not found with ID: " + customerId);
            }
		} catch (DataIntegrityViolationException e) {
	        throw new CustomDatabaseException("Constraint violation - possibly duplicate entry", e);
	    } catch (Exception e) {
	        throw new CustomDatabaseException("An error occurred while adding the Account", e);
	    }
	}
	
	// GET ALL ACCOUNTS DETAILS
	@Override
	public List<Account> getAllAccounts() {
		try {
			List<Account> allAccounts=accountRepository.findAll();
			for (Account existingAccount : allAccounts) {
				String customerId=existingAccount.getCustomerId();
				Customer customer= customerService.getCustomerDetails(customerId);
				existingAccount.setCustomerDetails(customer);
				accountRepository.save(existingAccount);
			}
	        return accountRepository.findAll();
	    } catch (Exception e) {
	        throw new CustomDatabaseException("An error occurred while retrieving customers", e);
	    }
	}
	
	// GET SINGLE ACCOUNT DETAILS BY ACCOUNT NUMBER 
	@Override
	public Account getAccountDetails(Integer accountNumber) {
		try {
			Optional<Account>  account=accountRepository.findById(accountNumber);
			if(account.isPresent()) {
				Account existingAccount=account.get();
				String customerId=existingAccount.getCustomerId();
				Customer customer= customerService.getCustomerDetails(customerId);
				existingAccount.setCustomerDetails(customer);
				accountRepository.save(existingAccount);
				return account.get();
			}else {
				throw new ResourceNotFoundException("Account not found with id: " + accountNumber);
			}
		} catch(Exception e) {
			throw new CustomDatabaseException("An error occurred while fetching account with id " + accountNumber, e);
		}
		
		
	}
	
	
	// DELETE ACCOUNT
	@Override
	public Account deleteAccount(Integer accountNumber) {
		try {
	        Optional<Account> account = accountRepository.findById(accountNumber);
	        if (account.isPresent()) {
	            accountRepository.deleteById(accountNumber);
	            return account.get();
	        } else {
	            throw new ResourceNotFoundException("Account not found with id " + accountNumber);
	        }
	    } catch (Exception e) {
	        throw new CustomDatabaseException("An error occurred while trying to delete the account with id " + accountNumber, e);
	    }
	}

	
	// GET ACCOUNT DETAIL BY CUSTOMER ID
	@Override
	public List<Account> getAccountsByCustomerId(String customerId) {
		try {
			List<Account> accountList=accountRepository.findByCustomerId(customerId);
			return accountList;
			
		}catch (Exception e) {
	        throw new CustomDatabaseException("An error occurred while fetching the account with customer id " + customerId, e);
	    }
		
		
	}
	

	
	// CHECK CUSTOMER ID IS VALID OR NOT ( FOR DEPOSIT AND WITHDRAWL REQUEST)
	// ADD MONEY TO ACCOUNT
	@Override
	public Account depositMoney(int accountNumber, double depositAmount, String customerId) {
		try {
			if(!isValidCustomer(customerId)) {
				throw new ResourceNotFoundException("Customer does not exist with ID: "+ customerId);
			}
			Optional<Account>  existingAccount=accountRepository.findById(accountNumber);
			if(existingAccount.isPresent()) {
				Account account=existingAccount.get();
				if(account.getCustomerId().equals(customerId)) {
					double newBalance=account.getBalance()+depositAmount;
					account.setBalance(newBalance);
					accountRepository.save(account);
					return account;
				}else {
					throw new ResourceNotFoundException("Invalid Customer ID: "+ customerId);
				}
			}else {
				throw new ResourceNotFoundException("Account not found with id: " + accountNumber);
			}
		} catch(Exception e) {
			throw new ResourceNotFoundException("Invalid Customer ID: "+ customerId);
		}
		
	}
	
	// WITHRAW MONEY FROM THE ACCOUNT 
	@Override
	public Account withdrawMoney(int accountNumber, double withdrawAmount, String customerId) {
		try {
			// IF CUSTOMER IS NOT VALID
			if(!isValidCustomer(customerId)) {
				throw new ResourceNotFoundException("Customer does not exist with ID: "+ customerId);
			}
			
			Optional<Account>  existingAccount=accountRepository.findById(accountNumber);
			if(existingAccount.isPresent()) {
				Account account=existingAccount.get();
				// IF CUSTOMER ID NOT MATCH WITH ACCOUNT NUMBER AND BAL GREATER THAN WITHDRAW AMOUNT
				if (account.getCustomerId().equals(customerId) && account.getBalance() >= withdrawAmount) {
					account.setBalance(account.getBalance() - withdrawAmount);
                    accountRepository.save(account);
                    return account;
				}else {
					throw new CustomDatabaseException("Insufficient balance for withdrawal");
				}
				
			}else {
				throw new ResourceNotFoundException("Account not found with id: " + accountNumber);
			}
		} catch(Exception e) {
			throw new ResourceNotFoundException("Invalid Customer ID: "+ customerId);
		}
	}
	
	// CHECK CUSTOMER ID IS VALID OR NOT
	@Override
	public Boolean isValidCustomer(String customerId) {
		try {
			Customer customer=customerService.getCustomerDetails(customerId);
			return customer!=null;
		}catch (Exception e) {
			return false;
		}
	}

	

}
