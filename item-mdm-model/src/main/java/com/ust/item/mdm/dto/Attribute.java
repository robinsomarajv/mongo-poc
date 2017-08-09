package com.ust.item.mdm.dto;

import java.io.Serializable;
import java.util.List;

public class Attribute implements Serializable {

	private String id;
	private Metadata metadata;
	private List<Object> values = null;
	private Object errors;
	private final static long serialVersionUID = 7079635015507669049L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

	public Object getErrors() {
		return errors;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}

}
