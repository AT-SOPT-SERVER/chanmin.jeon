package org.sopt.validator;

public class PostValidator {
  private static final int MAX_TITLE_LENGTH = 30;


  public static void validateTitle(final String title) {
    if (title == null || title.isEmpty()) {
      throw new IllegalArgumentException("제목은 비어있을 수 없습니다.");
    }

    if (title.length() > MAX_TITLE_LENGTH) {
      throw new IllegalArgumentException("제목은 30자를 넘을 수 없습니다.");
    }


  }
}
