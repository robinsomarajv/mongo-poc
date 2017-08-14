package com.ust.item.mdm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Product implements Serializable {

	@Id
	private String _id;
	private String product_id;
	private List<String> consumable_gtins = null;
	private String product_name;
	private String assembled_product_height_unitOfMeasure;
	private Boolean has_warnings;
	private Float assembled_product_height;
	private Float assembled_product_length;
	private Float assembled_product_width;
	private String assembled_product_length_unitOfMeasure;
	private String assembled_product_width_unitOfMeasure;
	private Boolean batteries_included;
	private String product_short_description;
	private String product_long_description;
	private String event_name;
	private String category;
	private String sub_category;
	private Integer brand_code;
	private Boolean multipack_indicator;
	private Boolean chemical_indicator;
	private String battery_type_and_quantity;
	private String country_of_origin_components;
	private Boolean is_temperature_sensitive;
	private Boolean has_expiration;
	private String asset_url_fromUIFor_additional_assets_1;
	private String autographed_by;
	private Boolean has_warranty;
	private Float price_per_unit_quantity;
	private Date product_launch_date;
	private final static long serialVersionUID = -4870314168196637166L;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public List<String> getConsumable_gtins() {
		return consumable_gtins;
	}

	public void setConsumable_gtins(List<String> consumable_gtins) {
		this.consumable_gtins = consumable_gtins;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getAssembled_product_height_unitOfMeasure() {
		return assembled_product_height_unitOfMeasure;
	}

	public void setAssembled_product_height_unitOfMeasure(String assembled_product_height_unitOfMeasure) {
		this.assembled_product_height_unitOfMeasure = assembled_product_height_unitOfMeasure;
	}

	public Boolean getHas_warnings() {
		return has_warnings;
	}

	public void setHas_warnings(Boolean has_warnings) {
		this.has_warnings = has_warnings;
	}

	public Float getAssembled_product_height() {
		return assembled_product_height;
	}

	public void setAssembled_product_height(Float assembled_product_height) {
		this.assembled_product_height = assembled_product_height;
	}

	public Float getAssembled_product_length() {
		return assembled_product_length;
	}

	public void setAssembled_product_length(Float assembled_product_length) {
		this.assembled_product_length = assembled_product_length;
	}

	public Float getAssembled_product_width() {
		return assembled_product_width;
	}

	public void setAssembled_product_width(Float assembled_product_width) {
		this.assembled_product_width = assembled_product_width;
	}

	public String getAssembled_product_length_unitOfMeasure() {
		return assembled_product_length_unitOfMeasure;
	}

	public void setAssembled_product_length_unitOfMeasure(String assembled_product_length_unitOfMeasure) {
		this.assembled_product_length_unitOfMeasure = assembled_product_length_unitOfMeasure;
	}

	public String getAssembled_product_width_unitOfMeasure() {
		return assembled_product_width_unitOfMeasure;
	}

	public void setAssembled_product_width_unitOfMeasure(String assembled_product_width_unitOfMeasure) {
		this.assembled_product_width_unitOfMeasure = assembled_product_width_unitOfMeasure;
	}

	public Boolean getBatteries_included() {
		return batteries_included;
	}

	public void setBatteries_included(Boolean batteries_included) {
		this.batteries_included = batteries_included;
	}

	public String getProduct_short_description() {
		return product_short_description;
	}

	public void setProduct_short_description(String product_short_description) {
		this.product_short_description = product_short_description;
	}

	public String getProduct_long_description() {
		return product_long_description;
	}

	public void setProduct_long_description(String product_long_description) {
		this.product_long_description = product_long_description;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSub_category() {
		return sub_category;
	}

	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}

	public Integer getBrand_code() {
		return brand_code;
	}

	public void setBrand_code(Integer brand_code) {
		this.brand_code = brand_code;
	}

	public Boolean getMultipack_indicator() {
		return multipack_indicator;
	}

	public void setMultipack_indicator(Boolean multipack_indicator) {
		this.multipack_indicator = multipack_indicator;
	}

	public Boolean getChemical_indicator() {
		return chemical_indicator;
	}

	public void setChemical_indicator(Boolean chemical_indicator) {
		this.chemical_indicator = chemical_indicator;
	}

	public String getBattery_type_and_quantity() {
		return battery_type_and_quantity;
	}

	public void setBattery_type_and_quantity(String battery_type_and_quantity) {
		this.battery_type_and_quantity = battery_type_and_quantity;
	}

	public String getCountry_of_origin_components() {
		return country_of_origin_components;
	}

	public void setCountry_of_origin_components(String country_of_origin_components) {
		this.country_of_origin_components = country_of_origin_components;
	}

	public Boolean getIs_temperature_sensitive() {
		return is_temperature_sensitive;
	}

	public void setIs_temperature_sensitive(Boolean is_temperature_sensitive) {
		this.is_temperature_sensitive = is_temperature_sensitive;
	}

	public Boolean getHas_expiration() {
		return has_expiration;
	}

	public void setHas_expiration(Boolean has_expiration) {
		this.has_expiration = has_expiration;
	}

	public String getAsset_url_fromUIFor_additional_assets_1() {
		return asset_url_fromUIFor_additional_assets_1;
	}

	public void setAsset_url_fromUIFor_additional_assets_1(String asset_url_fromUIFor_additional_assets_1) {
		this.asset_url_fromUIFor_additional_assets_1 = asset_url_fromUIFor_additional_assets_1;
	}

	public String getAutographed_by() {
		return autographed_by;
	}

	public void setAutographed_by(String autographed_by) {
		this.autographed_by = autographed_by;
	}

	public Boolean getHas_warranty() {
		return has_warranty;
	}

	public void setHas_warranty(Boolean has_warranty) {
		this.has_warranty = has_warranty;
	}

	public Float getPrice_per_unit_quantity() {
		return price_per_unit_quantity;
	}

	public void setPrice_per_unit_quantity(Float price_per_unit_quantity) {
		this.price_per_unit_quantity = price_per_unit_quantity;
	}

	public Date getProduct_launch_date() {
		return product_launch_date;
	}

	public void setProduct_launch_date(Date product_launch_date) {
		this.product_launch_date = product_launch_date;
	}

}
