package com.rb.parser.model;

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
	private Double mutation;
	@CsvBindByName(column = "End Balance")
	private Double endBalance;
	@CsvBindByName(column = "Start Balance")
	private Double startBalance;
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

	public Double getMutation() {
		return mutation;
	}

	@XmlElement
	public void setMutation(Double mutation) {
		this.mutation = mutation;
	}

	public Double getEndBalance() {
		return endBalance;
	}

	@XmlElement
	public void setEndBalance(Double endBalance) {
		this.endBalance = endBalance;
	}

	public Double getStartBalance() {
		return startBalance;
	}

	@XmlElement
	public void setStartBalance(Double startBalance) {
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
