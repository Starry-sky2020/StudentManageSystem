package com.njfu.edu.dao.impl;

import com.njfu.edu.dao.StudentDao;
import com.njfu.edu.pojo.CheckStudentFormatResult;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.utils.CRUDUtils;
import com.njfu.edu.utils.Tools;

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
     * 检查导入的学生信息格式
     * @param filePath
     * @return
     */
    public CheckStudentFormatResult checkStudentFormat(String filePath) {
        CheckStudentFormatResult result = new CheckStudentFormatResult();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = bufferedReader.readLine();

            while(line != null){
                line = bufferedReader.readLine();
                if(line != null){
                    // 判断检查
                    String[] data = line.split(" ");
                    if(data.length != 6){
                        result.getFailData().add(line);
                        result.getFailCode().add(CheckStudentFormatResult.CODE_1);
                        continue;
                    }
                    if(!Tools.checkString(data[0])){
                        result.getFailData().add(line);
                        result.getFailCode().add(CheckStudentFormatResult.CODE_2);
                        continue;
                    }
                    if(!Tools.checkInt(data[2])){
                        System.out.println("hhh"+data[2]);
                        result.getFailData().add(line);
                        result.getFailCode().add(CheckStudentFormatResult.CODE_3);
                        continue;
                    }

                    //检查重复的数据
                    if (checkRepeatData(selectStudentMessage(), data[0])){
                        result.getExistData().add(line);
                        result.getFailCode().add(CheckStudentFormatResult.CODE_4);
                        continue;
                    }

                    result.getSuccessData().add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(result.getFailData().size() == 0 && result.getExistData().size() == 0){
            result.setResult(true);
            result.setMessage("检查成功");
        }
        return result;
    }

    public Boolean checkRepeatData(List<Student> list, String id){
        List<Student> studentList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getStudent_id().equals(id)){
                return true;
            }
        }

        return false;
    }

    /**
     * 根据学号删除学生
     * @param id
     */
    public void deleteStudentById(String id){
        String sql = "delete from lessontraining.student where student_id = ?";
        crudUtils.delete(sql,id);
    }

    /**
     * 获取数据库数据条数
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public long getItemNums() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql = "select count(*) from lessontraining.student";
        long query = CRUDUtils.selectItems(Student.class, sql,null);
        return query;
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
