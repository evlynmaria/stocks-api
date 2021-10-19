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

import com.rbctest.api.stocksapi.util.Constants;
import com.rbctest.api.stocksapi.v1.service.FileUploadService;
import com.rbctest.api.stocksapi.v1.util.CSVFileHelper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;



@RestController
@RequestMapping("/rbc-test/api/v1")
public class BatchUploadController {
	
	@Autowired
	FileUploadService fileServ;
	
	
	@PostMapping(value = "/uploadCSV", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "Batch upload Stock Info", description = "The API helps in uploading CSV so as to add multiple stock info simultaneously. "
			+ 	"	The CSV should contain the following heading:\n" +
	            "quarter,stock,date,open,high,low,close,volume,percent_change_price,percent_change_volume_over_last_wk,\n"+
	            "previous_weeks_volume,next_weeks_open,next_weeks_close,percent_change_next_weeks_price,days_to_next_dividend,percent_return_next_dividend")
	@ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "File Upload Successful"),
	            @ApiResponse(responseCode = "406", description = "File type mismatch"),
	            @ApiResponse(responseCode = "406", description = "File type mismatch"),
	            @ApiResponse(responseCode = "500", description = "Internal server error")})
	public ResponseEntity<Object> batchStockUpload(	@RequestPart(required = true) MultipartFile file){
		  

		    if (!CSVFileHelper.isCSVFormat(file)) {
		    	 return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Constants.INCORRECT_FILE_FORMAT);
		    }
		    
		    if(file.getSize() == 0)
		    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.FILE_EMPTY);
		    
		      try {
		    	  fileServ.batchUpload(file);
		        return ResponseEntity.status(HttpStatus.OK).body(Constants.UPLOAD_SUCCESS);
		      } catch (Exception e) {
		        
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(Constants.UPLOAD_FAILED);
		      }
		    }

		   
		  
		
	

}
