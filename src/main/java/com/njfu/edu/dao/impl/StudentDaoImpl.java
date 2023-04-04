package com.njfu.edu.dao.impl;

import com.njfu.edu.dao.StudentDao;
import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.utils.CRUDUtils;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private CRUDUtils crudUtils = new CRUDUtils();
    public void insertStudent(Student student){
        String sql = "insert into lessonTraining.student(student_name,age,sex,school,address) " +
                "value(?,?,?,?,?)";
        crudUtils.insert(sql,student.getStudent_name(),String.valueOf(student.getAge()),String.valueOf(student.getSex()),student.getSchool(),student.getAddress());
    }

    public List<Student> selectStudentMessage() {
        String sql = "select * from lessontraining.student";
        List<Student> studentList = CRUDUtils.query(Student.class, sql, null);
        return studentList;
    }

    public long selectItems(Paging paging) throws SQLException {

        String sqlItems = "select count(*) from lessontraining.student where 1 = 1";

        List list = new ArrayList<>();
        Object[] args = new Object[list.size()];
        if (paging.getMap() != null){
            if (paging.getMap().containsKey("age")){
                sqlItems += " and age = ?";
                list.add(paging.getMap().get("age"));
            }
            if (paging.getMap().containsKey("name")){
                sqlItems += " and student_name like concat('%',?,'%')";
                list.add( paging.getMap().get("name"));
            }
            for(int i = 0; i < list.size(); i++){
                args[i] = list.get(i);
            }
        }

        long cnt = CRUDUtils.selectItems(sqlItems,args);

        return cnt;
    }


    /**
     * 获取数据信息
     * 采用分页查询
     * @return
     */
    @Override
    public List<Student> selectStudentMessage(Paging paging){
        int page = paging.getPageNum()-1;
        int pageSize = paging.getPageSize();
        String sql = "select * from lessontraining.student where 1 = 1";

        List list = new ArrayList<>();
        Object[] args = new Object[list.size()];
        if (paging.getMap() != null){
            if (paging.getMap().containsKey("age")){
                sql += " and age = ?";
                list.add(paging.getMap().get("age"));
            }
            if (paging.getMap().containsKey("name")){
                sql += " and student_name like concat('%',?,'%')";
                list.add( paging.getMap().get("name"));
            }
            if (paging.getMap().containsKey("student_id")){
                sql += " and student_id = ?";
            }
            if (paging.getMap().containsKey("key")){
                if (paging.getMap().get("key").equals(SORT_BY_ID)) {
                    sql += " order by student_id";
                }
                if (paging.getMap().get("key").equals(SORT_BY_AGE)){
                    sql += " order by age";
                }

                if (paging.getMap().get("key").equals(SORT_BY_SEX)){
                    sql += " order by sex";
                }

            }

            for(int i = 0; i < list.size(); i++){
                args[i] = list.get(i);
            }
        }
        sql += " limit "+page+","+pageSize;
        return CRUDUtils.query(Student.class,sql,args);
    }

    public List<Student> selectStudentMessage(String filePath) throws IOException {


        List<Student> studentList = new ArrayList<>();

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String message;
        int lineNum = 0;

        while ((message = bufferedReader.readLine()) != null){
            Student student = new Student();
            String []studentArray = message.split(" ");

            if (lineNum == 0){
                lineNum++;
            } else {
                student.setStudent_id(studentArray[0]);
                student.setStudent_name(studentArray[1]);
                student.setAge(Integer.valueOf(studentArray[2]));
                if (studentArray[3].equals("男")){
                    student.setSex(1);
                } else student.setSex(0);

                student.setSchool(studentArray[4]);
                student.setAddress(studentArray[5]);
                studentList.add(student);
            }
        }
        bufferedReader.close();

        return studentList;
    }

    /**
     * 根据学号删除学生
     * @param id
     */
    public void deleteStudentById(String id){
        String sql = "delete from lessontraining.student where student_id = ?";
        crudUtils.delete(sql,id);
    }

    @Override
    public void updateStudentMessage(Student student) {
        String sql = "update lessontraining.student set " +
                "student_id = ?,student_name = ?,age = ?," +
                "sex = ?,school = ?,address = ?,deleteFlag = ?,info = ?," +
                "updateTime = ? where student_id = ?";
//        if (student.getStudent_id() != null)
//            sql += " student_id = ?,";
//        if (student.getStudent_name() != null)
//            sql += " student_name = ?,";
//        if (student.getAge() != null)
//            sql += " student_age = ?,";
//        if (student.getSex() != null)
//            sql += " sex = ?,";
//        if (student.getSchool() != null)
//            sql += " school = ?,";
//        if (student.getAddress() != null)
//            sql += " deleteFlag = ?,";
//        if (student.getInfo() != null)
//            sql += " info = ?,";
//        if (student.getUpdateTime() != null)
//            sql += " updateTime = ?,";
//        sql.substring(0,sql.length()-2);
//        sql += "where student_id = ?";

        CRUDUtils.update(sql,
                student.getStudent_id(),
                student.getStudent_name(),
                student.getAge(),
                student.getSex(),
                student.getSchool(),
                student.getAddress(),
                student.getDeleteFlag(),
                student.getInfo(),
                student.getUpdateTime(),
                student.getStudent_id());
    }
}
