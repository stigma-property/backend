package com.lastline.backend.domain.contract.domain;

import com.lastline.backend.global.enums.ContractStatus;

public class Contract {
	private final Long id;
	private final Long requestId;
	private final String contractDate;
	private final String moveInDate;
	private ContractStatus status;

	public Contract(Long id, Long requestId, String contractDate, String moveInDate) {
		this.id = id;
		this.requestId = requestId;
		this.contractDate = contractDate;
		this.moveInDate = moveInDate;
		this.status = ContractStatus.PENDING;  // 계약 진행 중 상태로 시작
	}

	// Getter 메서드들
	public Long getId() {
		return id;
	}

	public String getContractDate() {
		return contractDate;
	}

	public String getMoveInDate() {
		return moveInDate;
	}

	public ContractStatus getStatus() {
		return status;
	}

	// Setter 메서드들
	public void setStatus(ContractStatus status) {
		this.status = status;
	}

	// complete() 메서드
	public void complete() {
		if (this.status == ContractStatus.COMPLETED) {
			throw new IllegalStateException("이미 완료된 계약입니다.");
		}

		if (this.status == ContractStatus.CANCELLED) {
			throw new IllegalStateException("취소된 계약은 완료할 수 없습니다.");
		}

		this.status = ContractStatus.COMPLETED;

		System.out.println("계약이 완료되었습니다: " + this.id);
	}

	// 계약 취소 메서드
	public void cancel() {
		if (this.status == ContractStatus.COMPLETED) {
			throw new IllegalStateException("완료된 계약은 취소할 수 없습니다.");
		}

		this.status = ContractStatus.CANCELLED;

		System.out.println("계약이 취소되었습니다: " + this.id);
	}

	@Override
	public String toString() {
		return "Contract{" +
			"id='" + id + '\'' +
			", contractDate='" + contractDate + '\'' +
			", moveInDate='" + moveInDate + '\'' +
			", status=" + status +
			'}';
	}
}
