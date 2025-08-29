package com.lastline.backend.domain.user.service;

import java.util.Optional;

import com.lastline.backend.domain.user.domain.User;
import com.lastline.backend.domain.user.repository.UserRepository;

public class AuthServiceImpl implements AuthService {
	private final UserRepository repository;
	private final AuthValidator authValidator;

	public AuthServiceImpl(UserRepository repository, AuthValidator authValidator) {
		this.repository = repository;
		this.authValidator = authValidator;
	}

	@Override
	public Optional<User> login(String email) {
		authValidator.validateEmail(email);
		return repository.findByEmail(email);
	}
}
