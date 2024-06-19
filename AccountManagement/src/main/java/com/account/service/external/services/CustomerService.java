package com.account.service.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.account.service.entity.Customer;



@FeignClient(name="CUSTOMER-MANAGEMENT-SERVICE")
public interface CustomerService {
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomerDetails(@PathVariable String customerId);

}
