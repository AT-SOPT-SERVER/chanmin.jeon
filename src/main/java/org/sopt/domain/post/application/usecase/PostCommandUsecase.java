package org.sopt.domain.post.application.usecase;


import org.sopt.domain.post.application.dto.PostCreateCommand;
import org.sopt.domain.post.application.dto.PostUpdateCommand;
import org.sopt.domain.post.domain.entity.Post;
import org.sopt.domain.post.domain.entity.Tag;
import org.sopt.domain.post.domain.repository.PostRepository;
import org.sopt.domain.post.exception.PostErrorCode;
import org.sopt.domain.user.domain.entity.User;
import org.sopt.domain.user.domain.repository.UserRepository;
import org.sopt.domain.user.exception.UserErrorCode;
import org.sopt.global.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCommandUsecase {

	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public void createPost(PostCreateCommand command) {
		User user = userRepository.findById(command.userId())
			.orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

		Post post = Post.create(
			command.title(),
			command.content(),
			user,
			Tag.from(command.tag())
		);

		postRepository.save(post);
	}

	public void updatePost(PostUpdateCommand command) {
		Post post = postRepository.findById(command.postId())
			.orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

		if (!post.getUser().getId().equals(command.userId())) {
			throw new CustomException(PostErrorCode.NO_PERMISSION);
		}

		post.update(
			command.title(),
			command.content(),
			Tag.from(command.tag())
		);
	}

	public void deletePost(Long postId, Long userId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

		if (!post.getUser().getId().equals(userId)) {
			throw new CustomException(PostErrorCode.NO_PERMISSION);
		}

		postRepository.delete(post);
	}
}
