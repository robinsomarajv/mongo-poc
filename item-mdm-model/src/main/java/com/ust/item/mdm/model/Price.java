package com.ust.item.mdm.model;

import java.io.Serializable;

public class Price implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String currency;
	
	private double value;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
