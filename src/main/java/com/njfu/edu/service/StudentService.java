package com.njfu.edu.service;

import com.njfu.edu.pojo.ImportResult;
import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface StudentService {

    /**
     * 查询所有学生信息
     * 采用分页
     * @return
     */
    void selectAllStudent(Paging paging) throws IOException;


    /**
     * 根据学生学号查询学生信息
     * @return
     */
    Student selectStudetById(Long id) throws IOException;


    /**
     * 根据学号进行排序
     */
    List<Student> SortByStudetId(Paging paging) throws IOException;

    /**
     * 导入学生信息
     */
    ImportResult ImportStudentMessage(String path) throws IOException;

    /**
     *  插入学生信息
     */
    void InsertStudentMessage(Student student) throws IOException;

    /**
     * 根据学号删除学生信息
     * 并在文件中删除特定行
     * @param id
     */
    void DeleteStudentById(String id) throws IOException;

    /**
     * 更改学生信息
     * @return
     */
    boolean changeStudentInfo(Student student) throws ParseException;

    List<Student> selectStuInfo() ;
}
