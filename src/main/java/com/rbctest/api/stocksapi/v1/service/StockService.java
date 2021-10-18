package com.rbctest.api.stocksapi.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbctest.api.stocksapi.v1.repository.StockRepository;
import com.rbctest.api.stocksapi.v1.entity.Stock;
import com.rbctest.api.stocksapi.v1.exception.ResourceNotFound;

@Service
public class StockService {
	
	@Autowired
	StockRepository stockRepo;
	
	 public List<Stock> findByStockTicker(String stockTicker) throws ResourceNotFound {
	
			List<Stock> stockList = stockRepo.findByStock(stockTicker);
			if(stockList.isEmpty())
				throw new ResourceNotFound("There is no matching info found for " +stockTicker);
			else
				return stockList;
	    }

}
