package com.example.controllers;

import com.example.models.Complaint;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ComplaintDataController {
    private static final String COMPLAINT_FILE_PATH = "com/example/database/complaint.json";
    private List<Complaint> complaints;
    private AtomicInteger complaintIdCounter;
    private Gson gson;

    public ComplaintDataController() {
        gson = new Gson();
        loadComplaints();
    }

    private void loadComplaints() {
        try (FileReader reader = new FileReader(COMPLAINT_FILE_PATH)) {
            complaints = gson.fromJson(reader, new TypeToken<List<Complaint>>() {}.getType());
            if (complaints == null) {
                complaints = new ArrayList<>();
            }
            complaintIdCounter = new AtomicInteger(
                    complaints.stream().mapToInt(Complaint::getComplaintId).max().orElse(0) + 1
            );
        } catch (IOException e) {
            complaints = new ArrayList<>();
            complaintIdCounter = new AtomicInteger(1);
        }
    }

    private void saveComplaints() {
        try (FileWriter writer = new FileWriter(COMPLAINT_FILE_PATH)) {
            gson.toJson(complaints, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addComplaint(Complaint complaint) {
        complaints.add(complaint);
        saveComplaints();
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public int getNextComplaintId() {
        return complaintIdCounter.getAndIncrement();
    }
}
