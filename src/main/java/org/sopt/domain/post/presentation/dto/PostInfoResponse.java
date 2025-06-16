package org.sopt.domain.post.presentation.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.sopt.domain.post.domain.entity.Post;

public record PostInfoResponse(
	Long postId,
	String title,
	String author,
	List<String> tags
) {

	public static PostInfoResponse from(Post post) {
		return new PostInfoResponse(
			post.getId(),
			post.getTitle(),
			post.getUser().getAuthor(),
			post.getTags().stream()
				.map(Enum::name)
				.collect(Collectors.toList())
		);
	}

}
