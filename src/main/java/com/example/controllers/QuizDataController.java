package com.example.controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.helpers.PATH;
import com.example.models.Quiz;
import com.example.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class QuizDataController {
  private final Map<String, Quiz> quizzes; // Key: quizId, Value: Quiz object
  private final Map<String, User> users; // Key: userId, Value: User object

  public QuizDataController() {
    this.quizzes = new HashMap<>();
    this.users = new HashMap<>();
    loadQuizzes(); // Load quizzes from file when controller is initialized
  }

  // Teacher creates a weekly quiz
  public void createQuiz(Quiz quiz, String teacherId) {
    User user = users.get(teacherId);
    if (user == null) {
      throw new IllegalArgumentException("Null User Error");
    }

    quizzes.put(quiz.getQuizId(), quiz);
    saveQuizzes();
  }

  // Student views a quiz
  public Quiz viewQuiz(String quizId, String studentId) {
    User user = users.get(studentId);
    if (user == null) {
      throw new IllegalArgumentException("Only students can view quizzes.");
    }
    return quizzes.get(quizId);
  }

  // Student submits answers for a quiz
  public void submitQuizAnswers(String quizId, String studentId, int[] mcqAnswers,
      String openEndedAnswer) {
    Quiz quiz = quizzes.get(quizId);
    User user = users.get(studentId);
    if (quiz == null) {
      throw new IllegalArgumentException("Quiz not found.");
    }
    if (user == null) {
      throw new IllegalArgumentException("Only students can submit quiz answers.");
    }
    quiz.submitAnswers(studentId, mcqAnswers, openEndedAnswer);
    saveQuizzes(); // Save quizzes to file after a student submits answers
  }

  // Teacher views all submissions for a quiz
  public Map<String, Quiz.StudentSubmission> viewSubmissions(String quizId, String teacherId) {
    Quiz quiz = quizzes.get(quizId);
    User user = users.get(teacherId);
    if (quiz == null) {
      throw new IllegalArgumentException("Quiz not found.");
    }
    if (user == null) {
      throw new IllegalArgumentException("Only teachers can view submissions.");
    }
    return quiz.getStudentSubmissions();
  }

  // Teacher grades a quiz
  public void gradeQuiz(String quizId, Map<String, Integer> grades, String teacherId) {
    Quiz quiz = quizzes.get(quizId);
    User user = users.get(teacherId);
    if (quiz == null) {
      throw new IllegalArgumentException("Quiz not found.");
    }
    if (user == null) {
      throw new IllegalArgumentException("Only teachers can grade quizzes.");
    }
    quiz.gradeQuiz(grades);
    saveQuizzes(); // Save quizzes to file after grading
  }

  // Student views their grade for a quiz
  public Integer viewGrade(String quizId, String studentId) {
    Quiz quiz = quizzes.get(quizId);
    User user = users.get(studentId);
    if (quiz == null) {
      throw new IllegalArgumentException("Quiz not found.");
    }
    if (user == null) {
      throw new IllegalArgumentException("Only students can view grades.");
    }
    return quiz.getStudentScore(studentId);
  }

  // Adding a user to the system (for demo purposes)
  public void addUser(User user) {
    users.put(user.getUserId(), user);
  }

  // Save quizzes to a JSON file
  private void saveQuizzes() {
    Gson gson = new Gson();
    try (FileWriter writer = new FileWriter(PATH.QUIZ.getFilePath())) {
      gson.toJson(quizzes, writer);
    } catch (IOException e) {
    }
  }

  // Load quizzes from a JSON file
  private void loadQuizzes() {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(PATH.QUIZ.getFilePath())) {
      Map<String, Quiz> loadedQuizzes = gson.fromJson(reader, new TypeToken<Map<String, Quiz>>() {
      }.getType());
      if (loadedQuizzes != null) {
        quizzes.putAll(loadedQuizzes);
      }
    } catch (IOException e) {
    }
  }
}
