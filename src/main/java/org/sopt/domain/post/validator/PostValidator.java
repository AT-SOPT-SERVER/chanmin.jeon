package org.sopt.domain.post.validator;

import java.time.Duration;
import java.time.LocalDateTime;
import org.sopt.domain.post.dto.PostRequest;
import org.sopt.domain.post.entity.Post;
import org.sopt.domain.post.exception.PostErrorCode;
import org.sopt.domain.post.repository.PostRepository;
import org.sopt.exception.CustomException;

public class PostValidator {

  private static final int MAX_TITLE_LENGTH = 30;
  private static final int MAX_CONTENT_LENGTH = 1000;

  private PostValidator() {}

  public static void validateTitle(String title) {
    if (title == null || title.isBlank()) {
      throw new CustomException(PostErrorCode.TITLE_REQUIRED);
    }
    if (title.length() > MAX_TITLE_LENGTH) {
      throw new CustomException(PostErrorCode.TITLE_TOO_LONG);
    }
  }

  public static void validateContent(String content) {
    if (content == null || content.isBlank()) {
      throw new CustomException(PostErrorCode.CONTENT_REQUIRED);
    }
    if (content.length() > MAX_CONTENT_LENGTH) {
      throw new CustomException(PostErrorCode.CONTENT_TOO_LONG);
    }

  }
}
