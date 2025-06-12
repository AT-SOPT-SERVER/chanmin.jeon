package org.sopt.global.exception;

import org.sopt.global.exception.code.BaseErrorCode;
import org.sopt.global.exception.code.GlobalErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException e) {
    return createErrorResponse(e.getErrorCode());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    BaseErrorCode errorCode = GlobalErrorCode.INVALID_REQUEST;

    return ResponseEntity
        .status(errorCode.getStatus())
        .body(ApiResponse.error(errorCode.getCode(), errorMessage, null));
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ApiResponse<Void>> handleMethodNotAllowed(
      HttpRequestMethodNotSupportedException e) {
    return createErrorResponse(GlobalErrorCode.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiResponse<Void>> handleInvalidJson(HttpMessageNotReadableException e) {
    return createErrorResponse(GlobalErrorCode.INVALID_JSON);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleNotFound(NoHandlerFoundException e) {
    return createErrorResponse(GlobalErrorCode.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleUnexpectedException(Exception e) {
    e.printStackTrace();
    return createErrorResponse(GlobalErrorCode.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<ApiResponse<Void>> createErrorResponse(BaseErrorCode errorCode) {
    return ResponseEntity
        .status(errorCode.getStatus())
        .body(ApiResponse.error(errorCode.getCode(), errorCode.getMessage(), null));
  }
}

