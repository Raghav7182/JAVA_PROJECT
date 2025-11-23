package com.studentresult.services;

import java.util.*;
import java.io.*;
import java.nio.file.*;
public class StudentServices {
    List<String> totalresult = new ArrayList<>();
    List<String> totalAverage = new ArrayList<>();
    List<String> grading = new ArrayList<>();
    int subjectCount = 0;

    public void calculateTotalMarks(File filePath){
        try{
        List<String> lines = Files.readAllLines(filePath.toPath());
        int total = 0;
        int currentRollNo = 0;
        for(int i=1;i<lines.size();i++){
            String[] arr = lines.get(i).split(",");
            int rollNo = Integer.parseInt(arr[0]);
            int marks = Integer.parseInt(arr[2]);
            if(currentRollNo == 0){
                currentRollNo = rollNo;
            }
            if(rollNo != currentRollNo){
                System.out.println("Total Marks of Roll No : "+currentRollNo+" is ="+total);
                totalresult.add(currentRollNo+","+total+","+subjectCount);
                total = 0;
                subjectCount = 0;
                currentRollNo = rollNo;
            }
            subjectCount++;
            total += marks;
        }
        totalresult.add(currentRollNo+","+total+","+subjectCount);
        System.out.println("Total Marks of Roll No : "+currentRollNo+" is ="+total);
        System.out.println(" ");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void calculateAverage(){
        if(totalresult.isEmpty()){
            System.out.println("List is empty");
        }
        for(String s : totalresult){
            String[] arr = s.split(",");
            int rollNo = Integer.parseInt(arr[0]);
            int total = Integer.parseInt(arr[1]);
            int totalSubject = Integer.parseInt(arr[2]);

            double average = (double) total / totalSubject;
            totalAverage.add(rollNo+","+total+","+average);
            System.out.println("Average of RollNo : "+rollNo+" is : "+String.format("%.2f",average));
        }
        System.out.println(" ");
    }

    public void calculateGrade(){
        char grade;
        if(totalAverage.isEmpty()){
            System.out.println("List is empty");
        }
        for(String s : totalAverage){
            String[] arr = s.split(",");
            int rollNo = Integer.parseInt(arr[0]);
            int total = Integer.parseInt(arr[1]);
            double average = Double.parseDouble((arr[2]));
            double per = (total * 100)/300;
            if(per >= 90.0){
                grade = 'O';
            }
            else if(per >=70){
                grade = 'A';
            }
            else if(per >=40){
                grade = 'B';
            }
            else{
                grade = 'F';
            }
            
            int totalMarks = 0;
            for(String s1 : totalresult){
            String[] arr2 = s1.split(",");
            int rollNo2 = Integer.parseInt(arr2[0]);
            if(rollNo2 == rollNo){
                totalMarks =  Integer.parseInt(arr2[1]);
            }
        }
        grading.add(rollNo+","+totalMarks+","+average+","+grade);
        System.out.println("Grade of Roll No : "+rollNo+" is: "+grade);
        }
        System.out.println(" ");
    }
    
    public List<String> getGrading(){
        return grading;
    }
}
