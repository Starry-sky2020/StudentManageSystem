package com.njfu.edu.controller;

import com.njfu.edu.pojo.ImportResult;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.service.impl.ManageStudentServiceImpl;

import java.io.*;
import java.util.*;

public class ManageStudentController {

    private ManageStudentServiceImpl manageStudentService = new ManageStudentServiceImpl();

    /**
     * 查询所有学生
     * @return
     * @throws IOException
     */
    public List<Student> selectAllStudent() throws IOException {
        return manageStudentService.selectAllStudent();
    }

    /**
     * 根据学号查询学生信息
     * @param id
     * @return
     * @throws IOException
     */
    public Student selectStudetById(String id) throws IOException {
       return manageStudentService.selectStudetById(id);
    }

    /**
     * 更新学生信息
     * @param id
     * @param name
     * @param age
     * @param sex
     * @param school
     * @param address
     * @throws IOException
     */
    public void UpdateStudentById(String id, String name, Integer age, Boolean sex, String school, String address)
            throws IOException {
        manageStudentService.UpdateStudentById(id,name,age,sex,school,address);
    }

    /**
     * 根据学生Id排序
     * 冒泡排序
     * @return
     * @throws IOException
     */
    public List<Student> SortByStudetId(String key) throws IOException {
       return manageStudentService.SortByStudetId(key);
    }

    /**
     * 导入数据
     * 将新数据导入到原始文件中
     * @param path
     * @throws IOException
     */
    public ImportResult ImportStudentMessage(String path) throws IOException {
        return manageStudentService.ImportStudentMessage(path);
    }

    /**
     * 插入学生信息
     * 根据Student参数，获取插入文件的信息
     * 将信息直接写（追加方式）到文件中
     * @param student
     * @throws IOException
     */
    public void InsertStudentMessage(Student student) throws IOException {
        manageStudentService.InsertStudentMessage(student);
    }

    /**
     * 在列表中将元素删除
     * 重新写入到文件中
     * @param id
     * @throws IOException
     */
    public void DeleteStudentById(String id) throws IOException {
       manageStudentService.DeleteStudentById(id);
    }

    /**
     * 退出系统
     */
    public void BackwardSystem() {
        manageStudentService.BackwardSystem();
    }
}
