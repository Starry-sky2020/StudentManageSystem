package com.njfu.edu.service.impl;

import com.njfu.edu.dao.StudentDao;
import com.njfu.edu.pojo.CheckStudentFormatResult;
import com.njfu.edu.pojo.ImportResult;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.service.ManageStudentService;

import java.io.*;
import java.util.*;

public class ManageStudentServiceImpl implements ManageStudentService {
//    private ReadFile readFile = new ReadFile();
    private StudentDao studentDao = new StudentDao();
    private List<Student> studentList;
    public ManageStudentServiceImpl() {
        try {
            studentList = studentDao.selectStudentMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有学生
     * @return
     * @throws IOException
     */
    @Override
    public List<Student> selectAllStudent() throws IOException {
        return studentDao.selectStudentMessage();
    }

    /**
     * 根据学号查询学生信息
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public Student selectStudetById(String id) throws IOException {
        for (int i = 0; i < studentList.size(); i++){
            if (studentList.get(i).getStudent_id().equals(id)){
                return studentList.get(i);
            }
        }
        return null;
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
    @Override
    public void UpdateStudentById(String id, String name, Integer age, Boolean sex, String school, String address)
            throws IOException {
        Student student = selectStudetById(id);
        DeleteStudentById(id);

        if (name != null)
            student.setStudent_name(name);
        if (age != null)
            student.setAge(age);
        if (sex != null)
            student.setSex(sex);
        if (school != null)
            student.setSchool(school);
        if (address != null)
            student.setAddress(address);

        InsertStudentMessage(student);
    }


    /**
     * 根据学生Id排序
     * 冒泡排序
     * @return
     * @throws IOException
     */
    @Override
    public List<Student> SortByStudetId(String key) throws IOException {
        Comparator<Student> comparator = null;

        if (SORT_BY_ID.equals(key)){
            comparator = new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o1.getStudent_id().compareTo(o2.getStudent_id());
                }
            };
        } else if (SORT_BY_AGE.equals(key)) {
            comparator = new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o1.getAge() - o2.getAge();
                }
            };
        } else if (SORT_BY_SEX.equals(key)) {
            comparator = new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    int tmp = 1;
                    if (o1.isSex() && !o2.isSex())
                        tmp = 1;
                    if (!o1.isSex() && o2.isSex())
                        tmp = -1;
                    return tmp;
                }
            };
        }

        Collections.sort(studentList,comparator);

        return studentList;
    }

    /**
     * 导入数据
     * 将新数据导入到原始文件中
     * @param path
     * @throws IOException
     */
    @Override
    public ImportResult ImportStudentMessage(String path) throws IOException {

        ImportResult importResult = new ImportResult();

        //文件名为空
        if (path == null || path.equals("")){
            importResult.setResult(false);
            importResult.setCode(ImportResult.CODE_1);
            importResult.setMessage("文件名为空");
            return importResult;
        }

        //文件不存在
        if (!new File(path).exists()){
            importResult.setResult(false);
            importResult.setCode(ImportResult.CODE_2);
            importResult.setMessage("文件名不存在");
            return importResult;
        }

        //文件内容格式检查
        CheckStudentFormatResult checkStudentFormatResult = studentDao.checkStudentFormat(path);
        if (checkStudentFormatResult.getFailCode().size() != 0){
            importResult.setResult(false);
            importResult.setCode(ImportResult.CODE_3);
            importResult.setMessage("文件内容格式不正确");
            return importResult;
        }

        List<Student> data = studentDao.selectStudentMessage(path);

        for (int i = 0; i < data.size(); i++){
            /**
             * selectStudetById查询系统文件内的学生
             * 检验导入的学生是否已经存在在系统文件
             */
            if (selectStudetById(data.get(i).getStudent_id()) == null){
                importResult.getSuccessData().add(data.get(i));
            } else {
                importResult.getExistData().add(data.get(i));
            }
        }

        for (Student student : data){
            studentDao.insertStudent(student);
        }

        importResult.setResult(true);
        importResult.setCode(0);
        importResult.setMessage("导入成功");

        return importResult;

    }

    /**
     * 插入学生信息
     * 根据Student参数，获取插入文件的信息
     * 将信息直接写（追加方式）到文件中
     * @param student
     * @throws IOException
     */
    @Override
    public void InsertStudentMessage(Student student) throws IOException {
        studentDao.insertStudent(student);
    }

    /**
     * 在列表中将元素删除
     * 重新写入到文件中
     * @param id
     * @throws IOException
     */
    @Override
    public void DeleteStudentById(String id) throws IOException {
        studentDao.deleteStudentById(id);
    }

    /**
     * 退出系统
     */
    @Override
    public void BackwardSystem() {
        System.exit(0);
    }
}
