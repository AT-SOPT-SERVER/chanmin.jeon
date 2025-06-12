package org.sopt.domain.comment.application.usecase;

import java.util.List;

import org.sopt.domain.comment.domain.repository.CommentRepository;
import org.sopt.domain.comment.presentation.dto.CommentResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentQueryUsecase {

	private final CommentRepository commentRepository;

	@Transactional(readOnly = true)
	public List<CommentResponse> getCommentsByPostId(Long postId) {
		return commentRepository.findByPostId(postId).stream()
			.map(CommentResponse::from)
			.toList();
	}
}
