package com.example.helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.codec.digest.DigestUtils;

import com.example.controllers.PopupController;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

public class Utils {
  private static final Set<Integer> idSet = new HashSet<>();

  public static int getRandomInt() {
    Random random = new Random();
    return 100 + random.nextInt(900);
  }

  public static boolean idExist(int id) {
    return idSet.contains(id);
  }

  public static int generateId() {
    loadId();
    int id = getRandomInt();
    while (idExist(id)) {
      id = getRandomInt();
    }
    idSet.add(id);
    saveId();
    return id;
  }

  public static void loadId() {
    Gson gson = new Gson();
    try (BufferedReader reader = new BufferedReader(new FileReader(PATH.ID.getFilePath()))) {
      List<Integer> tempIdList = gson.fromJson(reader, new TypeToken<List<Integer>>() {
      }.getType());
      if (tempIdList != null) {
        idSet.addAll(tempIdList);
      }
    } catch (IOException e) {
      PopupController.showPopup("Read/Write Error", "Something went wrong while trying to access the Database");
    }
  }

  public static void saveId() {
    Gson gson = new Gson();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH.ID.getFilePath()))) {
      gson.toJson(new ArrayList<>(idSet), writer);
    } catch (IOException e) {
      PopupController.showPopup("Read/Write Error", "Something went wrong while trying to access the Database");
    }
  }

  public static String getSha256Hash(String input) {
    return DigestUtils.sha256Hex(input);
  }
}
