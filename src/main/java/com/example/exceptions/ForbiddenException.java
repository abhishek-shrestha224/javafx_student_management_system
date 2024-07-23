package com.example.exceptions;

public class ForbiddenException extends Exception {
  private final int errorCode = 403;
  private final String errorName = "Forbidden";
  private final String errorTitle = String.valueOf(errorCode) + "-" + errorName;

  public ForbiddenException(String errorMessage) {
    super(errorMessage);

  }

  public int getErrorCode() {
    return this.errorCode;
  }

  public String getErrorTitle() {
    return this.errorTitle;
  }
}
