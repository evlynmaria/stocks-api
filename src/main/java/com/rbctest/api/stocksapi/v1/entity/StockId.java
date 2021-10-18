package com.rbctest.api.stocksapi.v1.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StockId implements Serializable {

	private static final long serialVersionUID = -8470153465387866703L;

	private String stock;

	private String date;

}
