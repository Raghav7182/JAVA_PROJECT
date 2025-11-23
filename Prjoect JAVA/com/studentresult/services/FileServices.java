package com.studentresult.services;

import com.studentresult.model.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class FileServices {
    public void storeStudentData(List<Student> students, File filePath){
        try{
            List<String> student = new ArrayList<>();
            student.add("RollNo,Name,Class");
            for(Student s : students){
            student.add(s.getRollNo()+","+s.getName()+","+s.getClassName());
            }

            Files.write(filePath.toPath(),student);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void storesMarks(List<Subject> subjects , File filePath){
        try{
            List<String> subject = new ArrayList<>();
            subject.add("RollNo,Name,Marks");
            for(Subject s : subjects){
            subject.add(s.getRollNo()+","+s.getSubjectName()+","+s.getMarks()); 
            }

            Files.write(filePath.toPath(),subject);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // public void storeStudentData(Student student , File filePath){
    //     try{
    //     Stream<String> lines = Stream.of("RollNo,Name,Class",
    //     String.valueOf(student.getRollNo())+","+student.getName()+","+student.getClassName());
        
    //     Files.write(filePath.toPath(),lines.collect(Collectors.toList()));
    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }
    // }

    // public void storesMarks(Subject subject , File filePath){
    //     try{
    //         Stream<String> lines = Stream.of("RollNo,Name,Marks",
    //         String.valueOf(subject.getRollNo())+","+subject.getSubjectName()+","+String.valueOf(subject.getMarks()));

    //         Files.write(filePath.toPath(),lines.collect(Collectors.toList()));
    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }
    // }

    public void readStudentData(File filePath){
        try{
            List<String> lines = Files.readAllLines(filePath.toPath());
            for(int i=1;i<lines.size();i++){
                String[] arr = lines.get(i).split(",");
                System.out.println("RollNo : "+arr[0]+" Name : "+arr[1]+" Class : "+arr[2]);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void readMarks(File filePath){
        try{
            List<String> lines = Files.readAllLines(filePath.toPath());
            for(int i=1;i<lines.size();i++){
                String[] arr = lines.get(i).split(",");
                System.out.println("Roll No : "+arr[0]+" Subject Name : "+arr[1]+" Marks : "+arr[2]);
            }
            System.out.println(" ");
            }catch(Exception e){
                e.printStackTrace();
        }
    }

    // public void readStudentData(File filePath){
    //     try(Stream<String> lines = Files.lines(filePath.toPath())){
    //         List<Student> students = lines.skip(1).map(line ->{
    //                 String[] arr = line.split(",");
    //                 return new Student(Integer.parseInt(arr[0]) , arr[1] , arr[2]);
    //             }).collect(Collectors.toList());
    //     students.forEach(System.out::println);
    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }
    // }

    // public void readMarks(File filePath){
    //     try(Stream<String> lines = Files.lines(filePath.toPath())){
    //         List<Subject> subjects = lines.skip(1).map(line ->{
    //             String[] arr = line.split(",");
    //             return new Subject(Integer.parseInt(arr[0]),arr[1],Integer.parseInt(arr[2]));
    //         }).collect(Collectors.toList());
    //         subjects.forEach(System.out::println);
    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }
    // }

    public void storeTotalMarksAverageGrade(StudentServices ss , File filePath){
        try{
            List<String> gradingList = ss.getGrading();
            List<String> lines = new ArrayList<>();
            lines.add("RollNO,TotalMarks,Average,Grade");
            lines.addAll(gradingList);

            Files.write(filePath.toPath(),lines);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void displayReport(File filePath){
        try{
        List<String> lines = Files.readAllLines(filePath.toPath());
        for(int i=1;i<lines.size();i++){
            String[] arr = lines.get(i).split(",");
            System.out.println("Roll No : "+arr[0]);
            System.out.println("Total Marks : "+arr[1]);
            System.out.println("Average: "+arr[2]);
            System.out.println("Grade : "+arr[3]);
            System.out.println(" ");
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
