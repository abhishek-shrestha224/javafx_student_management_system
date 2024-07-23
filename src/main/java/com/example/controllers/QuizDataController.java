package com.example.controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.exceptions.ForbiddenException;
import com.example.exceptions.NotFoundException;
import com.example.helpers.PATH;
import com.example.models.Quiz;
import com.example.models.Role;
import com.example.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class QuizDataController {
  private final Map<Integer, Quiz> quizzes; // Key: quizId, Value: Quiz object
  UserDataController userDataController;

  public QuizDataController() {
    this.quizzes = new HashMap<>();
    userDataController = new UserDataController();
    loadQuizzes(); // Load quizzes from file when controller is initialized
  }

  // Teacher creates a weekly quiz
  public void addQuiz(Quiz quiz, int userId) throws ForbiddenException {
    try {
      User user = userDataController.getUserById(userId);
      if (user == null || user.getRole() != Role.TEACHER) {
        throw new ForbiddenException("Only teacher can create quiz");
      }

      quizzes.put(quiz.getId(), quiz);
      saveQuizzes();
    } catch (ForbiddenException | NotFoundException err) {
      throw new ForbiddenException(err.getLocalizedMessage());
    }

  }

  // Student views a quiz

  public Map<Integer, Quiz> getAllQuizzes(int userId) throws NotFoundException, ForbiddenException {

    try {
      User user = userDataController.getUserById(userId);
      if (user == null || user.getRole() != Role.STUDENT) {
        throw new ForbiddenException("Only students can view quizzes.");
      }
      return quizzes;
    } catch (ForbiddenException | NotFoundException err) {
      throw new ForbiddenException(err.getLocalizedMessage());
    }

  }

  public Quiz getQuizById(int quizId, int userId) throws ForbiddenException {
    try {
      User user = userDataController.getUserById(userId);
      if (user == null || user.getRole() != Role.STUDENT) {
        throw new ForbiddenException("Only students can view quizzes.");
      }

      return quizzes.get(quizId);

    } catch (ForbiddenException | NotFoundException err) {
      throw new ForbiddenException(err.getLocalizedMessage());
    }

  }

  // Student submits answers for a quiz
  public void submitQuizAnswers(int quizId, int userId, int[] mcqAnswers,
      String openEndedAnswer) throws ForbiddenException, NotFoundException {
    try {
      Quiz quiz = quizzes.get(quizId);
      User user = userDataController.getUserById(userId);
      if (quiz == null) {
        throw new NotFoundException("Quiz not found.");
      }
      if (user.getRole() != Role.STUDENT) {
        throw new ForbiddenException("Only students can submit quiz answers.");
      }
      quiz.submitAnswers(userId, mcqAnswers, openEndedAnswer);
      saveQuizzes();

    } catch (ForbiddenException | NotFoundException err) {
      throw new ForbiddenException(err.getLocalizedMessage());
    }

  }

  // Teacher views all submissions for a quiz
  public Map<Integer, Quiz.StudentSubmission> getAllSubmissions(int quizId, int userId)
      throws ForbiddenException, NotFoundException {
    try {
      Quiz quiz = quizzes.get(quizId);
      User user = userDataController.getUserById(userId);
      if (quiz == null) {
        throw new NotFoundException("Quiz not found.");
      }
      if (user.getRole() != Role.TEACHER) {
        throw new ForbiddenException("Only teachers can view submissions.");
      }
      return quiz.getStudentSubmissions();
    } catch (ForbiddenException | NotFoundException err) {
      throw new ForbiddenException(err.getLocalizedMessage());
    }

  }

  // Teacher grades a quiz
  public void gradeQuiz(int quizId, Map<Integer, Integer> grades, int userId)
      throws ForbiddenException, NotFoundException {
    try {
      Quiz quiz = quizzes.get(quizId);
      User user = userDataController.getUserById(userId);
      if (quiz == null) {
        throw new NotFoundException("Quiz not found.");
      }
      if (user.getRole() != Role.TEACHER) {
        throw new ForbiddenException("Only teachers can grade quizzes.");
      }
      quiz.gradeQuiz(grades);
      saveQuizzes(); // Save quizzes to file after grading
    } catch (ForbiddenException | NotFoundException err) {
      throw new ForbiddenException(err.getLocalizedMessage());
    }

  }

  // Student views their grade for a quiz
  public Integer getGradeById(int quizId, int userId) throws ForbiddenException, NotFoundException {
    try {
      Quiz quiz = quizzes.get(quizId);
      User user = userDataController.getUserById(userId);
      if (quiz == null) {
        throw new NotFoundException("Quiz not found.");
      }
      if (user.getRole() != Role.STUDENT) {
        throw new ForbiddenException("Only students can view grades.");
      }
      return quiz.getStudentScore(userId);
    } catch (ForbiddenException | NotFoundException err) {
      throw new ForbiddenException(err.getLocalizedMessage());
    }

  }

  // Save quizzes to a JSON file
  private void saveQuizzes() {
    Gson gson = new Gson();
    try (FileWriter writer = new FileWriter(PATH.QUIZ.getFilePath())) {
      gson.toJson(quizzes, writer);
    } catch (IOException e) {
      PopupController.showPopup("512-Read Write Error", "Unable to write to quiz file");
    }
  }

  // Load quizzes from a JSON file
  private void loadQuizzes() {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(PATH.QUIZ.getFilePath())) {
      Map<Integer, Quiz> loadedQuizzes = gson.fromJson(reader, new TypeToken<Map<Integer, Quiz>>() {
      }.getType());
      if (loadedQuizzes != null) {
        quizzes.putAll(loadedQuizzes);
      }
    } catch (IOException e) {
      PopupController.showPopup("512-Read Write Error", "Unable to read from quiz file");
    }
  }
}
