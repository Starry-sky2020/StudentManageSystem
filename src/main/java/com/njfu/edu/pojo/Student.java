package com.njfu.edu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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


    public Student(Long student_id, String name, Integer age, Integer sex, String school, String address) {
        this.student_id = student_id;
        this.student_name = name;
        this.age = age;
        this.sex = sex;
        this.school = school;
        this.address = address;
    }
}
