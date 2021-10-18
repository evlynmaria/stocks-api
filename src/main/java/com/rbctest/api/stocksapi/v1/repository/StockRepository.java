package com.rbctest.api.stocksapi.v1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbctest.api.stocksapi.v1.entity.StockId;
import com.rbctest.api.stocksapi.v1.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, StockId> {

	List<Stock> findByStock(String stock);

}
