package com.rbctest.api.stocksapi.v1.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotBlank;

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

	@NotBlank(message = "Quarter is mandatory")
	private int quarter;

	@Id
	@NotBlank(message = "Stock Ticker is mandatory")
	private String stock;

	@Id
	@NotBlank(message = "Stock Date is mandatory")
	private String date;

	private BigDecimal open;

	private BigDecimal high;

	private BigDecimal low;

	private BigDecimal close;

	private Long volume;

	@Column(name = "percent_change_price")
	private double percentChangePrice;

	@Column(name = "percent_change_volume_over_last_wk")
	private String percentChangeVolumeOverLastWk;

	@Column(name = "previous_weeks_volume")
	private String previousWeeksVolume;

	@Column(name = "next_weeks_open")
	private BigDecimal nextWeeksOpen;

	@Column(name = "next_weeks_close")
	private BigDecimal nextWeeksClose;

	@Column(name = "percent_change_next_weeks_price")
	private double percentChangeNextWeeksPrice;

	@Column(name = "days_to_next_dividend")
	private int daysToNextDividend;

	@Column(name = "percent_return_next_dividend")
	private double percentReturnNextDividend;

}
