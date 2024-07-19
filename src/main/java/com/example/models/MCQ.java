package com.example.models;

public class MCQ {

  private String question;
  private String options[];

  public MCQ() {
  }

  public String getQuestion() {
    return this.question;
  }

  public void setQuestion(String question) {
    this.question = question;

  }

  public String[] getOptions() {
    return this.options;
  }

  public void setOptions(String[] options) {
    this.options = options;

  }

}
