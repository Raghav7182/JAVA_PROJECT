package com.studentresult.main;

import com.studentresult.services.FileServices;
import com.studentresult.services.ReportServices;
import com.studentresult.services.StudentServices;
import com.studentresult.model.Student;
import com.studentresult.model.Subject;
import java.io.*;
import java.util.*;

public class ResultApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File file1 = new File("com/data/student.csv");
        File file2 = new File("com/data/marks.csv");
        File file3 = new File("com/data/report.csv");

        FileServices f1 = new FileServices();
        ReportServices r1 = new ReportServices();
        StudentServices s2 = new StudentServices();

        List<Student> s1 = new ArrayList<>();
        List<Subject> sub = new ArrayList<>();

        int choice;

        do{
        System.out.println("STUDENT RESULT MENU");
        System.out.println("1. Add Students");
        System.out.println("2. Add Marks");
        System.out.println("3. Store Student Data to File");
        System.out.println("4. Store Marks to File");
        System.out.println("5. Generate Report");
        System.out.println("6. Display Student Data from File");
        System.out.println("7. Display Marks from File");
        System.out.println("8. Display Final Report");
        System.out.println("9. Display Top Performer");
        System.out.println("0. EXIT");
        System.out.print("Enter your choice: ");

        choice = sc.nextInt();
        sc.nextLine();

        switch(choice){

        case 1:
        System.out.print("Enter the number of students you want to enter : ");
        int ns = sc.nextInt();
        sc.nextLine();

        for(int i=0;i<ns;i++){
        System.out.println("Enter details of the student : "+(i+1));
        System.out.print("Enter roll no. of the student : ");
        int rnm = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter name of the student : ");
        String name = sc.nextLine();

        System.out.print("Enter Class of the student : ");
        String className = sc.nextLine();

        s1.add(new Student(rnm,name,className));

        }
        break;

        case 2:
        System.out.println(" ");
        System.out.print("Enter number of subjects for each student : ");
        int subcnt = sc.nextInt();
        sc.nextLine();

        if(file1.length()==0){
            System.out.println("Enter number of students and detail of each first!");
        }
        else{
        for(Student std : s1){
            System.out.println("\nEnter subject for roll no : "+std.getRollNo());
            for(int i=1;i<=subcnt;i++){
                System.out.print("Enter subject name : ");
                String subn =sc.nextLine();

                System.out.print("Enter marks : ");
                int m = sc.nextInt();
                sc.nextLine();

                sub.add(new Subject(std.getRollNo(), subn, m));
            }
        }
    }
        break;

        case 3:
        if(s1.isEmpty()){
            System.out.println("How can u store the data which you haven't entered yet? :/");
        }
        else{
        f1.storeStudentData(s1, file1);
        System.out.println("Your Data is stored successfully: ");
        }
        break;

        case 4:
        if(sub.isEmpty()){
            System.out.println("You havent entered marks yet..");
        }
        else{
        f1.storesMarks(sub, file2);
        System.out.println("Marks stored successfully :");
        }
        break;

        case 5:
        if(file2.length()==0){
            System.out.println("Sorry for the inconvinence ! Enter marks first...");
        }
        else{
        f1.storeTotalMarksAverageGrade(s2,file3);
        System.out.println("Report of sudents is generating .....");
        System.out.println("Completed and stored in a file ");
        }
        break;

        case 6:
        if(file1.length()==0){
            System.out.println("File is empty..");
        }     
        else{
        System.out.println("Reading student data from file... : ");
        f1.readStudentData(file1);
        }
        break;

        case 7:
        if(file2.length()==0){
            System.out.println("File is empty...");
        }
        else{
        System.out.println("Reading Marks from csv ....");
        f1.readMarks(file2);
        }
        break;

        case 8:
        if(file3.length()==0){
        System.out.println("File is empty...");
        }
        else{
        System.out.println("Showing Students Report ....");
        f1.displayReport(file3);
        }
        break;

        case 9:
        if(file3.length()==0){
        System.out.println("File is empty...");
        }
        else{
        System.out.println("Displaying Top Performer of the year ......");
        r1.displayTopPerformer(file3);
        }
        break;

        case 0:
        System.out.println("Exiting the program ....");
        break;

        default:
        System.out.println("Invalid choice !");

        }
        }while(choice != 0);
        sc.close();
    }
}
