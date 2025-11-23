package com.studentresult.model;
public class Student {
    private final int rollNo;
    private String name;
    private String className;

    public Student(int rollNo , String name , String className){
        this.rollNo = rollNo;
        this.name = name;
        this.className = className;
    }

    public int getRollNo(){
        return rollNo;
    }

    public String getName(){
        return name;
    }

    public String getClassName(){
        return className;
    }

    public String toString(){
        return "Roll No:"+rollNo+"Name:"+name+"Class:"+className;
    }
   
}
