package com.lastline.backend.domain.contractRequest.domain;

import java.time.LocalDateTime;

import com.lastline.backend.global.enums.RequestStatus;

import lombok.Getter;

@Getter
public class ContractRequest {
	private Long id;
	private final Long requesterId;
	private final Long propertyId;
	private RequestStatus status;
	private LocalDateTime submittedAt;

	public ContractRequest(Long id, Long requesterId, Long propertyId) {
		this.id = id;
		this.requesterId = requesterId;
		this.propertyId = propertyId;
		this.status = RequestStatus.REQUESTED; // 생성 시 기본 상태는 <요청 중>
		this.submittedAt = LocalDateTime.now(); // 생성 시 기본 시간은 <현재 시간>
	}

	// Setter 메서드들
	public void setId(Long id) {
		this.id = id;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("요청번호: %d | 요청자 ID: %s | 매물 ID: %s | 요청 상태: [%s] | 요청 시간: %s",
			id, requesterId, propertyId, status, submittedAt);
	}
}
