package org.sopt.global.security;

import org.sopt.domain.user.domain.repository.UserRepository;
import org.sopt.domain.user.exception.UserErrorCode;
import org.sopt.global.exception.CustomException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userIdStr) throws UsernameNotFoundException {
		Long userId = Long.parseLong(userIdStr);

		return userRepository.findById(userId)
			.map(user -> new CustomUserDetails(user.getId()))
			.orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
	}

}
