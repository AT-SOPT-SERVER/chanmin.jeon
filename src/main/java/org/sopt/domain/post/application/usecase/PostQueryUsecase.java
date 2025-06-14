package org.sopt.domain.post.application.usecase;

import java.util.List;

import org.sopt.domain.post.domain.entity.Post;
import org.sopt.domain.post.domain.entity.Tag;
import org.sopt.domain.post.domain.repository.PostRepository;
import org.sopt.domain.post.exception.PostErrorCode;
import org.sopt.domain.post.presentation.dto.PostDetailResponse;
import org.sopt.domain.post.presentation.dto.PostInfoResponse;
import org.sopt.global.exception.CustomException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostQueryUsecase {

	private final PostRepository postRepository;

	public List<PostInfoResponse> getAllPosts() {
		return postRepository.findAllByOrderByCreatedAtDesc().stream()
			.map(PostInfoResponse::from)
			.toList();
	}

	public PostDetailResponse getPostById(Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));
		return PostDetailResponse.from(post);
	}

	public List<PostDetailResponse> getPostsByCondition(String title, String author, Tag tag) {
		return postRepository.searchByCondition(title, author, tag).stream()
			.map(PostDetailResponse::from)
			.toList();

	}



}
