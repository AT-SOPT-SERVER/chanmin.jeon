package org.sopt.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostValidator {

  private static final int MAX_TITLE_LENGTH = 30;
  private static final Pattern GRAHEME_PATTERN = Pattern.compile("\\X");

  public static void validateTitle(final String title) {
    if (title == null || title.isEmpty()) {
      throw new IllegalArgumentException("제목은 비어있을 수 없습니다.");
    }

    int graphemeLength = countGraphemeLength(title);
    if (graphemeLength > MAX_TITLE_LENGTH) {
      throw new IllegalArgumentException("제목은 30자를 넘을 수 없습니다.");
    }

  }

  private static int countGraphemeLength(final String title) {
    Matcher matcher = GRAHEME_PATTERN.matcher(title);

    int count = 0;

    while (matcher.find()) {
      count++;
    }

    return count;
  }

}
