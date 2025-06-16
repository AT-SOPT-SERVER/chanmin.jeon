package org.sopt.domain.comment.presentation.dto;

import java.time.LocalDateTime;

import org.sopt.domain.comment.domain.entity.Comment;

public record CommentResponse(
	Long id,
	String author,
	String content,
	LocalDateTime createdAt
) {
	public static CommentResponse from(Comment comment) {
		return new CommentResponse(
			comment.getId(),
			comment.getUser().getAuthor(),
			comment.getContent(),
			comment.getCreatedAt()
		);
	}
}
