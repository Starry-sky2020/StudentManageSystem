package com.njfu.edu.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Student {

    private String student_id;
    private String student_name;
    private Integer age;
    private Integer  sex;
    private String school;
    private String address;
    private int deleteFlag;
    private String info;
    private Timestamp updateTime;

    public Student(){

    }

    public Student(String student_id, String name, Integer age, int sex, String school, String address) {
        this.student_id = student_id;
        this.student_name = name;
        this.age = age;
        this.sex = sex;
        this.school = school;
        this.address = address;
    }


}
