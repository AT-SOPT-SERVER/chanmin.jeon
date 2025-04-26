package org.sopt.common.code;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
  INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, 40000, "제목은 30자를 넘을 수 없습니다."),
  POST_NOT_FOUND(HttpStatus.NOT_FOUND, 40401, "해당 게시글이 존재하지 않습니다."),
  DUPLICATE_TITLE(HttpStatus.CONFLICT, 40901, "중복되는 제목입니다."),
  POST_INTERVAL_LIMIT(HttpStatus.CONFLICT, 40902, "마지막 게시글 작성 이후 3분이 지나지 않았습니다."),
  METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 40500, "지원하지 않는 HTTP 메서드입니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50000, "서버 내부 오류가 발생했습니다.");

  private final HttpStatus status;
  private final int code;
  private final String message;

  ErrorCode(HttpStatus status, int code, String message) {
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
