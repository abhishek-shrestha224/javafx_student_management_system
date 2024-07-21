package com.example.models;

public class Complaint {
  private int complaintId;
  private String name;
  private String complaintText;

  public Complaint(int complaintId, String name, String complaintText) {
    this.complaintId = complaintId;
    this.name = name;
    this.complaintText = complaintText;
  }

  // Getters and setters
  public int getComplaintId() {
    return complaintId;
  }

  public void setComplaintId(int complaintId) {
    this.complaintId = complaintId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getComplaintText() {
    return complaintText;
  }

  public void setComplaintText(String complaintText) {
    this.complaintText = complaintText;
  }
}
