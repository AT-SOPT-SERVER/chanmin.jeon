package org.sopt.domain.comment.application.dto;

public record CommentDeleteCommand(
	Long commentId,
	Long userId
) {
	public static CommentDeleteCommand from(Long commentId, Long userId) {
		return new CommentDeleteCommand(commentId, userId);
	}
}
