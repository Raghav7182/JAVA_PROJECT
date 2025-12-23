package com.studentresult.services;

import com.studentresult.model.*;
import java.sql.*;
import java.util.*;

public class DBServices {
private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
private static final String URL = "jdbc:mysql://localhost:3306/project_java";
private static final String USERNAME = "root";
private static final String PASSWORD = "chitkara7182";

public Connection getConnection() throws Exception {
    Class.forName(DRIVER);
    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
}



    // ================= STUDENT =================
    public void storeStudentData(List<Student> students) {
        String sql = "INSERT INTO student VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (Student s : students) {
                ps.setInt(1, s.getRollNo());
                ps.setString(2, s.getName());
                ps.setString(3, s.getClassName());
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= MARKS =================
    public void storeMarks(List<Subject> subjects) {
        String sql =
            "INSERT INTO marks(roll_no, subject_name, marks) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (Subject s : subjects) {
                ps.setInt(1, s.getRollNo());
                ps.setString(2, s.getSubjectName());
                ps.setInt(3, s.getMarks());
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= RESULT =================
    public void storeTotalMarksAverageGrade(StudentServices ss) {

        String sql =
            "INSERT INTO result VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (String line : ss.getGrading()) {
                String[] arr = line.split(",");

                ps.setInt(1, Integer.parseInt(arr[0]));
                ps.setInt(2, Integer.parseInt(arr[1]));
                ps.setDouble(3, Double.parseDouble(arr[2]));
                ps.setString(4, arr[3]);
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DISPLAY REPORT =================
    public void displayReport() {

    String sql = "SELECT roll_no, total_marks, average, grade FROM result";

    try (Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            System.out.println("Roll No : " + rs.getInt("roll_no"));
            System.out.println("Total Marks : " + rs.getInt("total_marks"));
            System.out.println("Average : " + rs.getDouble("average"));
            System.out.println("Grade : " + rs.getString("grade"));
            System.out.println();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
