package com.lastline.backend.domain.user.service;

import org.springframework.stereotype.Service;

import com.lastline.backend.domain.user.domain.User;
import com.lastline.backend.domain.user.domain.UserRepository;
import com.lastline.backend.global.exception.CustomException;
import com.lastline.backend.global.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
	}
}
