package org.sopt.domain.post.type;

import java.util.Arrays;
import java.util.Optional;

public enum Tag {
  BACKEND, DATABASE, INFRA;


  public static Optional<Tag> parse(String value) {
    return Arrays.stream(Tag.values())
        .filter(tag -> tag.name().equalsIgnoreCase(value))
        .findFirst();
  }
}
