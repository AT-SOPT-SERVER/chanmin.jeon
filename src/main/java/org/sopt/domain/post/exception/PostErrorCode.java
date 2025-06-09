package org.sopt.domain.post.exception;

import org.sopt.global.common.exception.code.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum PostErrorCode implements BaseErrorCode {

  // 400
  TITLE_REQUIRED(HttpStatus.BAD_REQUEST, 40020, "제목은 필수입니다."),
  TITLE_TOO_LONG(HttpStatus.BAD_REQUEST, 40021, "제목은 30자 이내여야 합니다."),
  CONTENT_REQUIRED(HttpStatus.BAD_REQUEST, 40022, "내용은 필수입니다."),
  CONTENT_TOO_LONG(HttpStatus.BAD_REQUEST, 40023, "내용은 1000자 이내여야 합니다."),
  INVALID_TAG(HttpStatus.BAD_REQUEST, 40024, "유효하지 않은 태그입니다."),
  INVALID_POST_ID_FORMAT(HttpStatus.BAD_REQUEST, 40025, "게시글 ID는 숫자여야 합니다."),
  EMPTY_SEARCH_CONDITION(HttpStatus.BAD_REQUEST, 40026, "검색 조건을 최소 하나는 입력해야 합니다."),

  // 403
  NO_PERMISSION(HttpStatus.FORBIDDEN, 40320, "해당 게시글에 대한 수정 권한이 없습니다."),

  // 404
  POST_NOT_FOUND(HttpStatus.NOT_FOUND, 40420, "해당 게시글을 찾을 수 없습니다."),

  // 409
  DUPLICATE_TITLE(HttpStatus.CONFLICT, 40920, "동일한 제목의 게시글이 이미 존재합니다."),
  POST_INTERVAL_LIMIT(HttpStatus.CONFLICT, 40921, "마지막 게시글 작성 이후 3분이 지나지 않았습니다.");


  private final HttpStatus status;
  private final int code;
  private final String message;

  PostErrorCode(HttpStatus status, int code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  @Override
  public HttpStatus getStatus() {
    return status;
  }

  @Override
  public int getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
