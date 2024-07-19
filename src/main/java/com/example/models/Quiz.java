package com.example.models;

import java.util.HashMap;
import java.util.Map;

public class Quiz {
  private final String quizId;
  private final int quizWeek;
  private final String[] mcqQuestions;
  private final String[][] mcqOptions; // Options for each MCQ question
  private final String[] tfQuestions;
  private final String openEndedQuestion;
  private Map<String, StudentSubmission> studentSubmissions; // Key: studentId, Value: student's answers
  private Map<String, Integer> studentScores; // Key: studentId, Value: score
  private boolean graded;

  public Quiz(String quizId, int quizWeek, String[] mcqQuestions, String[][] mcqOptions, String[] tfQuestions,
      String openEndedQuestion) {
    if (mcqQuestions.length != 2 || mcqOptions.length != 2) {
      throw new IllegalArgumentException("There must be exactly 2 MCQ questions and 2 sets of options.");
    }
    if (tfQuestions.length != 2) {
      throw new IllegalArgumentException("There must be exactly 2 True/False questions.");
    }

    this.quizId = quizId;
    this.quizWeek = quizWeek;
    this.mcqQuestions = mcqQuestions;
    this.mcqOptions = mcqOptions;
    this.tfQuestions = tfQuestions;
    this.openEndedQuestion = openEndedQuestion;
    this.studentSubmissions = new HashMap<>();
    this.studentScores = new HashMap<>();
    this.graded = false;
  }

  public String getQuizId() {
    return quizId;
  }

  public int getQuizWeek() {
    return quizWeek;
  }

  public String[] getMcqQuestions() {
    return mcqQuestions;
  }

  public String[][] getMcqOptions() {
    return mcqOptions;
  }

  public String[] getTfQuestions() {
    return tfQuestions;
  }

  public String getOpenEndedQuestion() {
    return openEndedQuestion;
  }

  public Map<String, StudentSubmission> getStudentSubmissions() {
    return studentSubmissions;
  }

  public Map<String, Integer> getStudentScores() {
    return studentScores;
  }

  public boolean isGraded() {
    return graded;
  }

  public void setGraded(boolean graded) {
    this.graded = graded;
  }

  public void submitAnswers(String studentId, int[] mcqAnswers, boolean[] tfAnswers, String openEndedAnswer) {
    if (mcqAnswers.length != 2) {
      throw new IllegalArgumentException("MCQ answers must contain exactly 2 responses.");
    }
    if (tfAnswers.length != 2) {
      throw new IllegalArgumentException("True/False answers must contain exactly 2 responses.");
    }

    if (studentSubmissions.containsKey(studentId)) {
      throw new IllegalArgumentException("This student has already submitted answers for this quiz.");
    }

    StudentSubmission submission = new StudentSubmission(mcqAnswers, tfAnswers, openEndedAnswer);
    studentSubmissions.put(studentId, submission);
  }

  public void gradeQuiz(Map<String, Integer> grades) {
    for (Map.Entry<String, Integer> entry : grades.entrySet()) {
      String studentId = entry.getKey();
      int grade = entry.getValue();
      studentScores.put(studentId, grade);
    }
    graded = true;
  }

  public Integer getStudentScore(String studentId) {
    return studentScores.get(studentId);
  }

  // Inner class to handle student submissions
  public static class StudentSubmission {

    // Student Submitted Answers
    private final int[] mcqAnswers;
    private final boolean[] tfAnswers;
    private final String openEndedAnswer;

    public StudentSubmission(int[] mcqAnswers, boolean[] tfAnswers, String openEndedAnswer) {
      this.mcqAnswers = mcqAnswers;
      this.tfAnswers = tfAnswers;
      this.openEndedAnswer = openEndedAnswer;
    }

    public int[] getMcqAnswers() {
      return mcqAnswers;
    }

    public boolean[] getTfAnswers() {
      return tfAnswers;
    }

    public String getOpenEndedAnswer() {
      return openEndedAnswer;
    }
  }
}