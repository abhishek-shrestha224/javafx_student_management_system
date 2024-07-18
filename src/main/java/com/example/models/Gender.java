package com.example.models;

public enum Gender {
  MALE("Male"),
  FEMALE("Female"),
  OTHER("Others");

  private final String displayName;

  Gender(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
