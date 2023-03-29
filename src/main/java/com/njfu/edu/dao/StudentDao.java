package com.njfu.edu.dao;

import com.njfu.edu.pojo.CheckStudentFormatResult;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.utils.CRUDUtils;
import com.njfu.edu.utils.Tools;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private CRUDUtils crudUtils = new CRUDUtils();
    public void insertStudent(Student student){

        String sql = "insert into lessonTraining.student(student_name,age,sex,school,address) " +
                "value(?,?,?,?,?)";
        crudUtils.insert(sql,student.getStudent_name(),String.valueOf(student.getAge()),String.valueOf(student.isSex()),student.getSchool(),student.getAddress());
    }

    public List<Student> selectStudentMessage() throws IOException {

        String sql = "select * from lessontraining.student";
        List<Student> studentList = CRUDUtils.query(Student.class, sql, null);

//        try {
//            while (resultSet.next()){
//                Student student = new Student(String.valueOf(resultSet.getBigDecimal("student_id")),
//                        resultSet.getString("student_name"),
//                        resultSet.getInt("age"),
//                        resultSet.getBoolean("sex"),
//                        resultSet.getString("school"),
//                        resultSet.getString("address"));
//                studentList.add(student);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

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
                    student.setSex(true);
                } else student.setSex(false);

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
}
