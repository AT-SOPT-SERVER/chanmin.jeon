package org.sopt.domain.post.dto;

import org.sopt.domain.post.entity.Post;

public record PostSummaryResponse(
    Long postId,
    String title,
    String author,
    String tag
) {

  public static PostSummaryResponse from(Post post) {
    return new PostSummaryResponse(
        post.getId(),
        post.getTitle(),
        post.getUser().getAuthor(),
        post.getTag().name()
    );
  }
}
