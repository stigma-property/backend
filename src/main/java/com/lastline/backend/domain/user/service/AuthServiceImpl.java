package com.lastline.backend.domain.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lastline.backend.domain.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public Boolean login(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
}
