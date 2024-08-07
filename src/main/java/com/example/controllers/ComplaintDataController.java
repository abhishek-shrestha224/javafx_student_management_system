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
import com.example.models.Complaint;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ComplaintDataController {
    private final Map<Integer, Complaint> complaints = new HashMap<>(); // {complaintId, complaintObject}
    private final Gson gson;

    public ComplaintDataController() {
        gson = new Gson();
        loadComplaints();
    }

    // Create
    public void createComplaint(Complaint complaint) {
        complaints.put(complaint.getId(), complaint);
        saveComplaints();
    }

    public List<Complaint> getAllComplaints() {
        return complaints.values().stream().collect(Collectors.toList());
    }

    // Load complaints from JSON file
    private void loadComplaints() {
        try (Reader reader = new FileReader(PATH.COMPLAINT.getFilePath())) {
            Type complaintMapType = new TypeToken<Map<Integer, Complaint>>() {
            }.getType();
            Map<Integer, Complaint> loadedComplaints = gson.fromJson(reader, complaintMapType);
            if (loadedComplaints != null) {
                complaints.putAll(loadedComplaints);
            }
        } catch (IOException e) {
            // Log the exception or handle it as needed
            System.err.println("Failed to load complaints: " + e.getMessage());
        }
    }

    // Save complaints to JSON file
    private void saveComplaints() {
        try (Writer writer = new FileWriter(PATH.COMPLAINT.getFilePath())) {
            gson.toJson(complaints, writer);
        } catch (IOException e) {
            // Log the exception or handle it as needed
            System.err.println("Failed to save complaints: " + e.getMessage());
        }
    }
}
