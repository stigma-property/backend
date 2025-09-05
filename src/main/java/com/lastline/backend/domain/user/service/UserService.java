package com.lastline.backend.domain.user.service;

import com.lastline.backend.domain.user.domain.User;

public interface UserService {

	User getUserById(Long id);

	User getUserByEmail(String email);
}
