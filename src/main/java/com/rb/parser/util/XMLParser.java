package com.rb.parser.util;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rb.parser.model.TransactionDetail;
import com.rb.parser.model.TransactionDetails;


public class XMLParser implements Parser {
	private static final Logger logger = LoggerFactory.getLogger(XMLParser.class);

	@Override
	public List<TransactionDetail> parse(File file) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(TransactionDetails.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TransactionDetails transactionDetails = (TransactionDetails) jaxbUnmarshaller.unmarshal(file);
			if(transactionDetails != null) {
				return transactionDetails.getTransactionDetails();
			}
		} catch (JAXBException e) {
			logger.error("Error parsing the xml", e);
		}
		return null;
	}
}
