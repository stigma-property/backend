package com.lastline.backend.domain.property.service;

import java.util.List;

import com.lastline.backend.domain.property.domain.Property;
import com.lastline.backend.domain.property.dto.PropertyCreateRequest;
import com.lastline.backend.domain.property.dto.PropertyFilter;
import com.lastline.backend.domain.property.dto.PropertyUpdateRequest;
import com.lastline.backend.domain.property.repository.PropertyRepository;
import com.lastline.backend.domain.user.domain.User;
import com.lastline.backend.domain.user.repository.UserRepository;
import com.lastline.backend.global.exception.CustomException;
import com.lastline.backend.global.exception.ErrorCode;

public class PropertyServiceImpl implements PropertyService {
	private final PropertyRepository propertyRepository;
	private final PropertyValidator validator;

	public PropertyServiceImpl(PropertyRepository propertyRepository, UserRepository userRepository,
		PropertyValidator validator) {
		this.propertyRepository = propertyRepository;
		this.validator = validator;
	}

	@Override
	public List<Property> findPropertiesByFilter(PropertyFilter filters) {
		// 검색 로직은 repository에 완전히 위임
		return propertyRepository.findByFilter(filters);
	}

	@Override
	public Property findPropertyById(Long propertyId) {
		return propertyRepository.findById(propertyId)
			.orElseThrow(() -> new CustomException(ErrorCode.PROPERTY_NOT_FOUND));
	}

	@Override
	public List<Property> findPropertiesByUserId(Long userId) {
		return propertyRepository.findByOwnerId(userId);
	}

	@Override
	public Property createProperty(User lessor, PropertyCreateRequest request) {
		// 1. 사용자 역할 검증
		validator.validateLessor(lessor);
		// 2. 입력값 검증
		validator.validateCreateRequest(request);
		// 3. Property 객체 생성
		Property property = new Property(
			null, // property ID는 레포지토리에서 생성
			lessor.getId(),
			request.getLocation(),
			request.getPrice(),
			request.getPropertyType(),
			request.getDealType()
		);
		// 4. 저장 및 반환
		return propertyRepository.save(property);
	}

	@Override
	public Property updateProperty(User lessor, Long propertyId, PropertyUpdateRequest request) {
		Property property = propertyRepository.findById(propertyId)
			.orElseThrow(() -> new CustomException(ErrorCode.PROPERTY_NOT_FOUND));
		// 1. 사용자 역할 검증
		validator.validateLessor(lessor);
		validator.validateOwner(property, lessor);
		// 2. 매물 상태 확인 (계약 중이거나 완료된 매물은 수정 불가)
		validator.validatePropertyStatus(property);
		// 3. 매물 상태 변경
		property.setStatus(request.getStatus());
		// 4. 저장 및 반환
		return propertyRepository.save(property);
	}

	@Override
	public boolean deleteProperty(User lessor, Long propertyId) {
		Property property = propertyRepository.findById(propertyId)
			.orElseThrow(() -> new CustomException(ErrorCode.PROPERTY_NOT_FOUND));
		// 1. 사용자 역할 검증
		validator.validateLessor(lessor);
		validator.validateOwner(property, lessor);
		// 2. 매물 상태 확인 (계약 중이거나 완료된 매물은 삭제 불가)
		validator.validatePropertyStatus(property);
		// 3. 매물 삭제
		propertyRepository.deleteById(propertyId);
		return true;
	}
}
