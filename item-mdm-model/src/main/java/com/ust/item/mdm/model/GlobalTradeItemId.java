package com.ust.item.mdm.model;

import java.io.Serializable;

public class GlobalTradeItemId implements Serializable {

	private String tradeItemGtin;
	private String informationProviderId;
	private String informationProviderTypeCode;
	private String recipientGln;
	private String targetMarketCode;
	private final static long serialVersionUID = -5801841217166898L;

	public String getTradeItemGtin() {
		return tradeItemGtin;
	}

	public void setTradeItemGtin(String tradeItemGtin) {
		this.tradeItemGtin = tradeItemGtin;
	}

	public String getInformationProviderId() {
		return informationProviderId;
	}

	public void setInformationProviderId(String informationProviderId) {
		this.informationProviderId = informationProviderId;
	}

	public String getInformationProviderTypeCode() {
		return informationProviderTypeCode;
	}

	public void setInformationProviderTypeCode(String informationProviderTypeCode) {
		this.informationProviderTypeCode = informationProviderTypeCode;
	}

	public String getRecipientGln() {
		return recipientGln;
	}

	public void setRecipientGln(String recipientGln) {
		this.recipientGln = recipientGln;
	}

	public String getTargetMarketCode() {
		return targetMarketCode;
	}

	public void setTargetMarketCode(String targetMarketCode) {
		this.targetMarketCode = targetMarketCode;
	}

}