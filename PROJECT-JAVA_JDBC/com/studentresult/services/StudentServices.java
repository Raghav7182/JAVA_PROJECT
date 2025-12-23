package com.studentresult.services;

import java.sql.*;
import java.util.*;

public class StudentServices {

    private DBServices dbServices;

    List<Integer> rollNos = new ArrayList<>();
    List<Integer> totalMarksList = new ArrayList<>();
    List<Double> averageList = new ArrayList<>();
    List<Character> gradeList = new ArrayList<>();

    public StudentServices(DBServices dbServices) {
        this.dbServices = dbServices;
    }

    public void calculateResultsFromDB() {
        String sql = "SELECT roll_no, marks FROM marks ORDER BY roll_no";

        try (Connection con = dbServices.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            int currentRoll = -1;
            int total = 0;
            int count = 0;

            while (rs.next()) {
                int rollNo = rs.getInt("roll_no");
                int marks = rs.getInt("marks");

                if (currentRoll == -1) {
                    currentRoll = rollNo;
                }

                // When roll changes, save previous student's result
                if (rollNo != currentRoll) {
                    saveResult(currentRoll, total, count);
                    currentRoll = rollNo;
                    total = 0;
                    count = 0;
                }

                total += marks;
                count++;
            }

            // Save last student's result
            if (currentRoll != -1) {
                saveResult(currentRoll, total, count);
            }

            // Display results
            for (int i = 0; i < rollNos.size(); i++) {
                System.out.println("RollNo: " + rollNos.get(i) +
                        ", Total: " + totalMarksList.get(i) +
                        ", Average: " + String.format("%.2f", averageList.get(i)) +
                        ", Grade: " + gradeList.get(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveResult(int rollNo, int total, int subjects) {
        rollNos.add(rollNo);
        totalMarksList.add(total);
        double average = (double) total / subjects;
        averageList.add(average);

        double percentage = (total * 100.0) / (subjects * 100);
        char grade;
        if (percentage >= 90) grade = 'O';
        else if (percentage >= 70) grade = 'A';
        else if (percentage >= 40) grade = 'B';
        else grade = 'F';
        gradeList.add(grade);
    }

    public List<String> getGrading() {
        List<String> grading = new ArrayList<>();
        for (int i = 0; i < rollNos.size(); i++) {
            grading.add(rollNos.get(i) + "," + totalMarksList.get(i) + "," +
                    averageList.get(i) + "," + gradeList.get(i));
        }
        return grading;
    }
}
