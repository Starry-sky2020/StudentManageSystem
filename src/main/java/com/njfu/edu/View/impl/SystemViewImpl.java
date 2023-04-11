package com.njfu.edu.View.impl;

import com.njfu.edu.controller.StudentController;
import com.njfu.edu.pojo.*;
import com.njfu.edu.utils.Tools;
import com.njfu.edu.View.SystemView;
import com.njfu.edu.controller.UserController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SystemViewImpl implements SystemView {

    private StudentController studentController = new StudentController();
    private UserController userController = new UserController();

    public Integer MainUI(){
        int choice;

        while (true){
            System.out.println("***************使用学生管理系统1.0V***************");
            System.out.println("1.登录系统");
            System.out.println("2.注册信息");
            System.out.println("3.退出系统");
            System.out.println("请输入您的选择：");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();

            if (!Tools.checkInt(str))
                System.out.println("请输入数字");
            else if (str.compareTo("1") < 0 || str.compareTo("3") > 0){
                System.out.println("请输入1-3的数字");
                choice = Integer.parseInt(str);
            } else {
                choice = Integer.valueOf(str);
                break;
            }

        }

        return choice;
    }

    @Override
    public Integer LoginUI() {
        Scanner scanner = new Scanner(System.in);
        String str;
        int choice;

        while (true){
            System.out.println("***************使用学生管理系统1.0V***************");
            System.out.println("1.管理员账号登录");
            System.out.println("2.用户账号登录");

            str = scanner.nextLine();
            if (!Tools.checkInt(str))
                System.out.println("请输入数字");
            else if (str.compareTo("1") < 0 || str.compareTo("2") > 0){
                System.out.println("请输入1-2的数字");
                choice = Integer.parseInt(str);
            } else {
                choice = Integer.valueOf(str);
                break;
            }
        }

        return choice;
    }

    /**
     * 用户登录数据获取
     * @return
     */
    @Override
    public Map<String,String> UserLogin() {

        System.out.println("***************使用学生管理系统1.0V***************");
        System.out.println("请输入用户名：");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("请输入用户免密：");
        String password = scanner.nextLine();

        Map<String, String> userMessageMap = new HashMap<>();
        userMessageMap.put("username",username);
        userMessageMap.put("password",password);

        return userMessageMap;

    }

    /**
     * 管理员登录
     * @return
     */
    @Override
    public Map<String, String> ManagerLogin() {

        System.out.println("***************使用学生管理系统1.0V***************");
        System.out.println("请输入管理员姓名：");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("请输入管理员免密：");
        String password = scanner.nextLine();

        Map<String, String> userMessageMap = new HashMap<>();
        userMessageMap.put("managername",username);
        userMessageMap.put("password",password);

        return userMessageMap;
    }

    /**
     * 用户管理学生信息
     * @return
     */
    @Override
    public Integer ManageStudentSystemView() {

        String str;
        int choice;

        while (true){
            System.out.println("***************使用学生管理系统1.0V***************");
            System.out.println("1.查看所有学生信息");
            System.out.println("2.查询学生信息");
            System.out.println("3.修改学生信息");
            System.out.println("4.根据学号删除学生信息");
            System.out.println("5.按照学号进行排序");
            System.out.println("6.按照年龄进行排序");
            System.out.println("7.按照性别进行排序");
            System.out.println("8.导入学生信息");
            System.out.println("9.插入学生信息");
            System.out.println("10.退出系统");
            System.out.println("***************使用学生管理系统1.0V***************");

            Scanner scanner = new Scanner(System.in);
            str = scanner.nextLine();
            if (!Tools.checkInt(str))
                System.out.println("请输入数字");
            else if (Integer.valueOf(str) < 0 || Integer.valueOf(str) > 10){
                System.out.println("请输入1-10的数字");
            } else {
                choice = Integer.valueOf(str);
                break;
            }
        }

        return choice;
    }

    @Override
    public void ManageStudentMessage(int choice) throws IOException, ParseException, SQLException {
        while(true){
            if (choice == 1){
                Paging<Student> paging = new Paging<>();
                System.out.println("请输入查询页数");
                Scanner scanner = new Scanner(System.in);
                String s = scanner.nextLine();
//                Map<String,Object> map = new HashMap<>();
//                map.put("age",22);
//                map.put("name","李");
//                paging.setMap(map);
                int pageNum = Integer.valueOf(s);
                paging.setPageNum(pageNum);

                studentController.selectAllStudent(paging);
                List<Student> studentList = paging.getList();

                for (Student student : studentList)
                    System.out.println(student);
            } else if (choice == 2) {
                System.out.println("请输入要查询学生的学号");
                Scanner scanner = new Scanner(System.in);
                String id = scanner.nextLine();
                Student student = studentController.selectStudetById(Long.parseLong(id));
                System.out.println(student);
            } else if (choice == 3) {
                System.out.println("请输入要修改的学生信息:");
                Scanner scanner = new Scanner(System.in);
                System.out.print("学号：");
                String id = scanner.nextLine();
                System.out.print("姓名：");
                String name = scanner.nextLine();
                System.out.print("年龄：");
                String age = scanner.nextLine();
                System.out.print("性别：");
                String sex = scanner.nextLine();
                System.out.print("学校：");
                String school = scanner.nextLine();
                System.out.print("地址：");
                String address = scanner.nextLine();

                int f = 0;
                if (sex.equals("男")){
                    f = 1;
                }
                Student student = new Student();
                student.setStudent_id(id);
                student.setStudent_name(name);
                student.setAge(Integer.valueOf(age));
                student.setSex(f);
                student.setSchool(school);
                student.setAddress(address);

                studentController.UpdateStudentById(student);
            } else if (choice == 4) {
                System.out.println("请输入要删除的学生信息:");
                Scanner scanner = new Scanner(System.in);
                System.out.print("学号：");
                String id = scanner.nextLine();
                studentController.DeleteStudentById(id);
            } else if (choice == 5) {
                Paging<Student> paging = new Paging<>();
                Map<String,Object> map = new HashMap<>();
                map.put("key","1");
                paging.setMap(map);
                List<Student> studentList = studentController.SortByStudetId(paging);
                System.out.println(studentList);
            } else if (choice == 6) {
                Paging<Student> paging = new Paging<>();
                Map<String,Object> map = new HashMap<>();
                map.put("key","2");
                paging.setMap(map);
                List<Student> studentList = studentController.SortByStudetId(paging);
                System.out.println(studentList);
            } else if (choice == 7) {
                Paging<Student> paging = new Paging<>();
                Map<String,Object> map = new HashMap<>();
                map.put("key","3");
                paging.setMap(map);
                List<Student> studentList = studentController.SortByStudetId(paging);
                System.out.println(studentList);
            } else if (choice == 8) {
                System.out.println("导入数据文件的路径:");
//                Scanner scanner = new Scanner(System.in);
//                String path = scanner.nextLine();
                //测试 写死路径
                ImportResult importResult = studentController.ImportStudentMessage("src/main/resources/test.txt");
                System.out.println(importResult);
            } else if (choice == 9) {
                System.out.println("请输入要插入的学生信息:");
                Scanner scanner = new Scanner(System.in);
                System.out.print("学号：");
                String id = scanner.nextLine();
                System.out.print("姓名：");
                String name = scanner.nextLine();
                System.out.print("年龄：");
                String age = scanner.nextLine();
                System.out.print("性别：");
                String sex = scanner.nextLine();
                System.out.print("学校：");
                String school = scanner.nextLine();
                System.out.print("地址：");
                String address = scanner.nextLine();

                int f = 0;
                if (sex.equals("男")){
                    f = 1;
                }

                Student student = new Student(id, name, Integer.valueOf(age), f, school, address);
                try {
                    student.setUpdateTime(Tools.getCurrentSystemDate());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                studentController.InsertStudentMessage(student);
            } else if (choice == 10) {
                studentController.BackwardSystem();
            }

            System.out.println("请输入选择：");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
        }

    }

    /**
     * 用户注册
     */
    @Override
    public Map<String,String> UserSubmit() {
        Map<String, String> hashMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入注册用户的姓名：");
        String username = scanner.nextLine();
        System.out.println("请输入注册用户的密码：");
        String password = scanner.nextLine();

        hashMap.put("username",username);
        hashMap.put("password",password);

        return hashMap;
    }

    @Override
    public Integer ManageUserView() {
        String str;
        int choice;

        while (true){
            System.out.println("***************学生信息管理系统管理员特权界面1.0V***************");
            System.out.println("1.管理学生信息");
            System.out.println("2.管理用户信息");
            System.out.println("3.创建管理账户");
            System.out.println("4.退出系统");

            Scanner scanner = new Scanner(System.in);
            str = scanner.nextLine();

            if (!Tools.checkInt(str))
                System.out.println("请输入数字");
            else if (str.compareTo("1") < 0 || str.compareTo("4") > 0){
                System.out.println("请输入1-4的数字");
                choice = Integer.parseInt(str);
            } else {
                choice = Integer.valueOf(str);
                break;
            }

        }

        return choice;
    }

    @Override
    public Integer ManageUserMessageView() {

        String str;
        int choice;
        while (true){
            System.out.println("***************学生信息管理系统管理员特权界面1.0V***************");
            System.out.println("1.查询所有用户");
            System.out.println("2.删除用户");
            System.out.println("3.返回上一层");
            Scanner scanner = new Scanner(System.in);

            str = scanner.nextLine();
            if (!Tools.checkInt(str))
                System.out.println("请输入数字");
            else if (str.compareTo("1") < 0 || str.compareTo("3") > 0){
                System.out.println("请输入1-3的数字");
                choice = Integer.parseInt(str);
            } else {
                choice = Integer.valueOf(str);
                break;
            }
        }

        return choice;
    }

    @Override
    public void ManageUserMessage(Integer choice) throws IOException {
        String str;
        Scanner scanner = new Scanner(System.in);

        while(true){
            if (choice == 1){
                List<User> userList = userController.selectAllUser();
                System.out.println(userList);
            } else if (choice == 2) {
                System.out.println("请输入删除用户Id");
                String id = scanner.nextLine();
                userController.deleteUserById(id);
            } else if (choice == 3) {
                ManageUserView();
            }
            str = scanner.nextLine();
            if (!Tools.checkInt(str))
                System.out.println("请输入数字");
            else if (str.compareTo("1") < 0 || str.compareTo("3") > 0){
                System.out.println("请输入1-3的数字");
                choice = Integer.parseInt(str);
            } else {
                choice = Integer.valueOf(str);
                break;
            }
        }
    }

    @Override
    public Manager createManager() {

        System.out.println("请输入创建的管理员信息");
        Manager manager = new Manager();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入管理员姓名：");
        String managerName = scanner.nextLine();
        System.out.println("请输入管理员密码：");
        String password = scanner.nextLine();

        manager.setManager_name(managerName);
        manager.setPassword(password);
        return manager;
    }
}
