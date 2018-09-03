package com.rb.parser.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.opencsv.bean.CsvBindByName;

@XmlRootElement(name = "record")
public class TransactionDetail {
	@CsvBindByName(column = "AccountNumber")
	private String accountNumber;
	@CsvBindByName(column = "Description")
	private String description;
	@CsvBindByName(column = "Mutation")
	private BigDecimal mutation;
	@CsvBindByName(column = "End Balance")
	private BigDecimal endBalance;
	@CsvBindByName(column = "Start Balance")
	private BigDecimal startBalance;
	@CsvBindByName(column = "Reference")
	private Integer reference;

	public String getAccountNumber() {
		return accountNumber;
	}

	@XmlElement
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getMutation() {
		if(mutation != null) {			
			return mutation.setScale(2);
		}
		return BigDecimal.ZERO;
	}

	@XmlElement
	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}

	public BigDecimal getEndBalance() {
		if(endBalance != null) {			
			return endBalance.setScale(2);
		}
		return BigDecimal.ZERO;
	}

	@XmlElement
	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

	public BigDecimal getStartBalance() {
		if(startBalance != null) {			
			return startBalance.setScale(2);
		}		
		return BigDecimal.ZERO;
	}

	@XmlElement
	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}

	public Integer getReference() {
		return reference;
	}

	@XmlAttribute
	public void setReference(Integer reference) {
		this.reference = reference;
	}

}
