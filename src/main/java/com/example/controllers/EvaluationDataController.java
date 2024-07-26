package com.example.controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.example.exceptions.NotFoundException;
import com.example.helpers.PATH;
import com.example.models.Evaluation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class EvaluationDataController {
  private final Map<Integer, Evaluation> evaluations = new HashMap<>(); // {evaluationId, evaluationObject}
  private final Gson gson;

  public EvaluationDataController() {
    gson = new Gson();
    loadEvaluations();
  }

  // Create
  public void createEvaluation(int userId, Evaluation evaluation) {
    evaluations.put(userId, evaluation);
    saveEvaluations();
  }

  // Read
  public Evaluation getEvaluationByUserId(int userId) throws NotFoundException {
    Evaluation evaluation = evaluations.get(userId);
    if (evaluation == null) {
      throw new NotFoundException("Feedback for user with ID: " + userId + " not found.");
    }
    return evaluation;
  }

  // Load evaluations from JSON file
  private void loadEvaluations() {
    try (Reader reader = new FileReader(PATH.EVALUATION.getFilePath())) {
      Type evaluationMapType = new TypeToken<Map<Integer, Evaluation>>() {
      }.getType();
      Map<Integer, Evaluation> loadedEvaluations = gson.fromJson(reader, evaluationMapType);
      if (loadedEvaluations != null) {
        evaluations.putAll(loadedEvaluations);
      }
    } catch (IOException e) {
      // Log the exception or handle it as needed
      System.err.println("Failed to load evaluations: " + e.getMessage());
    }
  }

  // Save evaluations to JSON file
  private void saveEvaluations() {
    try (Writer writer = new FileWriter(PATH.EVALUATION.getFilePath())) {
      gson.toJson(evaluations, writer);
    } catch (IOException e) {
      // Log the exception or handle it as needed
      System.err.println("Failed to save evaluations: " + e.getMessage());
    }
  }
}
