package org.sopt.domain.comment.application.dto;

import org.sopt.domain.comment.presentation.dto.CommentUpdateRequest;

public record CommentUpdateCommand(
	Long commentId,
	Long userId,
	String content
) {
	public static CommentUpdateCommand from(Long commentId, Long userId, CommentUpdateRequest request) {
		return new CommentUpdateCommand(commentId, userId, request.content());
	}
}
