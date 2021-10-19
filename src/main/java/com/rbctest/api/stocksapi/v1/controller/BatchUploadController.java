package com.rbctest.api.stocksapi.v1.controller;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rbctest.api.stocksapi.Constants;
import com.rbctest.api.stocksapi.v1.service.FileUploadService;
import com.rbctest.api.stocksapi.v1.util.CSVFileHelper;



@RestController
@RequestMapping("/rbc-test/api/v1")
public class BatchUploadController {
	
	@Autowired
	FileUploadService fileServ;
	
	
	@PostMapping(value = "/uploadCSV", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> batchStockUpload(	@RequestPart(required = true) MultipartFile file){
		  

		    if (!CSVFileHelper.isCSVFormat(file)) {
		    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.INCORRECT_FILE_FORMAT);
		    }
		      try {
		    	  fileServ.batchUpload(file);
		        return ResponseEntity.status(HttpStatus.OK).body(Constants.UPLOAD_SUCCESS);
		      } catch (Exception e) {
		        
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(Constants.UPLOAD_FAILED);
		      }
		    }

		   
		  
		
	

}
