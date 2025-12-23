package com.studentresult.services;

import java.sql.*;

public class ReportServices {

    private DBServices dbServices;

    public ReportServices(DBServices dbServices) {
        this.dbServices = dbServices;
    }

    public void displayTopPerformer() {
        String sql = "SELECT roll_no, total_marks, average, grade FROM result ORDER BY average DESC LIMIT 1";

        try (Connection con = dbServices.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                System.out.println("Roll No : " + rs.getInt("roll_no"));
                System.out.println("Total Marks: " + rs.getInt("total_marks"));
                System.out.println("Average : " + String.format("%.2f", rs.getDouble("average")));
                System.out.println("Grade : " + rs.getString("grade"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
