package com.example.models;

public class User {
  private final String email, userId;
  private String firstName, lastName, password;
  private Gender gender;
  private final Role role;

  public User(String firstName, String lastName, String email, String userId, String password, Gender gender,
      Role role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.userId = userId;
    this.password = password;
    this.gender = gender;
    this.role = role;
  }

  // Getters and setters
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getUserId() {
    return userId;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }
}
