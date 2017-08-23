package com.ust.item.mdm.dto;

import java.util.Map;

public class ItemSummary<T> {
	
	public T item;
	public Map<String, String> conceptCount;
	
	public T getItem() {
		return item;
	}
	public void setItem(T item) {
		this.item = item;
	}
	public Map<String, String> getConceptCount() {
		return conceptCount;
	}
	public void setConceptCount(Map<String, String> conceptCount) {
		this.conceptCount = conceptCount;
	}
	
	

}
