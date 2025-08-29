package com.lastline.backend.domain.property.dto;

import com.lastline.backend.global.enums.PropertyStatus;

public class PropertyUpdateRequest {
	private final PropertyStatus status;

	public PropertyUpdateRequest(PropertyStatus status) {
		this.status = status;
	}

	public PropertyStatus getStatus() {
		return status;
	}
}
