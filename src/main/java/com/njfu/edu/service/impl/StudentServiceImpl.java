package com.njfu.edu.service.impl;

import com.njfu.edu.Main;
import com.njfu.edu.dao.LogDao;
import com.njfu.edu.dao.impl.LogDaoImpl;
import com.njfu.edu.dao.impl.StudentDaoImpl;
import com.njfu.edu.pojo.*;
import com.njfu.edu.service.StudentService;
import com.njfu.edu.utils.JDBCUtils;
import com.njfu.edu.utils.Tools;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class StudentServiceImpl implements StudentService {

    private StudentDaoImpl studentDao = new StudentDaoImpl();

    /**
     * 查询所有学生
     * 分页查询
     * @return
     * @throws IOException
     */
    @Override
    public void selectAllStudent(Paging paging) throws IOException{
        Connection connection = JDBCUtils.getConnection();
        LogDao logDao = new LogDaoImpl();
        OperationLog operationLog = new OperationLog();

        boolean autoCommit = false;
        boolean res = false;
        try {
            autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);

            long items = studentDao.selectItems(connection, paging);
            paging.setRecordTotal(items);

            List<Student> students = studentDao.selectStudentMessage(connection, paging);
            paging.setList(students);

            operationLog.setOperationMsg("分页查询学生信息");
            operationLog.setDeleteFlag(1);
            //管理员和用户都具有管理学生的功能，判断执行对象是谁
            if (Main.is_manager) operationLog.setUserId(Main.managerId);
            if (Main.is_user) operationLog.setUserId(Main.userId);
            operationLog.setInfo("无");
            operationLog.setUpdateTime(Tools.getCurrentSystemDate());

            logDao.insert(connection, operationLog);

            res = true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            if (res){
                try {
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            try {
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            JDBCUtils.connRelease(connection);
        }
    }

    /**
     * 根据学号查询学生信息
     * @param paging
     * @return
     * @throws IOException
     */
    @Override
    public Student selectStudetById(Paging paging) throws IOException {
        Connection connection = JDBCUtils.getConnection();
        return studentDao.selectStudentMessage(connection,paging).get(0);
    }


    /**
     * 根据学生Id排序
     * 冒泡排序
     * @return
     * @throws IOException
     */
    @Override
    public List<Student> SortByStudetId(Paging paging) throws IOException {
        Connection connection = JDBCUtils.getConnection();
        return studentDao.selectStudentMessage(connection,paging);
    }

    /**
     * 检查导入的学生信息格式
     * @param filePath
     * @return
     */
    public CheckStudentFormatResult checkStudentFormat(String filePath) {
        CheckStudentFormatResult result = new CheckStudentFormatResult();
        Connection connection = JDBCUtils.getConnection();

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
                        result.getFailData().add(line);
                        result.getFailCode().add(CheckStudentFormatResult.CODE_3);
                        continue;
                    }
                    //获取全部数据
                    Paging<Student> paging = new Paging<>();
                    List<Student> list = new ArrayList<>();
                    long items = studentDao.selectItems(connection,new Paging());
                    paging.setRecordTotal(items);

                    for (int i = 1; i <= paging.getPageTotal(); i++){
                        paging.setPageNum(i);
                        list.addAll(studentDao.selectStudentMessage(connection,paging));
                    }
                    //检查重复的数据
                    if (checkRepeatData(list, data[0])){
                        result.getExistData().add(line);
                        result.getFailCode().add(CheckStudentFormatResult.CODE_4);
                        continue;
                    }

                    result.getSuccessData().add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getStudent_id().equals(id))
                return true;
        return false;
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
        Connection connection = JDBCUtils.getConnection();

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
        CheckStudentFormatResult checkStudentFormatResult = checkStudentFormat(path);
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
            Paging<Student> paging = new Paging<>();
            Map<String,Object> map = new HashMap<>();
            map.put("student_id",data.get(i).getStudent_id());
            paging.setMap(map);
            if (selectStudetById(paging) == null){
                importResult.getSuccessData().add(data.get(i));
            } else {
                importResult.getExistData().add(data.get(i));
            }
        }

        for (Student student : data){
            studentDao.insertStudent(connection,student);
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
        Connection connection = JDBCUtils.getConnection();
        studentDao.insertStudent(connection,student);
    }

    /**
     * 在列表中将元素删除
     * 重新写入到文件中
     * @param id
     * @throws IOException
     */
    @Override
    public void DeleteStudentById(String id) throws IOException {
        Connection connection = JDBCUtils.getConnection();
        studentDao.deleteStudentById(connection,id);
    }

    /**
     * 退出系统
     */
    @Override
    public void BackwardSystem() {
        System.exit(0);
    }

    @Override
    public boolean changeStudentInfo(Student student) throws ParseException {
        Connection connection = JDBCUtils.getConnection();
        OperationLog operationLog = new OperationLog();
        LogDao logDao = new LogDaoImpl();

        boolean res = false;
        boolean autoCommit = false;

        try {
            autoCommit = connection.getAutoCommit();
            //关闭自动提交事务
            connection.setAutoCommit(false);
            // 1检查该学号的学生是否存在、查不到直接报错
            Student stu = studentDao.selectStudentById(connection, Long.valueOf(student.getStudent_id()));

            if (stu != null){
                //更新学生信息
                studentDao.updateStudentMessage(connection,student);
                //日志信息
                operationLog.setOperationMsg("更新学生信息");
                operationLog.setDeleteFlag(1);
                //管理员和用户都具有管理学生的功能，判断执行对象是谁
                if (Main.is_manager) operationLog.setUserId(Main.managerId);
                if (Main.is_user) operationLog.setUserId(Main.userId);

                operationLog.setInfo("无");
                operationLog.setUpdateTime(Tools.getCurrentSystemDate());
                //添加日志记录
                logDao.insert(connection,operationLog);
            } else return false; //更改信息为空

            res = true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            if (res){
                try {
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            try {
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            JDBCUtils.connRelease(connection);
        }

        return true;
    }
}
