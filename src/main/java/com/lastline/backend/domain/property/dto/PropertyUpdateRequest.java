package com.lastline.backend.domain.property.dto;

import com.lastline.backend.global.enums.PropertyStatus;

import lombok.Getter;

@Getter
public class PropertyUpdateRequest {
	private final PropertyStatus status;

	public PropertyUpdateRequest(PropertyStatus status) {
		this.status = status;
	}

}
