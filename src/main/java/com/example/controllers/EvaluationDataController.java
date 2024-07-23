package com.example.controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
  public void createEvaluation(Evaluation evaluation) {
    evaluations.put(evaluation.getId(), evaluation);
    saveEvaluations();
  }

  // Read
  public Evaluation getEvaluationById(int evaluationId) throws NotFoundException {
    Evaluation evaluation = evaluations.get(evaluationId);
    if (evaluation == null) {
      throw new NotFoundException("Evaluation with ID " + evaluationId + " not found.");
    }
    return evaluation;
  }

  public List<Evaluation> getAllEvaluations() {
    return evaluations.values().stream().collect(Collectors.toList());
  }

  // Update
  public void updateEvaluation(Evaluation evaluation) throws NotFoundException {
    if (!evaluations.containsKey(evaluation.getId())) {
      throw new NotFoundException("Evaluation with ID " + evaluation.getId() + " not found.");
    }
    evaluations.put(evaluation.getId(), evaluation);
    saveEvaluations();
  }

  // Delete
  public void deleteEvaluationById(int evaluationId) throws NotFoundException {
    if (!evaluations.containsKey(evaluationId)) {
      throw new NotFoundException("Evaluation with ID " + evaluationId + " not found.");
    }
    evaluations.remove(evaluationId);
    saveEvaluations();
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
