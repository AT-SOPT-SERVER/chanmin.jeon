package org.sopt.domain.post.application.dto;

import org.sopt.domain.post.presentation.dto.PostUpdateRequest;

public record PostUpdateCommand(
	Long postId,
	String title,
	String content,
	Long userId,
	String tag
) {
	public static PostUpdateCommand from(Long postId, Long userId, PostUpdateRequest request) {
		return new PostUpdateCommand(
			postId,
			request.title(),
			request.content(),
			userId,
			request.tag()
		);
	}
}
