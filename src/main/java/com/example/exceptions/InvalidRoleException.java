package com.example.exceptions;

public class InvalidRoleException extends Exception {
  public InvalidRoleException() {
    super("403! You don't have permission to do that.");
  }

  public InvalidRoleException(String message) {
    super(message);
  }
}
