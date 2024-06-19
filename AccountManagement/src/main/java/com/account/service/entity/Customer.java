package com.account.service.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Customer {

	private String customerId;
	
	private String name;
	
	private String email;
	
//	private List<Account>  accounts=new ArrayList<>();

}
