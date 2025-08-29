package com.lastline.backend.domain.property.dto;

import com.lastline.backend.domain.property.domain.Location;
import com.lastline.backend.domain.property.domain.Price;
import com.lastline.backend.global.enums.DealType;
import com.lastline.backend.global.enums.PropertyType;

public class PropertyCreateRequest {

	private final Location location;
	private final Price price;
	private final PropertyType propertyType;
	private final DealType dealType;

	public PropertyCreateRequest(Location location, Price price, PropertyType propertyType, DealType dealType) {
		this.location = location;
		this.price = price;
		this.propertyType = propertyType;
		this.dealType = dealType;
	}

	public Location getLocation() {
		return location;
	}

	public Price getPrice() {
		return price;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public DealType getDealType() {
		return dealType;
	}
}
