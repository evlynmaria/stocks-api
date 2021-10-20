package com.rbctest.api.stocksapi.v1.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rbctest.api.stocksapi.v1.entity.Stock;
import com.rbctest.api.stocksapi.v1.service.StockService;

public class CSVFileHelper {

	private static Logger log = LoggerFactory.getLogger(CSVFileHelper.class);

	@Autowired
	public static StockService stockServ;

	public static String TYPE = Constants.FILE_TYPE;

	public static boolean isCSVFormat(MultipartFile file) {
		return TYPE.equals(file.getContentType());
	}

	public static List<Stock> rowsToStock(InputStream inputStream) {

		try (Reader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			CsvToBean<Stock> csvToBean = new CsvToBeanBuilder<Stock>(reader).withType(Stock.class).withSeparator(',')
					.withIgnoreLeadingWhiteSpace(true).build();

			List<Stock> stocks = csvToBean.parse();

			log.info("Read " + stocks.size() + " rows from the file uploaded");

			return removeInvalidStock(stocks);
		} catch (Exception e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	// method to remove the stock entry that fails the validation
	public static List<Stock> removeInvalidStock(List<Stock> stockList) {
		stockList.stream().filter(stock -> stockServ.validateStock(stock));
		return stockList;
	}

}
