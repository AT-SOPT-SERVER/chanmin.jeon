package org.sopt.validator;

import java.time.Duration;
import java.time.LocalDateTime;
import org.sopt.common.code.ErrorCode;
import org.sopt.domain.Post;
import org.sopt.exception.CustomException;
import org.sopt.repository.PostRepository;

public class PostValidator {

  private static final int MAX_TITLE_LENGTH = 30;
  private static final long POST_INTERVAL_LIMIT_MINUTES = 3;

  public static void validateTitle(String title, PostRepository postRepository) {
    if (title == null || title.isBlank()) {
      throw new CustomException(ErrorCode.INVALID_INPUT_VALUE);
    }
    if (title.length() > MAX_TITLE_LENGTH) {
      throw new CustomException(ErrorCode.INVALID_INPUT_VALUE);
    }
    if (postRepository.existsByTitle(title)) {
      throw new CustomException(ErrorCode.DUPLICATE_TITLE);
    }
  }

  public static void validatePostInterval(PostRepository postRepository) {
    Post lastPost = postRepository.findTopByOrderByCreatedAtDesc();
    if (lastPost != null) {
      Duration duration = Duration.between(lastPost.getCreatedAt(), LocalDateTime.now());
      if (duration.toMinutes() < POST_INTERVAL_LIMIT_MINUTES) {
        throw new CustomException(ErrorCode.POST_INTERVAL_LIMIT);
      }
    }
  }
}
