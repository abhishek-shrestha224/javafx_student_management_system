package com.example.models;

import java.util.HashMap;
import java.util.Map;

import com.example.helpers.Utils;

public class Quiz {
  private final int id;

  private final String[] mcqQuestions;
  private final String[][] mcqOptions; // Options for each MCQ question

  private final String openEndedQuestion;
  private Map<Integer, StudentSubmission> studentSubmissions; // Key: studentId, Value: student's answers

  public Quiz(String[] mcqQuestions, String[][] mcqOptions,
      String openEndedQuestion) {
    if (mcqQuestions.length != 2 || mcqOptions.length != 2) {
      throw new IllegalArgumentException("There must be exactly 2 MCQ questions and 2 sets of options.");
    }

    this.id = Utils.generateId();
    this.mcqQuestions = mcqQuestions;
    this.mcqOptions = mcqOptions;

    this.openEndedQuestion = openEndedQuestion;
    this.studentSubmissions = new HashMap<>();

  }

  public int getId() {
    return id;
  }

  public String[] getMcqQuestions() {
    return mcqQuestions;
  }

  public String[][] getMcqOptions() {
    return mcqOptions;
  }

  public String getOpenEndedQuestion() {
    return openEndedQuestion;
  }

  public Map<Integer, StudentSubmission> getStudentSubmissions() {
    return studentSubmissions;
  }

  public void submitAnswers(int studentId, String[] mcqAnswers, String openEndedAnswer) {
    if (mcqAnswers.length != 2) {
      throw new IllegalArgumentException("MCQ answers must contain exactly 2 responses.");
    }

    if (studentSubmissions.containsKey(studentId)) {
      throw new IllegalArgumentException("This student has already submitted answers for this quiz.");
    }

    StudentSubmission submission = new StudentSubmission(mcqAnswers, openEndedAnswer);
    studentSubmissions.put(studentId, submission);
  }

  // Inner class to handle student submissions
  public static class StudentSubmission {

    // Student Submitted Answers
    private final String[] mcqAnswers;

    private final String openEndedAnswer;

    public StudentSubmission(String[] mcqAnswers, String openEndedAnswer) {
      this.mcqAnswers = mcqAnswers;

      this.openEndedAnswer = openEndedAnswer;
    }

    public String[] getMcqAnswers() {
      return mcqAnswers;
    }

    public String getOpenEndedAnswer() {
      return openEndedAnswer;
    }
  }
}
