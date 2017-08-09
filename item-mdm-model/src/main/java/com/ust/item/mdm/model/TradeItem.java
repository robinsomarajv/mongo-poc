package com.ust.item.mdm.model;

import java.io.Serializable;
import java.util.Date;

public class TradeItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String _id;
	
	private String name;
	
	private String desc;
	
	private String gtin;
	
	private Boolean isMasterCarton;
	
	private Integer ti;
	
	private Integer hi;
	
	private Dimensions tiDimensions;
	
	private Date createTs;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public Boolean getIsMasterCarton() {
		return isMasterCarton;
	}

	public void setIsMasterCarton(Boolean isMasterCarton) {
		this.isMasterCarton = isMasterCarton;
	}

	public Integer getTi() {
		return ti;
	}

	public void setTi(Integer ti) {
		this.ti = ti;
	}

	public Integer getHi() {
		return hi;
	}

	public void setHi(Integer hi) {
		this.hi = hi;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	public Dimensions getTiDimensions() {
		return tiDimensions;
	}

	public void setTiDimensions(Dimensions tiDimensions) {
		this.tiDimensions = tiDimensions;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}
