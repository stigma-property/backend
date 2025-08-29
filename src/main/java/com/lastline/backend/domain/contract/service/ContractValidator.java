package com.lastline.backend.domain.contract.service;

import com.lastline.backend.domain.contract.domain.ContractRequest;
import com.lastline.backend.domain.property.domain.Property;
import com.lastline.backend.domain.user.domain.User;
import com.lastline.backend.global.enums.PropertyStatus;
import com.lastline.backend.global.enums.RequestStatus;
import com.lastline.backend.global.exception.CustomException;
import com.lastline.backend.global.exception.ErrorCode;

public class ContractValidator {
	// 사용자가 임차인 권한을 가졌는지 검증
	public void validateUser(User user) {
		if (!user.isLessee())
			throw new CustomException(ErrorCode.NO_AUTHORITY, "임차인만 계약 요청을 할 수 있습니다.");
	}

	// 승인/거절이 가능한 요청 상태인지 검증
	public void validateRequestStatus(ContractRequest request) {
		if (!request.getStatus().equals(RequestStatus.REQUESTED))
			throw new CustomException(ErrorCode.INVALID_REQUEST_STATUS);
	}

	// 계약이 가능한 매물인지 검증
	public void validatePropertyStatus(Property property) {
		if (!property.getStatus().equals(PropertyStatus.AVAILABLE))
			throw new CustomException(ErrorCode.INVALID_PROPERTY_STATUS);
	}

	// 계약 요청을 받은 사람이 해당 매물의 임대인인지 검증
	public void validateOwner(Property property, User lessor) {
		if (!property.getOwnerId().equals(lessor.getId()))
			throw new CustomException(ErrorCode.NOT_OWNER, "자신의 매물에 대한 요청만 승인할 수 있습니다.");
	}
}
