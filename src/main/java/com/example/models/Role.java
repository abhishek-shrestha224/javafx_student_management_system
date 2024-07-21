package com.example.models;

public enum Role {
  ADMIN("SUP"),
  STUDENT("STD"),
  TEACHER("TCH");

  private final String displayName;

  Role(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
