package org.sopt.global.common.exception.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
  HttpStatus getStatus();
  int getCode();
  String getMessage();
}
