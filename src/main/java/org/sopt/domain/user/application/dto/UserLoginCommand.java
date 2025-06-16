package org.sopt.domain.user.application.dto;

import org.sopt.domain.user.presentation.dto.UserLoginRequest;

public record UserLoginCommand(
	String author,
	String password
) {
	public static UserLoginCommand from(UserLoginRequest request) {
		return new UserLoginCommand(
			request.author(),
			request.password()
		);
	}
}
