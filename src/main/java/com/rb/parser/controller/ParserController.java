package com.rb.parser.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rb.parser.model.TransactionDetail;
import com.rb.parser.response.Response;
import com.rb.parser.response.Status;
import com.rb.parser.util.CSVParser;
import com.rb.parser.util.Parser;
import com.rb.parser.util.TransactionValidation;
import com.rb.parser.util.XMLParser;

@RestController
public class ParserController {

	private static final Logger logger = LoggerFactory.getLogger(ParserController.class);

	@PostMapping("/processReport")
	public ResponseEntity<?> processReport(@RequestParam("file") MultipartFile multipartFile) {
		FileOutputStream fos = null;
		File file = null;
		Status status;
		Response response;
		try {
			Parser parser;
			if (multipartFile != null && multipartFile.getOriginalFilename() != null) {
				file = new File(multipartFile.getOriginalFilename());
				fos = new FileOutputStream(file);
				fos.write(multipartFile.getBytes());
				fos.close();
				String fileName = multipartFile.getOriginalFilename();
				switch (fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase()) {
				case "csv":
					logger.debug("File identified as csv");
					parser = new CSVParser();
					break;
				case "xml":
					logger.debug("File identified as xml");
					parser = new XMLParser();
					break;
				default:
					status = new Status(HttpStatus.BAD_REQUEST.value(), "File is invalid");
					response = new Response(null, status);
					return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
				}
				List<TransactionDetail> failedDetails = new TransactionValidation().validate(parser.parse(file));
				status = new Status(HttpStatus.OK.value(), HttpStatus.OK.name());
				response = new Response(failedDetails, status);
				return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
			}
		} catch (Exception e) {
			logger.error("Error on processing the file", e);
			status = new Status(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
			response = new Response(null, status);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
		} finally {
			try {
				if (file != null) {
					file.delete();
					file = null;
				}
				if (fos != null) {
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
				logger.error("Closing the fos ", e);
			}
		}
		status = new Status(HttpStatus.BAD_REQUEST.value(), "File is invalid");
		response = new Response(null, status);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}
}
