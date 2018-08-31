package com.rb.parser.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.rb.parser.model.TransactionDetail;
/**
 * Description: This is parse the input
 * @author Raj Kumar0
 * @date 29 August 2018
 */
public interface Parser {
	/**
	 * Parse and return the list of transaction details
	 * @param file
	 * @return list of transaction details
	 * @throws JAXBException 
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public List<TransactionDetail> parse(File file) throws JAXBException, IllegalStateException, IOException;
}
