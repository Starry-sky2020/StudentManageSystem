package com.njfu.edu.controller;

import com.njfu.edu.pojo.StudentClass;
import com.njfu.edu.service.StudentClassService;
import com.njfu.edu.service.impl.StudentClassServiceImpl;

import java.util.List;

public class StudentClassController {

    private StudentClassService service = new StudentClassServiceImpl();

    public List<StudentClass> queryStudentClassById(int collegeId){
        return service.queryStudentClassById(collegeId);
    }

    public List<StudentClass> queryStuedntClass(){
        return service.queryStudentClass();
    }
}
