package com.njfu.edu.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Student implements Serializable {

    private Long student_id;
    private String student_name;
    private Integer age;
    private Integer sex;
    private String school;
    private String address;
    private Integer deleteFlag;
    private String info;
    private Timestamp updateTime;
    private Integer studentClassId;
    private String collegeName;
    private String studentclassName;
    private StudentClass studentClass;
    private College college;


    public Student(){

    }

    public Student(Long student_id, String name, Integer age, Integer sex, String school, String address) {
        this.student_id = student_id;
        this.student_name = name;
        this.age = age;
        this.sex = sex;
        this.school = school;
        this.address = address;
    }


}
