package com.studentresult.main;

import com.studentresult.services.*;
import com.studentresult.model.Student;
import com.studentresult.model.Subject;

import java.util.*;

public class ResultApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DBServices dbServices = new DBServices();
        StudentServices studentServices = new StudentServices(dbServices);
        ReportServices reportServices = new ReportServices(dbServices);

        List<Student> students = new ArrayList<>();
        List<Subject> subjects = new ArrayList<>();

        int choice;

        do {
            System.out.println("\nSTUDENT RESULT MENU");
            System.out.println("1. Add Students");
            System.out.println("2. Add Marks");
            System.out.println("3. Store Student Data to Database");
            System.out.println("4. Store Marks to Database");
            System.out.println("5. Generate Result Report");
            System.out.println("6. Display Student Data from DB");
            System.out.println("7. Display Marks from DB");
            System.out.println("8. Display Final Result Report");
            System.out.println("9. Display Top Performer");
            System.out.println("0. EXIT");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter number of students: ");
                    int ns = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < ns; i++) {
                        System.out.println("Enter details of student " + (i + 1));
                        System.out.print("Roll No: ");
                        int rollNo = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Class: ");
                        String className = sc.nextLine();

                        students.add(new Student(rollNo, name, className));
                    }
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("Enter students first!");
                        break;
                    }

                    System.out.print("Enter number of subjects for each student: ");
                    int subCount = sc.nextInt();
                    sc.nextLine();

                    for (Student s : students) {
                        System.out.println("Enter subjects for Roll No: " + s.getRollNo());
                        for (int i = 1; i <= subCount; i++) {
                            System.out.print("Subject Name: ");
                            String subName = sc.nextLine();

                            System.out.print("Marks: ");
                            int marks = sc.nextInt();
                            sc.nextLine();

                            subjects.add(new Subject(s.getRollNo(), subName, marks));
                        }
                    }
                    break;

                case 3:
                    if (students.isEmpty()) {
                        System.out.println("No students to store!");
                    } else {
                        dbServices.storeStudentData(students);
                        System.out.println("Student data stored successfully in DB!");
                    }
                    break;

                case 4:
                    if (subjects.isEmpty()) {
                        System.out.println("No marks to store!");
                    } else {
                        dbServices.storeMarks(subjects);
                        System.out.println("Marks stored successfully in DB!");
                    }
                    break;

                case 5:
                    // Calculate results from DB and store in 'result' table
                    studentServices.calculateResultsFromDB();
                    dbServices.storeTotalMarksAverageGrade(studentServices);
                    System.out.println("Result report generated and stored in DB!");
                    break;

                case 6:
                    System.out.println("Student Data from DB:");
                    String studentSql = "SELECT * FROM student";
                    try (var con = dbServices.getConnection();
                         var ps = con.prepareStatement(studentSql);
                         var rs = ps.executeQuery()) {

                        while (rs.next()) {
                            System.out.println("Roll No: " + rs.getInt("roll_no") +
                                    ", Name: " + rs.getString("name") +
                                    ", Class: " + rs.getString("class_name"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 7:
                    System.out.println("Marks Data from DB:");
                    String marksSql = "SELECT * FROM marks";
                    try (var con = dbServices.getConnection();
                         var ps = con.prepareStatement(marksSql);
                         var rs = ps.executeQuery()) {

                        while (rs.next()) {
                            System.out.println("Roll No: " + rs.getInt("roll_no") +
                                    ", Subject: " + rs.getString("subject_name") +
                                    ", Marks: " + rs.getInt("marks"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 8:
                    System.out.println("Final Result Report:");
                    dbServices.displayReport();
                    break;

                case 9:
                    System.out.println("Top Performer:");
                    reportServices.displayTopPerformer();
                    break;

                case 0:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
