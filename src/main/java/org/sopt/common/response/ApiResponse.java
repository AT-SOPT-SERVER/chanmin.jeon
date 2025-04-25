package org.sopt.common.response;

public class ApiResponse<T> {
  private final int statusCode;
  private final String message;
  private final T data;

  public ApiResponse(int statusCode, String message, T data) {
    this.statusCode = statusCode;
    this.message = message;
    this.data = data;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }

  public static <T> ApiResponse<T> success(String message, T data) {
    return new ApiResponse<>(200, message, data);
  }

  public static <T> ApiResponse<T> error(int statusCode, String message, T data) {
    return new ApiResponse<>(statusCode, message, null);
  }


}
