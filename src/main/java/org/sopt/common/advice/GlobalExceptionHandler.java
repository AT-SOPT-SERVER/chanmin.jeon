package org.sopt.common.advice;

import org.sopt.common.code.ErrorCode;
import org.sopt.common.response.ApiResponse;
import org.sopt.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e) {
    ErrorCode errorCode = e.getErrorCode();
    return ResponseEntity
        .status(errorCode.getStatus())
        .body(ApiResponse.error(errorCode.getCode(), errorCode.getMessage(), null));
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ApiResponse<Void>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
    return ResponseEntity
        .status(ErrorCode.METHOD_NOT_ALLOWED.getStatus())
        .body(ApiResponse.error(ErrorCode.METHOD_NOT_ALLOWED.getCode(), ErrorCode.METHOD_NOT_ALLOWED.getMessage(), null));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
    return ResponseEntity
        .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
        .body(ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage(), null));
  }
}

