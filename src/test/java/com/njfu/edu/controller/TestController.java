package com.njfu.edu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.pojo.StudentClass;
import com.njfu.edu.service.StudentClassService;
import com.njfu.edu.service.StudentService;
import com.njfu.edu.service.impl.StudentClassServiceImpl;
import com.njfu.edu.service.impl.StudentServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestController {
    private StudentService studentService = new StudentServiceImpl();
    private StudentClassService classService = new StudentClassServiceImpl();
    @Test
    public void testDataView(){
        List<Student> students = studentService.selectStuInfo();
        List<StudentClass> studentClasses = classService.queryStudentClass();
        Map map = new HashMap();

        for (int i = 0; i < studentClasses.size(); i++){
            int cnt = 0;
            for (int j = 0; j < students.size();j++){
                if (students.get(j).getStudentClassId().equals(studentClasses.get(i).getId())){
                    map.put(studentClasses.get(i).getId(),++cnt);
                }
            }
        }
        System.out.println(map);
        String json = new JSONObject(map).toString();
        System.out.println(json);
    }
}
