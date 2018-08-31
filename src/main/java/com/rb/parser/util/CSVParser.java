package com.rb.parser.util;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rb.parser.model.TransactionDetail;

public class CSVParser implements Parser {
	private static final Logger logger = LoggerFactory.getLogger(CSVParser.class);

	@Override
	public List<TransactionDetail> parse(File file) {
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
			CsvToBean<TransactionDetail> transactionDetails = new CsvToBeanBuilder<TransactionDetail>(reader)
					.withType(TransactionDetail.class).withIgnoreLeadingWhiteSpace(true).build();
			return transactionDetails.parse();
		} catch (IOException e) {
			logger.error("Error in processing the csv file ", e);
		}finally {
			if(reader != null) {
				try {
					reader.close();
					reader = null;
				} catch (IOException e) {
					logger.error("Error in closing file ", e);
				}
			}
		}
		return null;
	}

}
