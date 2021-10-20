package com.rbctest.api.stocksapi.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbctest.api.stocksapi.v1.entity.Stock;
import com.rbctest.api.stocksapi.v1.exception.DuplicateEntry;
import com.rbctest.api.stocksapi.v1.exception.InvalidDateEntry;
import com.rbctest.api.stocksapi.v1.exception.ResourceNotFound;
import com.rbctest.api.stocksapi.v1.service.StockService;
import com.rbctest.api.stocksapi.v1.util.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/rbc-test/api/v1")
public class StockController {

	@Autowired
	StockService stockService;

	@GetMapping("/stocks/{stockTicker}")
	@Operation(summary = "Find all matching stocks", description = "The service helps to fetch a list of available stock info")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found matching records"),
			@ApiResponse(responseCode = "500", description = "Internal server error"),
			@ApiResponse(responseCode = "404", description = "No Matching records found") })
	public ResponseEntity<Object> getAllMatchingStocks(@PathVariable(value = "stockTicker") final String stock) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(stockService.findByStockTicker(stock));
		} catch (ResourceNotFound e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@PostMapping("/stocks")
	@Operation(summary = "Insert Stock", description = "Insert info about a stock")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Stock created successfully"),
			@ApiResponse(responseCode = "409", description = "Stock Info already exisit"),
			@ApiResponse(responseCode = "400", description = "Invalid date entry"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	public ResponseEntity<Object> createStock(@Valid @RequestBody Stock stock) {
		try {
			stockService.createNewStock(stock);
		} catch (DuplicateEntry e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (InvalidDateEntry e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(Constants.INSERT_SUCCESS);

	}

}
