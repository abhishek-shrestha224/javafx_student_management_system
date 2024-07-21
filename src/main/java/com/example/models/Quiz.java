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
  private Map<Integer, Integer> studentScores; // Key: studentId, Value: score
  private boolean graded;

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
    this.studentScores = new HashMap<>();
    this.graded = false;
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

  public Map<Integer, Integer> getStudentScores() {
    return studentScores;
  }

  public boolean isGraded() {
    return graded;
  }

  public void setGraded(boolean graded) {
    this.graded = graded;
  }

  public void submitAnswers(int studentId, int[] mcqAnswers, String openEndedAnswer) {
    if (mcqAnswers.length != 2) {
      throw new IllegalArgumentException("MCQ answers must contain exactly 2 responses.");
    }

    if (studentSubmissions.containsKey(studentId)) {
      throw new IllegalArgumentException("This student has already submitted answers for this quiz.");
    }

    StudentSubmission submission = new StudentSubmission(mcqAnswers, openEndedAnswer);
    studentSubmissions.put(studentId, submission);
  }

  public void gradeQuiz(Map<Integer, Integer> grades) {
    for (Map.Entry<Integer, Integer> entry : grades.entrySet()) {
      int studentId = entry.getKey();
      int grade = entry.getValue();
      studentScores.put(studentId, grade);
    }
    graded = true;
  }

  public Integer getStudentScore(int studentId) {
    return studentScores.get(studentId);
  }

  // Inner class to handle student submissions
  public static class StudentSubmission {

    // Student Submitted Answers
    private final int[] mcqAnswers;

    private final String openEndedAnswer;

    public StudentSubmission(int[] mcqAnswers, String openEndedAnswer) {
      this.mcqAnswers = mcqAnswers;

      this.openEndedAnswer = openEndedAnswer;
    }

    public int[] getMcqAnswers() {
      return mcqAnswers;
    }

    public String getOpenEndedAnswer() {
      return openEndedAnswer;
    }
  }
}
