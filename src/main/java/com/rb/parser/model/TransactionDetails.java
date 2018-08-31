package com.rb.parser.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="records")
public class TransactionDetails {
	
	
	private List<TransactionDetail> transactionDetails;

	public List<TransactionDetail> getTransactionDetails() {
		return transactionDetails;
	}
	
	@XmlElement(name="record")
	public void setTransactionDetails(List<TransactionDetail> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	
}
