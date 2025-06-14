package org.sopt.domain.post.application.validator;

import org.sopt.domain.post.exception.PostErrorCode;
import org.sopt.global.exception.CustomException;

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
