package com.ust.item.mdm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;

public class LogisticalItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String _id;

	private String consumableGtin;

	private String supplierGtin;

	private String consumableGtinId;

	private String supplierGtinId;

	private Integer supplierNbr;
	
	private Price rtlPrice;

	private List<String> countryOfOrigin;

	private Date createTs;
	
	private Date expirationDate;
	
	public String getConsumableGtin() {
		return consumableGtin;
	}

	public void setConsumableGtin(String consumableGtin) {
		this.consumableGtin = consumableGtin;
	}

	public String getSupplierGtin() {
		return supplierGtin;
	}

	public void setSupplierGtin(String supplierGtin) {
		this.supplierGtin = supplierGtin;
	}

	public String getConsumableGtinId() {
		return consumableGtinId;
	}

	public void setConsumableGtinId(String consumableGtinId) {
		this.consumableGtinId = consumableGtinId;
	}

	public String getSupplierGtinId() {
		return supplierGtinId;
	}

	public void setSupplierGtinId(String supplierGtinId) {
		this.supplierGtinId = supplierGtinId;
	}

	public Integer getSupplierNbr() {
		return supplierNbr;
	}

	public void setSupplierNbr(Integer supplierNbr) {
		this.supplierNbr = supplierNbr;
	}

	public List<String> getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(List<String> countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Price getRtlPrice() {
		return rtlPrice;
	}

	public void setRtlPrice(Price rtlPrice) {
		this.rtlPrice = rtlPrice;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}
