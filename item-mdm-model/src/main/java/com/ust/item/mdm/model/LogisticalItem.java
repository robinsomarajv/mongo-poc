package com.ust.item.mdm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class LogisticalItem implements Serializable {
	
	private final static long serialVersionUID = -999590161740494050L;
	
	@Id
	private String _id;
	private String warehouseMinOrderQty;
	private String isCannedOrderInd;
	private String itemNbr;
	private String baseDivisionCode;
	private String warehouseMinLifeRemainingToReceiveQty;
	private String omitTraitNbr;
	private String isCancelWhenOutInd;
	private String supplierLeadTimeQty;
	private String isOfferedForSaleInd;
	private String warehouseAlignmentCode;
	private String seasonCode;
	private String factoryId;
	private String publishedGtin;
	private String seasonYearNbr;
	private String warehousePackGtin;
	private String itemChangeSendWalmartWeekNbr;
	private String supplierLeadTimeUomCode;
	private String hasRfidInd;
	private String shelfLabel3Description;
	private String isImportInd;
	private String orderablePackGtin;
	private String warehouseAreaCode;
	private String supplyItemSecondaryDescription;
	private String reserveMerchandiseTypeCode;
	private String isShelfLabelRequiredInd;
	private String merchandiseProgramId;
	private String hasSecurityTagInd;
	private String supplyItemChangeReasonCode;
	private String allowedTimeInWarehouseUomCode;
	private String warehouseMaxOrderQty;
	private List<String> destinationFormatCode = null;
	private String warehousePackQtyUomCode;
	private String legacyProductNbr;
	private String allowedTimeInWarehouseQty;
	private String isReplenishedByUnitInd;
	private String mbmTypeCode;
	private String supplierId;
	private String isCorporateReplenishableInd;
	private String isEcommerceReplenishableInd;
	private String warehousePackCalcMethodCode;
	private String recipientGln;
	private String warehousePackCostAmt;
	private String deptNbr;
	private String supplyItemPrimaryDescription;
	private String buyingRegionCode;
	private Date supplyItemEffectiveDate;
	private List<String> originCountryCode = null;
	private String supplyItemStatusCode;
	private String informationProviderId;
	private String consumableGtin;
	private String supplierStockId;
	private String baseRetailUomCode;
	private String isVariableWeightInd;
	private String orderablePackCostAmt;
	private String replenishSubTypeCode;
	private String warehousePackQty;
	private String informationProviderTypeCode;
	private String itemTypeCode;
	private String warehouseRotationTypeCode;
	private String orderablePackQty;
	private String targetMarketCode;
	private Date sendStoreDate;
	private String hasRigidPlasticPackagingContainerInd;
	private String unitCostAmt;
	private String baseRetailAmt;
	private String finelineNbr;
	private String isBackroomScaleInd;
	private Date supplyItemExpireDate;

	public String getWarehouseMinOrderQty() {
		return warehouseMinOrderQty;
	}

	public void setWarehouseMinOrderQty(String warehouseMinOrderQty) {
		this.warehouseMinOrderQty = warehouseMinOrderQty;
	}

	public String getIsCannedOrderInd() {
		return isCannedOrderInd;
	}

	public void setIsCannedOrderInd(String isCannedOrderInd) {
		this.isCannedOrderInd = isCannedOrderInd;
	}

	public String getItemNbr() {
		return itemNbr;
	}

	public void setItemNbr(String itemNbr) {
		this.itemNbr = itemNbr;
	}

	public String getBaseDivisionCode() {
		return baseDivisionCode;
	}

	public void setBaseDivisionCode(String baseDivisionCode) {
		this.baseDivisionCode = baseDivisionCode;
	}

	public String getWarehouseMinLifeRemainingToReceiveQty() {
		return warehouseMinLifeRemainingToReceiveQty;
	}

	public void setWarehouseMinLifeRemainingToReceiveQty(String warehouseMinLifeRemainingToReceiveQty) {
		this.warehouseMinLifeRemainingToReceiveQty = warehouseMinLifeRemainingToReceiveQty;
	}

	public String getOmitTraitNbr() {
		return omitTraitNbr;
	}

	public void setOmitTraitNbr(String omitTraitNbr) {
		this.omitTraitNbr = omitTraitNbr;
	}

	public String getIsCancelWhenOutInd() {
		return isCancelWhenOutInd;
	}

	public void setIsCancelWhenOutInd(String isCancelWhenOutInd) {
		this.isCancelWhenOutInd = isCancelWhenOutInd;
	}

	public String getSupplierLeadTimeQty() {
		return supplierLeadTimeQty;
	}

	public void setSupplierLeadTimeQty(String supplierLeadTimeQty) {
		this.supplierLeadTimeQty = supplierLeadTimeQty;
	}

	public String getIsOfferedForSaleInd() {
		return isOfferedForSaleInd;
	}

	public void setIsOfferedForSaleInd(String isOfferedForSaleInd) {
		this.isOfferedForSaleInd = isOfferedForSaleInd;
	}

	public String getWarehouseAlignmentCode() {
		return warehouseAlignmentCode;
	}

	public void setWarehouseAlignmentCode(String warehouseAlignmentCode) {
		this.warehouseAlignmentCode = warehouseAlignmentCode;
	}

	public String getSeasonCode() {
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}

	public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}

	public String getPublishedGtin() {
		return publishedGtin;
	}

	public void setPublishedGtin(String publishedGtin) {
		this.publishedGtin = publishedGtin;
	}

	public String getSeasonYearNbr() {
		return seasonYearNbr;
	}

	public void setSeasonYearNbr(String seasonYearNbr) {
		this.seasonYearNbr = seasonYearNbr;
	}

	public String getWarehousePackGtin() {
		return warehousePackGtin;
	}

	public void setWarehousePackGtin(String warehousePackGtin) {
		this.warehousePackGtin = warehousePackGtin;
	}

	public String getItemChangeSendWalmartWeekNbr() {
		return itemChangeSendWalmartWeekNbr;
	}

	public void setItemChangeSendWalmartWeekNbr(String itemChangeSendWalmartWeekNbr) {
		this.itemChangeSendWalmartWeekNbr = itemChangeSendWalmartWeekNbr;
	}

	public String getSupplierLeadTimeUomCode() {
		return supplierLeadTimeUomCode;
	}

	public void setSupplierLeadTimeUomCode(String supplierLeadTimeUomCode) {
		this.supplierLeadTimeUomCode = supplierLeadTimeUomCode;
	}

	public String getHasRfidInd() {
		return hasRfidInd;
	}

	public void setHasRfidInd(String hasRfidInd) {
		this.hasRfidInd = hasRfidInd;
	}

	public String getShelfLabel3Description() {
		return shelfLabel3Description;
	}

	public void setShelfLabel3Description(String shelfLabel3Description) {
		this.shelfLabel3Description = shelfLabel3Description;
	}

	public String getIsImportInd() {
		return isImportInd;
	}

	public void setIsImportInd(String isImportInd) {
		this.isImportInd = isImportInd;
	}

	public String getOrderablePackGtin() {
		return orderablePackGtin;
	}

	public void setOrderablePackGtin(String orderablePackGtin) {
		this.orderablePackGtin = orderablePackGtin;
	}

	public String getWarehouseAreaCode() {
		return warehouseAreaCode;
	}

	public void setWarehouseAreaCode(String warehouseAreaCode) {
		this.warehouseAreaCode = warehouseAreaCode;
	}

	public String getSupplyItemSecondaryDescription() {
		return supplyItemSecondaryDescription;
	}

	public void setSupplyItemSecondaryDescription(String supplyItemSecondaryDescription) {
		this.supplyItemSecondaryDescription = supplyItemSecondaryDescription;
	}

	public String getReserveMerchandiseTypeCode() {
		return reserveMerchandiseTypeCode;
	}

	public void setReserveMerchandiseTypeCode(String reserveMerchandiseTypeCode) {
		this.reserveMerchandiseTypeCode = reserveMerchandiseTypeCode;
	}

	public String getIsShelfLabelRequiredInd() {
		return isShelfLabelRequiredInd;
	}

	public void setIsShelfLabelRequiredInd(String isShelfLabelRequiredInd) {
		this.isShelfLabelRequiredInd = isShelfLabelRequiredInd;
	}

	public String getMerchandiseProgramId() {
		return merchandiseProgramId;
	}

	public void setMerchandiseProgramId(String merchandiseProgramId) {
		this.merchandiseProgramId = merchandiseProgramId;
	}

	public String getHasSecurityTagInd() {
		return hasSecurityTagInd;
	}

	public void setHasSecurityTagInd(String hasSecurityTagInd) {
		this.hasSecurityTagInd = hasSecurityTagInd;
	}

	public String getSupplyItemChangeReasonCode() {
		return supplyItemChangeReasonCode;
	}

	public void setSupplyItemChangeReasonCode(String supplyItemChangeReasonCode) {
		this.supplyItemChangeReasonCode = supplyItemChangeReasonCode;
	}

	public String getAllowedTimeInWarehouseUomCode() {
		return allowedTimeInWarehouseUomCode;
	}

	public void setAllowedTimeInWarehouseUomCode(String allowedTimeInWarehouseUomCode) {
		this.allowedTimeInWarehouseUomCode = allowedTimeInWarehouseUomCode;
	}

	public String getWarehouseMaxOrderQty() {
		return warehouseMaxOrderQty;
	}

	public void setWarehouseMaxOrderQty(String warehouseMaxOrderQty) {
		this.warehouseMaxOrderQty = warehouseMaxOrderQty;
	}

	public List<String> getDestinationFormatCode() {
		return destinationFormatCode;
	}

	public void setDestinationFormatCode(List<String> destinationFormatCode) {
		this.destinationFormatCode = destinationFormatCode;
	}

	public String getWarehousePackQtyUomCode() {
		return warehousePackQtyUomCode;
	}

	public void setWarehousePackQtyUomCode(String warehousePackQtyUomCode) {
		this.warehousePackQtyUomCode = warehousePackQtyUomCode;
	}

	public String getLegacyProductNbr() {
		return legacyProductNbr;
	}

	public void setLegacyProductNbr(String legacyProductNbr) {
		this.legacyProductNbr = legacyProductNbr;
	}

	public String getAllowedTimeInWarehouseQty() {
		return allowedTimeInWarehouseQty;
	}

	public void setAllowedTimeInWarehouseQty(String allowedTimeInWarehouseQty) {
		this.allowedTimeInWarehouseQty = allowedTimeInWarehouseQty;
	}

	public String getIsReplenishedByUnitInd() {
		return isReplenishedByUnitInd;
	}

	public void setIsReplenishedByUnitInd(String isReplenishedByUnitInd) {
		this.isReplenishedByUnitInd = isReplenishedByUnitInd;
	}

	public String getMbmTypeCode() {
		return mbmTypeCode;
	}

	public void setMbmTypeCode(String mbmTypeCode) {
		this.mbmTypeCode = mbmTypeCode;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getIsCorporateReplenishableInd() {
		return isCorporateReplenishableInd;
	}

	public void setIsCorporateReplenishableInd(String isCorporateReplenishableInd) {
		this.isCorporateReplenishableInd = isCorporateReplenishableInd;
	}

	public String getIsEcommerceReplenishableInd() {
		return isEcommerceReplenishableInd;
	}

	public void setIsEcommerceReplenishableInd(String isEcommerceReplenishableInd) {
		this.isEcommerceReplenishableInd = isEcommerceReplenishableInd;
	}

	public String getWarehousePackCalcMethodCode() {
		return warehousePackCalcMethodCode;
	}

	public void setWarehousePackCalcMethodCode(String warehousePackCalcMethodCode) {
		this.warehousePackCalcMethodCode = warehousePackCalcMethodCode;
	}

	public String getRecipientGln() {
		return recipientGln;
	}

	public void setRecipientGln(String recipientGln) {
		this.recipientGln = recipientGln;
	}

	public String getWarehousePackCostAmt() {
		return warehousePackCostAmt;
	}

	public void setWarehousePackCostAmt(String warehousePackCostAmt) {
		this.warehousePackCostAmt = warehousePackCostAmt;
	}

	public String getDeptNbr() {
		return deptNbr;
	}

	public void setDeptNbr(String deptNbr) {
		this.deptNbr = deptNbr;
	}

	public String getSupplyItemPrimaryDescription() {
		return supplyItemPrimaryDescription;
	}

	public void setSupplyItemPrimaryDescription(String supplyItemPrimaryDescription) {
		this.supplyItemPrimaryDescription = supplyItemPrimaryDescription;
	}

	public String getBuyingRegionCode() {
		return buyingRegionCode;
	}

	public void setBuyingRegionCode(String buyingRegionCode) {
		this.buyingRegionCode = buyingRegionCode;
	}

	public Date getSupplyItemEffectiveDate() {
		return supplyItemEffectiveDate;
	}

	public void setSupplyItemEffectiveDate(Date supplyItemEffectiveDate) {
		this.supplyItemEffectiveDate = supplyItemEffectiveDate;
	}

	public List<String> getOriginCountryCode() {
		return originCountryCode;
	}

	public void setOriginCountryCode(List<String> originCountryCode) {
		this.originCountryCode = originCountryCode;
	}

	public String getSupplyItemStatusCode() {
		return supplyItemStatusCode;
	}

	public void setSupplyItemStatusCode(String supplyItemStatusCode) {
		this.supplyItemStatusCode = supplyItemStatusCode;
	}

	public String getInformationProviderId() {
		return informationProviderId;
	}

	public void setInformationProviderId(String informationProviderId) {
		this.informationProviderId = informationProviderId;
	}

	public String getConsumableGtin() {
		return consumableGtin;
	}

	public void setConsumableGtin(String consumableGtin) {
		this.consumableGtin = consumableGtin;
	}

	public String getSupplierStockId() {
		return supplierStockId;
	}

	public void setSupplierStockId(String supplierStockId) {
		this.supplierStockId = supplierStockId;
	}

	public String getBaseRetailUomCode() {
		return baseRetailUomCode;
	}

	public void setBaseRetailUomCode(String baseRetailUomCode) {
		this.baseRetailUomCode = baseRetailUomCode;
	}

	public String getIsVariableWeightInd() {
		return isVariableWeightInd;
	}

	public void setIsVariableWeightInd(String isVariableWeightInd) {
		this.isVariableWeightInd = isVariableWeightInd;
	}

	public String getOrderablePackCostAmt() {
		return orderablePackCostAmt;
	}

	public void setOrderablePackCostAmt(String orderablePackCostAmt) {
		this.orderablePackCostAmt = orderablePackCostAmt;
	}

	public String getReplenishSubTypeCode() {
		return replenishSubTypeCode;
	}

	public void setReplenishSubTypeCode(String replenishSubTypeCode) {
		this.replenishSubTypeCode = replenishSubTypeCode;
	}

	public String getWarehousePackQty() {
		return warehousePackQty;
	}

	public void setWarehousePackQty(String warehousePackQty) {
		this.warehousePackQty = warehousePackQty;
	}

	public String getInformationProviderTypeCode() {
		return informationProviderTypeCode;
	}

	public void setInformationProviderTypeCode(String informationProviderTypeCode) {
		this.informationProviderTypeCode = informationProviderTypeCode;
	}

	public String getItemTypeCode() {
		return itemTypeCode;
	}

	public void setItemTypeCode(String itemTypeCode) {
		this.itemTypeCode = itemTypeCode;
	}

	public String getWarehouseRotationTypeCode() {
		return warehouseRotationTypeCode;
	}

	public void setWarehouseRotationTypeCode(String warehouseRotationTypeCode) {
		this.warehouseRotationTypeCode = warehouseRotationTypeCode;
	}

	public String getOrderablePackQty() {
		return orderablePackQty;
	}

	public void setOrderablePackQty(String orderablePackQty) {
		this.orderablePackQty = orderablePackQty;
	}

	public String getTargetMarketCode() {
		return targetMarketCode;
	}

	public void setTargetMarketCode(String targetMarketCode) {
		this.targetMarketCode = targetMarketCode;
	}

	public Date getSendStoreDate() {
		return sendStoreDate;
	}

	public void setSendStoreDate(Date sendStoreDate) {
		this.sendStoreDate = sendStoreDate;
	}

	public String getHasRigidPlasticPackagingContainerInd() {
		return hasRigidPlasticPackagingContainerInd;
	}

	public void setHasRigidPlasticPackagingContainerInd(String hasRigidPlasticPackagingContainerInd) {
		this.hasRigidPlasticPackagingContainerInd = hasRigidPlasticPackagingContainerInd;
	}

	public String getUnitCostAmt() {
		return unitCostAmt;
	}

	public void setUnitCostAmt(String unitCostAmt) {
		this.unitCostAmt = unitCostAmt;
	}

	public String getBaseRetailAmt() {
		return baseRetailAmt;
	}

	public void setBaseRetailAmt(String baseRetailAmt) {
		this.baseRetailAmt = baseRetailAmt;
	}

	public String getFinelineNbr() {
		return finelineNbr;
	}

	public void setFinelineNbr(String finelineNbr) {
		this.finelineNbr = finelineNbr;
	}

	public String getIsBackroomScaleInd() {
		return isBackroomScaleInd;
	}

	public void setIsBackroomScaleInd(String isBackroomScaleInd) {
		this.isBackroomScaleInd = isBackroomScaleInd;
	}

	public Date getSupplyItemExpireDate() {
		return supplyItemExpireDate;
	}

	public void setSupplyItemExpireDate(Date supplyItemExpireDate) {
		this.supplyItemExpireDate = supplyItemExpireDate;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}
