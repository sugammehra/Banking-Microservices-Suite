package com.account.service.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name="accounts")
public class Account {
	@Id
	@Column(name="account_no")
	private Integer accountNumber;
	
	@Column(name="balance")
	private double balance;
	
	@Column(name="account_holder")
	private String accountHolderName;
	
	@Column(name="customerId")
	private String customerId;
	
	@Transient
	private Customer customerDetails;
	
	
	
	
}
