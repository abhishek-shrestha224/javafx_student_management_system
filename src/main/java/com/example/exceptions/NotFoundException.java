package com.example.exceptions;

public class NotFoundException extends Exception {
  public NotFoundException() {
    super("403! You don't have permission to do that.");
  }

  public NotFoundException(String message) {
    super(message);
  }
}
