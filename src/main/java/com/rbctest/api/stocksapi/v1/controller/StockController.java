package com.rbctest.api.stocksapi.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rbctest.api.stocksapi.v1.entity.Stock;
import com.rbctest.api.stocksapi.v1.exception.ResourceNotFound;
import com.rbctest.api.stocksapi.v1.repository.StockRepository;
import com.rbctest.api.stocksapi.v1.service.StockService;

@RestController
@RequestMapping(value = "/rbc-test/api/v1")
public class StockController {
	
	@Autowired
	StockService stockService;
	
	@GetMapping("/stocks/{stockTicker}")
	public ResponseEntity<?> getAllMatchingStocks(@PathVariable(value = "stockTicker") final String stock){
			try {
				return ResponseEntity.status(HttpStatus.OK).body(stockService.findByStockTicker(stock));
			} catch (ResourceNotFound e) {
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}  catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
			
	}

}
