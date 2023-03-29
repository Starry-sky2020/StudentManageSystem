package com.njfu.edu.pojo;

import lombok.Data;

@Data
public class Student {

    private String student_id;
    private String student_name;
    private Integer age;
    private boolean sex;
    private String school;
    private String address;

    public Student(){

    }

    public Student(String student_id, String name, Integer age, boolean sex, String school, String address) {
        this.student_id = student_id;
        this.student_name = name;
        this.age = age;
        this.sex = sex;
        this.school = school;
        this.address = address;
    }
}
