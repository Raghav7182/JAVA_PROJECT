package com.studentresult.model;
public class Result {
    private final int rollNo;
    private int totalmarks;
    private double average;
    private String grade;

    Result(int rollNo , int totalmarks , double average , String grade){
        this.rollNo = rollNo;
        this.totalmarks = totalmarks;
        this.average = average;
        this.grade = grade;
    }

    public int getRollNo(){
        return rollNo;
    }

    public int getTotalMarks(){
        return totalmarks;
    }

    public double getAverage(){
        return average;
    }

    public String getGrade(){
        return grade;
    }

    public String toString(){
        return "RollNo:"+rollNo+"TotalMarks:"+totalmarks+"Average:"+average+"Grade:"+grade;
    }
}
