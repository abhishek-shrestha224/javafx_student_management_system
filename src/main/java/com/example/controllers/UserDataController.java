package com.example.controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.adapters.LocalDateAdapter;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.NotFoundException;
import com.example.helpers.PATH;
import com.example.helpers.Utils;
import com.example.models.Role;
import com.example.models.User;
import com.google.gson.Gson; // Import Utils for hashing
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class UserDataController {
  private final Map<Integer, User> users = new HashMap<>(); // {uesrId:Integer, user:User}
  private final Gson gson;

  public UserDataController() {
    // Register the LocalDateAdapter with Gson
    gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();
    loadUsers();
  }

  // Sort users by ID for Serialization
  public void sortUsersById() {
    List<Map.Entry<Integer, User>> sortedEntries = users.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toList());
    users.clear();
    for (Map.Entry<Integer, User> entry : sortedEntries) {
      users.put(entry.getKey(), entry.getValue());
    }
  }

  // Create
  public void addUser(User user) {
    // Hash the password before saving
    user.setPassword(Utils.getSha256Hash(user.getPassword()));
    users.put(user.getId(), user);
    sortUsersById();
    saveUsers();
  }

  // Read
  public User getUserById(int userId) throws NotFoundException {
    User user = users.get(userId);
    if (user == null) {
      throw new NotFoundException("User with ID " + userId + " not found.");
    }
    return user;
  }

  // Get users by role
  public List<User> getUsersByRole(Role role) {
    return users.values().stream()
        .filter(user -> user.getRole() == role)
        .collect(Collectors.toList());
  }

  public Map<Integer, User> getAllUsers() {
    return users;
  }

  // Update
  public void updateUser(User user) throws NotFoundException {
    if (!users.containsKey(user.getId())) {
      throw new NotFoundException("User with ID " + user.getId() + " not found.");
    }

    User existingUser = users.get(user.getId());
    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
      if (!user.getPassword().equals(existingUser.getPassword())) {
        user.setPassword(Utils.getSha256Hash(user.getPassword()));
      }
    } else {
      user.setPassword(existingUser.getPassword());
    }

    users.put(user.getId(), user);
    sortUsersById();
    saveUsers();
  }

  // Delete
  public void deleteUserById(int userId) throws NotFoundException {
    if (!users.containsKey(userId)) {
      throw new NotFoundException("User with ID " + userId + " not found.");
    }
    users.remove(userId);
    saveUsers();
  }

  // Load users from JSON file
  private void loadUsers() {
    try (Reader reader = new FileReader(PATH.USER.getFilePath())) {
      Type userMapType = new TypeToken<Map<Integer, User>>() {
      }.getType();
      Map<Integer, User> loadedUsers = gson.fromJson(reader, userMapType);
      if (loadedUsers != null) {
        users.putAll(loadedUsers);
      }
    } catch (IOException e) {
      // Log the exception or handle it as needed
      System.err.println("Failed to load users: " + e.getMessage());
    }
  }

  // Save users to JSON file
  private void saveUsers() {
    try (Writer writer = new FileWriter(PATH.USER.getFilePath())) {
      gson.toJson(users, writer);
    } catch (IOException e) {
      // Log the exception or handle it as needed
      System.err.println("Failed to save users: " + e.getMessage());
    }
  }

  public boolean authenticate(int userId, String password) throws InvalidCredentialsException {
    User user = users.get(userId);
    if (user == null || !Utils.getSha256Hash(password).equals(user.getPassword())) {
      throw new InvalidCredentialsException("Invalid credentials for user ID " + userId);
    }
    return true;
  }

  public Role getUserRole(int userId) throws NotFoundException {
    User user = users.get(userId);
    if (user == null) {
      throw new NotFoundException("User with ID " + userId + " not found.");
    }
    return user.getRole();
  }
}
