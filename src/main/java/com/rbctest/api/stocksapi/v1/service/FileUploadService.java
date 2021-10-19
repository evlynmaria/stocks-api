package com.rbctest.api.stocksapi.v1.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rbctest.api.stocksapi.v1.entity.Stock;
import com.rbctest.api.stocksapi.v1.repository.StockRepository;
import com.rbctest.api.stocksapi.v1.util.CSVFileHelper;

@Service
public class FileUploadService {

	@Autowired
	StockRepository stockRepo;

	private static Logger log = LoggerFactory.getLogger(FileUploadService.class);

	public void batchUpload(MultipartFile file) {
		try {
			log.info("batch upload started");
			List<Stock> stocks = CSVFileHelper.rowsToStock(file.getInputStream());
			log.info("Found " + stocks.size() + " valid stock from the file uploaded");
			stockRepo.saveAll(stocks);
		} catch (IOException e) {
			log.error("batch upload failed");
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

}
