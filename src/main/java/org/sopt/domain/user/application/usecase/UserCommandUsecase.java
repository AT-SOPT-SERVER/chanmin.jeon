package org.sopt.domain.user.application.usecase;

import org.sopt.domain.user.application.dto.UserCreateCommand;
import org.sopt.domain.user.application.dto.UserLoginCommand;
import org.sopt.domain.user.domain.entity.User;
import org.sopt.domain.user.domain.repository.UserRepository;
import org.sopt.domain.user.exception.UserErrorCode;
import org.sopt.global.exception.CustomException;
import org.sopt.global.security.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandUsecase {
	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	public void createUser(UserCreateCommand command) {

		if (userRepository.existsByAuthor(command.author())) {
			throw new CustomException(UserErrorCode.DUPLICATE_NICKNAME);
		}

		String encodedPassword = passwordEncoder.encode(command.password());

		User user = User.create(command.author(), encodedPassword);
		userRepository.save(user);
	}

	public String login(UserLoginCommand command) {
		User user = userRepository.findByAuthor(command.author())
			.orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

		if (!passwordEncoder.matches(command.password(), user.getPassword())) {
			throw new CustomException(UserErrorCode.INVALID_PASSWORD);
		}

		return jwtTokenProvider.createToken(user.getId());
	}
}
