package com.ust.item.mdm.model;

import java.io.Serializable;

public class Dimensions implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double length;

	private Double width;

	private Double height;

	private String measuringUnit;

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getMeasuringUnit() {
		return measuringUnit;
	}

	public void setMeasuringUnit(String measuringUnit) {
		this.measuringUnit = measuringUnit;
	}
}
