package com.njfu.edu.dao.impl;

import com.njfu.edu.dao.StudentDao;
import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.utils.CRUDUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private CRUDUtils crudUtils = new CRUDUtils();
    public void insertStudent(Connection connection,Student student){
        String sql = "insert into lessonTraining.student(student_name,age,sex,school,address) " +
                "value(?,?,?,?,?)";
        crudUtils.insert(connection,sql,student.getStudent_name(),String.valueOf(student.getAge()),String.valueOf(student.getSex()),student.getSchool(),student.getAddress());
    }

    public Student selectStudentById(Connection connection, Long id){
        String sql = "select * from lessontraining.student where student_id = ?";
        Object params[] = {id};
        return CRUDUtils.query(connection,Student.class,sql,params).get(0);
    }

    public long selectItems(Connection connection,Paging paging) throws SQLException {

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

        long cnt = CRUDUtils.selectItems(connection,sqlItems,args);

        return cnt;
    }


    /**
     * 获取数据信息
     * 采用分页查询
     * @return
     */
    @Override
    public List<Student> selectStudentMessage(Connection connection,Paging paging){
        int page = paging.getPageNum()-1;
        int pageSize = paging.getPageSize();
        System.out.println(paging.getMap());
//        String sql = "select * from lessontraining.student, lessontraining.studentClass, where 1 = 1";
        String sql = "select lessontraining.student.student_id,lessontraining.student.student_name,\n" +
                "       lessontraining.student.age,lessontraining.student.sex,lessontraining.student.school,\n" +
                "       lessontraining.student.address,lessontraining.studentclass.studentclassName,\n" +
                "       lessontraining.college.collegeName\n" +
                "from lessontraining.student,lessontraining.studentclass,lessontraining.college\n" +
                "where lessontraining.studentclass.id = lessontraining.student.studentClassId\n" +
                "and lessontraining.studentclass.collegeId = lessontraining.college.id";
        List list = new ArrayList<>();
        Object[] args = null;
        if (paging.getMap() != null){
            if (paging.getMap().containsKey("age")){
                sql += " and age = ?";
                list.add(paging.getMap().get("age"));
            }
            if (paging.getMap().containsKey("name")){
                sql += " and lessontraining.student.student_name like concat('%',?,'%')";
                list.add( paging.getMap().get("name"));
            }
            if (paging.getMap().containsKey("collegeName")){
                sql += " and lessontraining.college.collegeName like concat('%',?,'%')";
                list.add(paging.getMap().get("collegeName"));
            }
            if (paging.getMap().containsKey("clazzName")){
                sql += " and lessontraining.studentclass.studentclassName like concat('%',?,'%')";
                list.add(paging.getMap().get("clazzName"));
            }

            //根据id 年龄 性别进行排序，并使用分页查询
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

            args = new Object[list.size()];
            System.out.println(paging.getMap());
//            System.out.println(list.get(0));
            for(int i = list.size()-1; i >= 0; i--){
                args[i] = list.get(i);
            }
        }
        sql += " limit "+page*pageSize+","+pageSize;
        System.out.println(sql);
        return CRUDUtils.query(connection,Student.class,sql,args);
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
                student.setStudent_id(Long.valueOf(studentArray[0]));
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
    public void deleteStudentById(Connection connection,String id){
        String sql = "delete from lessontraining.student where student_id = ?";
        CRUDUtils.delete(connection,sql,id);
    }

    @Override
    public void updateStudentMessage(Connection connection,Student student) {
        String sql = "update lessontraining.student set " +
                "student_name = ?,age = ?," +
                "sex = ?,school = ?,address = ?,deleteFlag = ?,info = ?," +
                "updateTime = ? where student_id = ?";

        CRUDUtils.update(connection,sql,
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
