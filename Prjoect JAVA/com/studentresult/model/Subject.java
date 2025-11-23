package com.studentresult.model;

public class Subject {
    private final int rollNo;
    private String subjectName;
    private int marks;

    public Subject(int rollNo , String subjectName , int marks){
        this.rollNo = rollNo;
        this.subjectName = subjectName;
        this.marks = marks;
    }

    public int getRollNo(){
        return rollNo;
    }

    public String getSubjectName(){
        return subjectName;
    }

    public int getMarks(){
        return marks;
    }

    public String toString(){
        return "RollNo:"+rollNo+"Subject:"+subjectName+"Marks:"+marks;
    }

}
