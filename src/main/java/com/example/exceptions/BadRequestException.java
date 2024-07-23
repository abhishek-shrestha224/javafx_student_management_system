package com.example.exceptions;

public class BadRequestException extends Exception {
  private final int errorCode = 400;
  private final String errorName = "Bad Request";
  private final String errorTitle = String.valueOf(errorCode) + "-" + errorName;

  public BadRequestException(String errorMessage) {
    super(errorMessage);

  }

  public int getErrorCode() {
    return this.errorCode;
  }

  public String getErrorTitle() {
    return this.errorTitle;
  }
}
