package org.sopt.domain.post.application.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.sopt.domain.post.domain.entity.Tag;
import org.sopt.domain.post.presentation.dto.PostUpdateRequest;

public record PostUpdateCommand(
	Long postId,
	String title,
	String content,
	Long userId,
	List<Tag> tags
) {
	public static PostUpdateCommand from(Long postId, Long userId, PostUpdateRequest request) {
		return new PostUpdateCommand(
			postId,
			request.title(),
			request.content(),
			userId,
			request.tags().stream()
				.map(Tag::from)
				.collect(Collectors.toList())
		);
	}

}
