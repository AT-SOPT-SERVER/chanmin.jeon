package org.sopt.domain.post.domain.entity;

import java.util.Arrays;
import java.util.Optional;

import org.sopt.domain.post.exception.PostErrorCode;
import org.sopt.global.exception.CustomException;

public enum Tag {
  BACKEND, DATABASE, INFRA;


  public static Tag from(String value) {
    return Arrays.stream(values())
        .filter(tag -> tag.name().equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(() -> new CustomException(PostErrorCode.INVALID_TAG));
  }


}
