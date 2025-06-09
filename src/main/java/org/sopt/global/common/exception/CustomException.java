package org.sopt.global.common.exception;

import org.sopt.global.common.exception.code.BaseErrorCode;

public class CustomException extends RuntimeException {

  private final BaseErrorCode errorCode;

  public CustomException(BaseErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public BaseErrorCode getErrorCode() {
    return errorCode;
  }
}
