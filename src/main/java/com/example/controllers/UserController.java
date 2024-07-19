package com.example.controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.example.adapters.LocalDateAdapter;
import com.example.helpers.PATH;
import com.example.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class UserController {
  private final Map<String, User> users = new HashMap<>();
  private final Gson gson;

  public UserController() {
    // Register the LocalDateAdapter with Gson
    gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();
    loadUsers();
  }

  // Create
  public void addUser(User user) {
    users.put(user.getUserId(), user);
    saveUsers();
  }

  // Read
  public User getUser(String userId) {
    return users.get(userId);
  }

  // Update
  public void updateUser(User user) {
    if (users.containsKey(user.getUserId())) {
      users.put(user.getUserId(), user);
      saveUsers();
    }
  }

  // Delete
  public void deleteUser(String userId) {
    if (users.containsKey(userId)) {
      users.remove(userId);
      saveUsers();
    }
  }

  // Load users from JSON file
  private void loadUsers() {
    try (Reader reader = new FileReader(PATH.USER.getFilePath())) {
      Type userMapType = new TypeToken<Map<String, User>>() {
      }.getType();
      Map<String, User> loadedUsers = gson.fromJson(reader, userMapType);
      if (loadedUsers != null) {
        users.putAll(loadedUsers);
      }
    } catch (IOException e) {
    }
  }

  // Save users to JSON file
  private void saveUsers() {
    try (Writer writer = new FileWriter(PATH.USER.getFilePath())) {
      gson.toJson(users, writer);
    } catch (IOException e) {
    }
  }
}
