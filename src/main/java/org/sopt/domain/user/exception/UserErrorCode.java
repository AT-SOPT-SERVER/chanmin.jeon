package org.sopt.domain.user.exception;

import org.sopt.common.code.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum UserErrorCode implements BaseErrorCode {
  NICKNAME_REQUIRED(HttpStatus.BAD_REQUEST, 40001, "닉네임은 필수입니다."),
  NICKNAME_TOO_LONG(HttpStatus.BAD_REQUEST, 40002, "닉네임은 10자 이내여야 합니다."),
  DUPLICATE_NICKNAME(HttpStatus.CONFLICT, 40901, "이미 존재하는 닉네임입니다."),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, 40401, "유저를 찾을 수 없습니다.");

  private final HttpStatus status;
  private final int code;
  private final String message;

  UserErrorCode(HttpStatus status, int code, String message) {
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
