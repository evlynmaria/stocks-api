package com.rbctest.api.stocksapi.v1.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rbctest.api.stocksapi.v1.entity.Stock;
import com.rbctest.api.stocksapi.v1.repository.StockRepository;
import com.rbctest.api.stocksapi.v1.service.StockService;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

	@Mock
	private StockRepository stockRepo;

	@InjectMocks
	private StockService stockService;

	@Test
	public void testfindByStockTicker() {
		String stockTicker = "TEST";
		Stock stock = new Stock(1, "TEST", "01/07/2011", "$15.82", "$16.72", "$15.78", "$16.42", "239655616", 3.79267,
				"1.222", "3.333", "$16.71", "$15.97", -4.42849, 26, 0.182704);
		Stock stock2 = new Stock(2, "TEST", "01/07/2010", "$15.82", "$16.72", "$15.78", "$16.42", "239655616", 3.79267,
				"1.222", "3.333", "$16.71", "$15.97", -4.42849, 26, 0.182704);

		List<Stock> listOfStocks = new ArrayList<>();
		listOfStocks.add(stock2);
		listOfStocks.add(stock);

		when(stockRepo.findByStock(stockTicker)).thenReturn(listOfStocks);
		List<Stock> returnedStocks = stockService.findByStockTicker(stockTicker);
		assertEquals(2, returnedStocks.size());
		assertEquals(1, returnedStocks.get(0).getQuarter());
		assertEquals("TEST", returnedStocks.get(0).getStock());
		assertEquals("01/07/2011", returnedStocks.get(0).getDate());

		assertEquals(2, returnedStocks.get(1).getQuarter());
		assertEquals("TEST", returnedStocks.get(1).getStock());
		assertEquals("01/07/2010", returnedStocks.get(1).getDate());

	}

	@Test
	public void testvalidateDate() {
		String newDate = "11/11/2020";
		assertTrue(stockService.validateDate(newDate));
	}

	@Test
	public void testvalidateDate_invalid() {
		String newDate = "11/11/2022";
		assertFalse(stockService.validateDate(newDate));
	}

}
