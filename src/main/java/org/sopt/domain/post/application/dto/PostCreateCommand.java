package org.sopt.domain.post.application.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.sopt.domain.post.domain.entity.Tag;
import org.sopt.domain.post.presentation.dto.PostRequest;

public record PostCreateCommand(
	String title,
	String content,
	Long userId,
	List<Tag> tags
) {
	public static PostCreateCommand from(PostRequest request, Long userId) {
		return new PostCreateCommand(
			request.title(),
			request.content(),
			userId,
			request.tags().stream()
				.map(Tag::from)
				.collect(Collectors.toList())
		);
	}

}
