package org.sopt.common.code;

import org.springframework.http.HttpStatus;

public enum GlobalErrorCode implements BaseErrorCode{
  METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 40500, "지원하지 않는 HTTP 메서드입니다."),
  NOT_FOUND(HttpStatus.NOT_FOUND, 40400, "요청한 경로가 존재하지 않습니다."),
  INVALID_HEADER(HttpStatus.BAD_REQUEST, 40010, "요청 헤더 값이 올바르지 않습니다."),
  INVALID_JSON(HttpStatus.BAD_REQUEST, 40013, "요청 형식이 올바르지 않습니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50000, "서버 내부 오류가 발생했습니다.");

  private final HttpStatus status;
  private final int code;
  private final String message;

  GlobalErrorCode(HttpStatus status, int code, String message) {
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
