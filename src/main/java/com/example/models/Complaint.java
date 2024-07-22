package com.example.models;

import com.example.helpers.Utils;

public class Complaint {
  private final int id;
  private final String title, text;

  public Complaint(String title, String text) {
    this.id = Utils.generateId();
    this.title = title;
    this.text = text;
  }

  // Getters and setters
  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }

}
