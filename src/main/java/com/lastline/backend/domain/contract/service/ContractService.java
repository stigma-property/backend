package com.lastline.backend.domain.contract.service;

import java.util.List;

import com.lastline.backend.domain.contract.domain.ContractRequest;
import com.lastline.backend.domain.user.domain.User;

public interface ContractService {
	// 임차인이 한 요청 목록 조회
	List<ContractRequest> findContractRequestsByUserId(Long userId);

	// 특정 매물 소유자의 매물에 대한 모든 요청 목록 조회
	List<ContractRequest> findContractRequestsByPropertyOwnerId(Long ownerId);

	/**
	 * 계약 요청을 생성합니다.
	 * @param lessee 요청자 (임차인)
	 * @param propertyId 요청할 매물 ID
	 * @return 생성된 계약 요청
	 */
	ContractRequest createRequest(User lessee, Long propertyId);

	/**
	 * 계약 요청을 승인합니다.
	 * @param lessor 승인자 (임대인)
	 * @param requestId 승인할 요청 ID
	 * @return 상태가 '승인'으로 업데이트된 요청
	 */
	ContractRequest approveRequest(User lessor, Long requestId);

	/**
	 * 계약 요청을 거절합니다.
	 * @param lessor 거절자 (임대인)
	 * @param requestId 거절할 요청 ID
	 * @return 상태가 '거절'로 업데이트된 요청
	 */
	ContractRequest rejectRequest(User lessor, Long requestId);
}
