package com.example.helpers;

import java.nio.file.Paths;

public enum PATH {
  QUIZ(Paths.get("src", "main", "java", "com", "example", "database", "quiz.json").toString()),
  USER(Paths.get("src", "main", "java", "com", "example", "database", "user.json").toString()),
  COURSE(Paths.get("src", "main", "java", "com", "example", "database", "course.json").toString());

  private final String filePath;

  PATH(String filePath) {
    this.filePath = filePath;
  }

  public String getFilePath() {
    return filePath;
  }
}
