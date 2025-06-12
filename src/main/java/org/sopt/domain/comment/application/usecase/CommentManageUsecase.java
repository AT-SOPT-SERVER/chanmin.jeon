package org.sopt.domain.comment.application.usecase;

import org.sopt.domain.comment.application.dto.CommentCreateCommand;
import org.sopt.domain.comment.application.dto.CommentDeleteCommand;
import org.sopt.domain.comment.application.dto.CommentUpdateCommand;
import org.sopt.domain.comment.domain.entity.Comment;
import org.sopt.domain.comment.domain.repository.CommentRepository;
import org.sopt.domain.comment.exception.CommentErrorCode;
import org.sopt.domain.post.entity.Post;
import org.sopt.domain.post.repository.PostRepository;
import org.sopt.domain.user.domain.entity.User;
import org.sopt.domain.user.domain.repository.UserRepository;
import org.sopt.global.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentManageUsecase {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public void createComment(CommentCreateCommand command) {
		Post post = postRepository.findById(command.postId())
			.orElseThrow(() -> new CustomException(CommentErrorCode.POST_NOT_FOUND));

		User user = userRepository.findById(command.userId())
			.orElseThrow(() -> new CustomException(CommentErrorCode.USER_NOT_FOUND));

		Comment comment = Comment.create(post, user, command.content());
		commentRepository.save(comment);
	}

	public void updateComment(CommentUpdateCommand command) {
		Comment comment = commentRepository.findById(command.commentId())
			.orElseThrow(() -> new CustomException(CommentErrorCode.COMMENT_NOT_FOUND));

		if (!comment.isAuthor(command.userId())) {
			throw new CustomException(CommentErrorCode.FORBIDDEN_COMMENT_UPDATE);
		}

		comment.updateContent(command.content());
	}

	public void deleteComment(CommentDeleteCommand command) {
		Comment comment = commentRepository.findById(command.commentId())
			.orElseThrow(() -> new CustomException(CommentErrorCode.COMMENT_NOT_FOUND));

		if (!comment.isAuthor(command.userId())) {
			throw new CustomException(CommentErrorCode.FORBIDDEN_COMMENT_DELETE);
		}

		commentRepository.delete(comment);
	}

}
