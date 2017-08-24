package com.ust.item.mdm.dto;

import java.util.List;
import java.util.Map;

public class ItemSummary<T> {
	
	public T item;
	public List<Map> conceptCount;
	
	public T getItem() {
		return item;
	}
	public void setItem(T item) {
		this.item = item;
	}
	public List<Map> getConceptCount() {
		return conceptCount;
	}
	public void setConceptCount(List<Map> conceptCount2) {
		this.conceptCount = conceptCount2;
	}
	
	

}
