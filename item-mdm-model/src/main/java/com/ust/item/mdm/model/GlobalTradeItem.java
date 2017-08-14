package com.ust.item.mdm.model;

import java.io.Serializable;
import java.util.List;

public class GlobalTradeItem implements Serializable {

	private GlobalTradeItemId _id;
	private String tradeItemPackTypeCode;
	private Boolean isConsumableInd;
	private Boolean isOrderableInd;
	private String tradeItemDescription;
	private Integer batteryTypeCode;
	private String tradeItemWeightUomCode;
	private Double tradeItemWeightQty;
	private String tradeItemDimensionsUomCode;
	private Double tradeItemDimensionsWidthQty;
	private Double tradeItemDimensionsDepthQty;
	private Double tradeItemDimensionsHeightQty;
	private Boolean isConveyableInd;
	private Integer totalQuantityOfNextLowerLevelTradeItem;
	private Integer palletTiQty;
	private Integer palletHiQty;
	private List<GlobalTradeItemId> parent = null;
	private List<GlobalTradeItemId> children = null;
	private final static long serialVersionUID = 6453965612560132070L;

	public GlobalTradeItemId get_id() {
		return _id;
	}

	public void set_id(GlobalTradeItemId _id) {
		this._id = _id;
	}

	public String getTradeItemPackTypeCode() {
		return tradeItemPackTypeCode;
	}

	public void setTradeItemPackTypeCode(String tradeItemPackTypeCode) {
		this.tradeItemPackTypeCode = tradeItemPackTypeCode;
	}

	public Boolean getIsConsumableInd() {
		return isConsumableInd;
	}

	public void setIsConsumableInd(Boolean isConsumableInd) {
		this.isConsumableInd = isConsumableInd;
	}

	public Boolean getIsOrderableInd() {
		return isOrderableInd;
	}

	public void setIsOrderableInd(Boolean isOrderableInd) {
		this.isOrderableInd = isOrderableInd;
	}

	public String getTradeItemDescription() {
		return tradeItemDescription;
	}

	public void setTradeItemDescription(String tradeItemDescription) {
		this.tradeItemDescription = tradeItemDescription;
	}

	public Integer getBatteryTypeCode() {
		return batteryTypeCode;
	}

	public void setBatteryTypeCode(Integer batteryTypeCode) {
		this.batteryTypeCode = batteryTypeCode;
	}

	public String getTradeItemWeightUomCode() {
		return tradeItemWeightUomCode;
	}

	public void setTradeItemWeightUomCode(String tradeItemWeightUomCode) {
		this.tradeItemWeightUomCode = tradeItemWeightUomCode;
	}

	public Double getTradeItemWeightQty() {
		return tradeItemWeightQty;
	}

	public void setTradeItemWeightQty(Double tradeItemWeightQty) {
		this.tradeItemWeightQty = tradeItemWeightQty;
	}

	public String getTradeItemDimensionsUomCode() {
		return tradeItemDimensionsUomCode;
	}

	public void setTradeItemDimensionsUomCode(String tradeItemDimensionsUomCode) {
		this.tradeItemDimensionsUomCode = tradeItemDimensionsUomCode;
	}

	public Double getTradeItemDimensionsWidthQty() {
		return tradeItemDimensionsWidthQty;
	}

	public void setTradeItemDimensionsWidthQty(Double tradeItemDimensionsWidthQty) {
		this.tradeItemDimensionsWidthQty = tradeItemDimensionsWidthQty;
	}

	public Double getTradeItemDimensionsDepthQty() {
		return tradeItemDimensionsDepthQty;
	}

	public void setTradeItemDimensionsDepthQty(Double tradeItemDimensionsDepthQty) {
		this.tradeItemDimensionsDepthQty = tradeItemDimensionsDepthQty;
	}

	public Double getTradeItemDimensionsHeightQty() {
		return tradeItemDimensionsHeightQty;
	}

	public void setTradeItemDimensionsHeightQty(Double tradeItemDimensionsHeightQty) {
		this.tradeItemDimensionsHeightQty = tradeItemDimensionsHeightQty;
	}

	public Boolean getIsConveyableInd() {
		return isConveyableInd;
	}

	public void setIsConveyableInd(Boolean isConveyableInd) {
		this.isConveyableInd = isConveyableInd;
	}

	public Integer getTotalQuantityOfNextLowerLevelTradeItem() {
		return totalQuantityOfNextLowerLevelTradeItem;
	}

	public void setTotalQuantityOfNextLowerLevelTradeItem(Integer totalQuantityOfNextLowerLevelTradeItem) {
		this.totalQuantityOfNextLowerLevelTradeItem = totalQuantityOfNextLowerLevelTradeItem;
	}

	public Integer getPalletTiQty() {
		return palletTiQty;
	}

	public void setPalletTiQty(Integer palletTiQty) {
		this.palletTiQty = palletTiQty;
	}

	public Integer getPalletHiQty() {
		return palletHiQty;
	}

	public void setPalletHiQty(Integer palletHiQty) {
		this.palletHiQty = palletHiQty;
	}

	public List<GlobalTradeItemId> getParent() {
		return parent;
	}

	public void setParent(List<GlobalTradeItemId> parent) {
		this.parent = parent;
	}

	public List<GlobalTradeItemId> getChildren() {
		return children;
	}

	public void setChildren(List<GlobalTradeItemId> children) {
		this.children = children;
	}

}
