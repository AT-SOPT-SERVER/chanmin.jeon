package org.sopt.domain.post.application.dto;

import org.sopt.domain.post.presentation.dto.PostRequest;

public record PostCreateCommand(
	String title,
	String content,
	Long userId,
	String tag
) {
	public static PostCreateCommand from(PostRequest request, Long userId) {
		return new PostCreateCommand(
			request.title(),
			request.content(),
			userId,
			request.tag()
		);
	}
}
