package com.xxniu.file.bean;

import com.alibaba.fastjson.annotation.JSONField;
public class Student
{
    @JSONField(name = "USERNAMES")
    private String studentName;
    @JSONField(name = "STUDENTAGE")
    private String studentAge;
    public String getStudentName()
    {
        return studentName;
    }
    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }
    public String getStudentAge()
    {
        return studentAge;
    }
    public void setStudentAge(String studentAge)
    {
        this.studentAge = studentAge;
    }
}
