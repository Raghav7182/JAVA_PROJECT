package com.studentresult.services;
import java.util.*;
import java.nio.file.*;
import java.io.*;
public class ReportServices {
    public void displayTopPerformer(File filePath){
        try{
        List<String> lines = Files.readAllLines(filePath.toPath());
        String top = lines.stream().skip(1).sorted((a,b)->{
            double avg1 = Double.parseDouble(a.split(",")[2]);
            double avg2 = Double.parseDouble(b.split(",")[2]);
            return Double.compare(avg2, avg1);
        }).findFirst().get();
        String[] arr = top.split(",");
        System.out.println("Top Performer :");
        System.out.println("Roll No : "+arr[0]);
        System.out.println("Total Marks: "+arr[1]);
        System.out.println("Average : "+arr[2]);
        System.out.println("Grade : "+arr[3]);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
