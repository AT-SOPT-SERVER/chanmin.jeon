package org.sopt.validator;

public class PostValidator {

  public static void validateTitle(final String title) {
    if (title == null || title.isEmpty()) {
      throw new IllegalArgumentException("제목은 비어있을 수 없습니다.");
    }
  }
}
