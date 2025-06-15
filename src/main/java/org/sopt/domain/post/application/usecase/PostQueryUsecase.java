package org.sopt.domain.post.application.usecase;

import java.util.List;

import org.sopt.domain.post.domain.entity.Post;
import org.sopt.domain.post.domain.entity.Tag;
import org.sopt.domain.post.domain.repository.PostLikeRepository;
import org.sopt.domain.post.domain.repository.PostRepository;
import org.sopt.domain.post.exception.PostErrorCode;
import org.sopt.domain.post.presentation.dto.PostDetailResponse;
import org.sopt.domain.post.presentation.dto.PostInfoResponse;
import org.sopt.global.exception.CustomException;
import org.sopt.global.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostQueryUsecase {

	private final PostRepository postRepository;
	private final PostLikeRepository postLikeRepository;

	public PageResponse<PostInfoResponse> getAllPosts(Pageable pageable) {
		Page<Post> posts = postRepository.findAllByOrderByCreatedAtDesc(pageable);
		return PageResponse.from(posts.map(PostInfoResponse::from));
	}

	public PostDetailResponse getPostById(Long postId, Long userId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

		int likeCount = postLikeRepository.countByPostId(postId);
		boolean liked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

		return PostDetailResponse.from(post, liked, likeCount);
	}

	public List<PostDetailResponse> getPostsByCondition(String title, String author, Tag tag, Long userId) {
		return postRepository.searchByCondition(title, author, tag).stream()
			.map(post -> {
				int likeCount = postLikeRepository.countByPostId(post.getId());
				boolean liked = postLikeRepository.existsByUserIdAndPostId(userId, post.getId());
				return PostDetailResponse.from(post, liked, likeCount);
			})
			.toList();

	}



}
