package com.rbctest.api.stocksapi.v1.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbctest.api.stocksapi.v1.entity.Stock;
import com.rbctest.api.stocksapi.v1.entity.StockId;
import com.rbctest.api.stocksapi.v1.exception.DuplicateEntry;
import com.rbctest.api.stocksapi.v1.exception.InvalidDateEntry;
import com.rbctest.api.stocksapi.v1.exception.ResourceNotFound;
import com.rbctest.api.stocksapi.v1.repository.StockRepository;

@Service
public class StockService {

	private static Logger log = LoggerFactory.getLogger(StockService.class);

	@Autowired
	StockRepository stockRepo;

	// method to fetch all matching stocks
	public List<Stock> findByStockTicker(String stockTicker) throws ResourceNotFound {
		log.info("Finding matches for given stock Ticker : " + stockTicker);
		List<Stock> stockList = stockRepo.findByStock(stockTicker);
		if (stockList.isEmpty()) {
			log.error("No matching records found for : " + stockTicker);
			throw new ResourceNotFound("There is no matching info found for " + stockTicker);
		} else
			return stockList;
	}

	// method to create new stock
	public void createNewStock(Stock stock) throws DuplicateEntry, InvalidDateEntry {
		if (!validateDate(stock.getDate())) {
			log.error("Date entered is a future date for:" + stock.getStock() + "date given : " + stock.getDate());
			throw new InvalidDateEntry(
					"Date entered is a future date for: " + stock.getStock() + " date given : " + stock.getDate());
		}

		if (checkForDuplicates(new StockId(stock.getStock(), stock.getDate()))) {
			log.error("Duplcate Record for :" + stock.getStock() + "on " + stock.getDate());
			throw new DuplicateEntry("Duplcate Record for : " + stock.getStock() + " on " + stock.getDate());
		}

		stockRepo.save(stock);

	}

	// method to validate if date is in the past
	public boolean validateDate(String date) {

		Date enteredDate = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			enteredDate = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date currentDate = new Date();
		return enteredDate.before(currentDate);
	}

	// method to validate if a stock meets the basic validation
	public boolean validateStock(Stock stock) {
		return !stock.getDate().isEmpty() || !stock.getStock().isEmpty() || validateDate(stock.getDate())
				|| !checkForDuplicates(new StockId(stock.getStock(), stock.getDate()));
	}

	// method to validate if a stock already exist for the given date
	public boolean checkForDuplicates(StockId stock_key) {
		return stockRepo.findById(stock_key).isPresent();
	}

}
