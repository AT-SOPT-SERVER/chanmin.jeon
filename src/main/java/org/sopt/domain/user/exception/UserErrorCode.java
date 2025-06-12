package org.sopt.domain.user.exception;

import org.sopt.global.exception.code.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum UserErrorCode implements BaseErrorCode {

  // 400
  NICKNAME_REQUIRED(HttpStatus.BAD_REQUEST, 40010, "닉네임은 필수입니다."),
  NICKNAME_TOO_LONG(HttpStatus.BAD_REQUEST, 40011, "닉네임은 10자 이내여야 합니다."),
  PASSWORD_REQUIRED(HttpStatus.BAD_REQUEST, 40012, "비밀번호는 필수입니다."),
  INVALID_PASSWORD_PATTERN(HttpStatus.BAD_REQUEST, 40013, "비밀번호는 최소 8자 이상, 영문, 숫자, 특수문자를 포함해야 합니다."),
  INVALID_PASSWORD(HttpStatus.BAD_REQUEST, 40014, "비밀번호가 일치하지 않습니다."),

  // 404
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, 40410, "유저를 찾을 수 없습니다."),

  // 409
  DUPLICATE_NICKNAME(HttpStatus.CONFLICT, 40910, "이미 존재하는 닉네임입니다.");

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
