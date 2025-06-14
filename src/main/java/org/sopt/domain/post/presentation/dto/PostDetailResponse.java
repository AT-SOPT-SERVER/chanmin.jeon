package org.sopt.domain.post.presentation.dto;

import org.sopt.domain.post.domain.entity.Post;

public record PostDetailResponse (
    Long postId,
    String title,
    String content,
    String author,
    String tag
){

  public static PostDetailResponse from(Post post) {
    return new PostDetailResponse(
        post.getId(),
        post.getTitle(),
        post.getContent(),
        post.getUser().getAuthor(),
        post.getTag().name()
    );
  }
}
