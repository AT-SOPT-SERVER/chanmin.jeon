package org.sopt.domain.post.application.usecase;

import org.sopt.domain.post.domain.entity.Post;
import org.sopt.domain.post.domain.entity.PostLike;
import org.sopt.domain.post.domain.repository.PostLikeRepository;
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
public class PostLikeCommandUsecase {

	private final PostRepository postRepository;
	private final PostLikeRepository postLikeRepository;
	private final UserRepository userRepository;

	public void likePost(Long postId, Long userId) {
		if (postLikeRepository.existsByUserIdAndPostId(userId, postId)) {
			throw new CustomException(PostErrorCode.ALREADY_LIKED);
		}

		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

		PostLike postLike = PostLike.create(user, post);
		postLikeRepository.save(postLike);
	}

	public void unlikePost(Long postId, Long userId) {
		PostLike like = postLikeRepository.findByUserIdAndPostId(userId, postId)
			.orElseThrow(() -> new CustomException(PostErrorCode.LIKE_NOT_FOUND));

		postLikeRepository.delete(like);
	}

}
