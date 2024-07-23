package com.example.exceptions;

public class UnauthorizedException extends Exception {
  private final int errorCode = 401;
  private final String errorName = "Unauthorized";
  private final String errorTitle = String.valueOf(errorCode) + "-" + errorName;

  public UnauthorizedException(String errorMessage) {
    super(errorMessage);

  }

  public int getErrorCode() {
    return this.errorCode;
  }

  public String getErrorTitle() {
    return this.errorTitle;
  }
}
