package org.sopt.domain.user.application.dto;

import org.sopt.domain.user.presentation.dto.UserCreateRequest;

public record UserCreateCommand(
	String author,
	String password
) {
	public static UserCreateCommand from(UserCreateRequest request) {
		return new UserCreateCommand(
			request.author(),
			request.password()
		);
	}
}
