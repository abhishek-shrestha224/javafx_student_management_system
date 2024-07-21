package com.example.models;

public class Complaint {
  private final String id, title, body, submittedBy;
  private boolean resolved;

  public Complaint(String id, String title, String body, String submittedBy) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.submittedBy = submittedBy;
    resolved = false;
  }

  // Getters and Setters

  public String getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getBody() {
    return this.body;
  }

  public String getSubmittedBy() {
    return this.submittedBy;
  }

  public boolean getResolved() {
    return this.resolved;
  }

  public void setResolved(boolean resolved) {
    this.resolved = resolved;
  }
}
