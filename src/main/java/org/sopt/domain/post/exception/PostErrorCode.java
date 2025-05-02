package org.sopt.domain.post.exception;

import org.sopt.common.code.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum PostErrorCode implements BaseErrorCode {

  // 제목
  TITLE_REQUIRED(HttpStatus.BAD_REQUEST, 40003, "제목은 필수입니다."),
  TITLE_TOO_LONG(HttpStatus.BAD_REQUEST, 40004, "제목은 30자 이내여야 합니다."),
  DUPLICATE_TITLE(HttpStatus.CONFLICT, 40902, "동일한 제목의 게시글이 이미 존재합니다."),

  // 내용
  CONTENT_REQUIRED(HttpStatus.BAD_REQUEST, 40005, "내용은 필수입니다."),
  CONTENT_TOO_LONG(HttpStatus.BAD_REQUEST, 40006, "내용은 1000자 이내여야 합니다."),

  // 태그
  INVALID_TAG(HttpStatus.BAD_REQUEST, 40012, "유효하지 않은 태그입니다."),

  // 게시글
  POST_NOT_FOUND(HttpStatus.NOT_FOUND, 40402, "해당 게시글을 찾을 수 없습니다."),
  INVALID_POST_ID_FORMAT(HttpStatus.BAD_REQUEST, 40008, "게시글 ID는 숫자여야 합니다."),
  NO_PERMISSION(HttpStatus.FORBIDDEN, 40301, "해당 게시글에 대한 수정 권한이 없습니다."),
  POST_INTERVAL_LIMIT(HttpStatus.CONFLICT, 40903, "마지막 게시글 작성 이후 3분이 지나지 않았습니다."),

  // 검색 조건
  EMPTY_SEARCH_CONDITION(HttpStatus.BAD_REQUEST, 40011, "검색 조건을 최소 하나는 입력해야 합니다.");


  private final HttpStatus status;
  private final int code;
  private final String message;

  PostErrorCode(HttpStatus status, int code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
