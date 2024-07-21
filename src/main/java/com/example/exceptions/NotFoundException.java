package com.example.exceptions;

public class NotFoundException extends RuntimeException {

  // Default constructor
  public NotFoundException() {
    super();
  }

  // Constructor with a custom message
  public NotFoundException(String message) {
    super(message);
  }

  // Constructor with a custom message and a cause
  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with a cause
  public NotFoundException(Throwable cause) {
    super(cause);
  }
}
