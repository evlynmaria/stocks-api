package com.rbctest.api.stocksapi.v1.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(StockId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

	@NotNull(message = "Quarter is mandatory")
	@CsvBindByName(column = "quarter", required = true)
	private int quarter;

	@Id
	@NotNull(message = "Stock Ticker is mandatory")
	@CsvBindByName(column = "stock", required = true)
	private String stock;

	@Id
	@NotEmpty(message = "Stock Date is mandatory")
	@CsvBindByName(column = "date", required = true)
	private String date;
	
	@CsvBindByName
	private String open;

	@CsvBindByName
	private String high;

	@CsvBindByName
	private String low;
	
	@CsvBindByName
	private String close;
	
	@CsvBindByName
	private String volume;

	@Column(name = "percent_change_price")
	@CsvBindByName(column = "percent_change_price")
	private double percentChangePrice;

	@Column(name = "percent_change_volume_over_last_wk")
	@CsvBindByName(column = "percent_change_volume_over_last_wk")
	private String percentChangeVolumeOverLastWk;

	@Column(name = "previous_weeks_volume")
	@CsvBindByName(column = "previous_weeks_volume")
	private String previousWeeksVolume;

	@Column(name = "next_weeks_open")
	@CsvBindByName(column = "next_weeks_open")
	private String nextWeeksOpen;

	@Column(name = "next_weeks_close")
	@CsvBindByName(column = "next_weeks_close")
	private String nextWeeksClose;

	@Column(name = "percent_change_next_weeks_price")
	@CsvBindByName(column = "percent_change_next_weeks_price")
	private double percentChangeNextWeeksPrice;

	@Column(name = "days_to_next_dividend")
	@CsvBindByName(column = "days_to_next_dividend")
	private int daysToNextDividend;

	@Column(name = "percent_return_next_dividend")
	@CsvBindByName(column = "percent_return_next_dividend")
	private double percentReturnNextDividend;

}
