package com.njfu.edu.service.impl;

import com.njfu.edu.mapper.StudenClazzMapper;
import com.njfu.edu.pojo.StudentClass;
import com.njfu.edu.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentClassServiceImpl implements StudentClassService {

    @Autowired
    private StudenClazzMapper mapper;
    @Override
    public List<StudentClass> queryStudentClassById(int id) {
        List<StudentClass> studentClasses = mapper.queryStudentClassById(id);
        return studentClasses;
    }

    @Override
    public List<StudentClass> queryStudentClass() {
        List<StudentClass> studentClasses = mapper.queryStudentClass();
        return studentClasses;
    }
}
