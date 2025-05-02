package org.sopt.domain.post.type;

import java.util.Arrays;
import java.util.Optional;
import org.sopt.domain.post.exception.PostErrorCode;
import org.sopt.exception.CustomException;

public enum Tag {
  BACKEND, DATABASE, INFRA;


  public static Optional<Tag> parse(String value) {
    return Arrays.stream(Tag.values())
        .filter(tag -> tag.name().equalsIgnoreCase(value))
        .findFirst();
  }
}
