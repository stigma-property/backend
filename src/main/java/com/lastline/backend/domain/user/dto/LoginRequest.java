package com.lastline.backend.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LoginRequest {

	@NotEmpty(message = "이메일을 입력해주세요.")
	@Email(message = "유효하지 않은 이메일 형식입니다.")
	private String email;
}
