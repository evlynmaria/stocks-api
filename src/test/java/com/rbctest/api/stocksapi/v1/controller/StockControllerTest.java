package com.rbctest.api.stocksapi.v1.controller;


import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.platform.runner.JUnitPlatform;


import com.rbctest.api.stocksapi.v1.entity.Stock;
import com.rbctest.api.stocksapi.v1.service.StockService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class StockControllerTest {
	
	 	@InjectMocks
	    private StockController stockController;
	     
	    @Mock
	    private StockService stockServ;
	     
	    @Test
	    public void testAddStock() 
	    {
	        MockHttpServletRequest request = new MockHttpServletRequest();
	        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	         
	        //when(stockServ.createNewStock(any(Stock.class))).thenReturn(true);
	         
	        Stock stock = new Stock(1, "TEST", "01/07/2011","$15.82","$16.72","$15.78","$16.42","239655616",3.79267,"1.222","3.333","$16.71","$15.97",-4.42849,26,0.182704);
	        ResponseEntity<Object> responseEntity = stockController.createStock(stock);
	     
	        verify(stockServ).createNewStock(stock);

	        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	        assertEquals("Record inserted Successfully", responseEntity.getBody());
	    }
	    
	    
	    @Test
	    public void testAddStockWithFutureDate() 
	    {
	        
	        Stock stock = new Stock(1, "TEST", "01/07/2023","$15.82","$16.72","$15.78","$16.42","239655616",3.79267,"1.222","3.333","$16.71","$15.97",-4.42849,26,0.182704);
	        ResponseEntity<Object> responseEntity = stockController.createStock(stock);
	     
	        verify(stockServ).createNewStock(stock);

	        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	        assertEquals("Date entered is a future date for: TEST on 01/07/2023", responseEntity.getBody());
	    }
	    
	    @Test
	    public void testAddDuplicateStock () 
	    {
	        	         
	        Stock stock = new Stock(1, "TEST", "01/07/2023","$15.82","$16.72","$15.78","$16.42","239655616",3.79267,"1.222","3.333","$16.71","$15.97",-4.42849,26,0.182704);
	        Stock stock2 = new Stock(1, "TEST", "01/07/2023","$15.82","$16.72","$15.78","$16.42","239655616",3.79267,"1.222","3.333","$16.71","$15.97",-4.42849,26,0.182704);
	        ResponseEntity<Object> responseEntity = stockController.createStock(stock);
	     
	        stockServ.createNewStock(stock);
	        verify(stockServ).createNewStock(stock2);

	        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
	        assertEquals("Duplcate Record for :TEST on 01/07/2023", responseEntity.getBody());
	    }
	    
	    @Test
	    public void testFetchMatchingStock () 
	    {
	        	         
	        Stock stock = new Stock(1, "TEST", "01/07/2011","$15.82","$16.72","$15.78","$16.42","239655616",3.79267,"1.222","3.333","$16.71","$15.97",-4.42849,26,0.182704);
	        Stock stock2 = new Stock(1, "TEST", "01/07/2010","$15.82","$16.72","$15.78","$16.42","239655616",3.79267,"1.222","3.333","$16.71","$15.97",-4.42849,26,0.182704);
	       
	        stockServ.createNewStock(stock);
	        (stockServ).createNewStock(stock2);
	        
	        String stockTicker = "TEST";
	        List<Stock> listOfStocks = new ArrayList<>();
	        
	        when(stockServ.findByStockTicker(stockTicker)).thenReturn(listOfStocks);
	        ResponseEntity<Object> responseEntity = stockController.getAllMatchingStocks(stockTicker);
	       
	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	       
	    }
	    
	    
	    @Test
	    public void testFetchMatchingStock_whenNotFound () 
	    {
	    	String stockTicker = "TEST";
	        List<Stock> listOfStocks = new ArrayList<>();
	        
	        when(stockServ.findByStockTicker(stockTicker)).thenReturn(listOfStocks);
	        ResponseEntity<Object> responseEntity = stockController.getAllMatchingStocks(stockTicker);
	       
	        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	       
	    }

}
