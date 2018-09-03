package com.rb.parser.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rb.parser.model.TransactionDetail;

public class TransactionValidation {
	private static final Logger logger = LoggerFactory.getLogger(TransactionValidation.class);

	public List<TransactionDetail> validate(List<TransactionDetail> transactionDetails) {

		Set<Integer> uniqueTransaction = new HashSet<>();
		List<TransactionDetail> failedTransaction = new ArrayList<>();
		for (TransactionDetail td : transactionDetails) {
			// Checks for the duplicate and end balance validation
			if (!uniqueTransaction.add(td.getReference())) {
				logger.debug("Duplicate transaction ref: " + td.getReference());
				failedTransaction.add(td);
			} else if (!td.getEndBalance().equals((td.getMutation().add(td.getStartBalance())))) {
				logger.debug("End balance validation failure ref: " + td.getReference());
				failedTransaction.add(td);
			}
		}
		return failedTransaction.size() > 0 ? failedTransaction : null;
	}
}
