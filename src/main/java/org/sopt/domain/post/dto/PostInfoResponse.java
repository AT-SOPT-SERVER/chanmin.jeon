package org.sopt.domain.post.dto;

import org.sopt.domain.post.entity.Post;

public record PostInfoResponse(
    Long postId,
    String title,
    String author,
    String tag
) {

  public static PostInfoResponse from(Post post) {
    return new PostInfoResponse(
        post.getId(),
        post.getTitle(),
        post.getUser().getAuthor(),
        post.getTag().name()
    );
  }
}
