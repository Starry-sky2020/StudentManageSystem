package com.njfu.edu.controller;

import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.njfu.edu.pojo.Ajax;
import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.pojo.StudentClass;
import com.njfu.edu.service.CollegeService;
import com.njfu.edu.service.StudentClassService;
import com.njfu.edu.service.StudentService;
import com.njfu.edu.utils.Tools;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private StudentClassService classService;

    @GetMapping(value = "/stulist/{pageNum}/{collegeName}/{clazzName}/{condition}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String studentList(@PathVariable("pageNum") Integer pageNum,
                            @PathVariable("collegeName") String collegeName,
                            @PathVariable("clazzName") String clazzName,
                            @PathVariable("condition") String condition,
                            HttpServletRequest request) throws IOException {

        Paging<Student> paging = new Paging<>();
        HttpSession session = request.getSession();
        Map map = new HashMap();

        if (!collegeName.equals("#")) {
            map.put("collegeName",collegeName);
            session.setAttribute("map_name",map);
        }
        if (!clazzName.equals("#")) {
            map.put("clazzName",clazzName);
            session.setAttribute("map_name",map);
        }
        boolean falg = false;
        if (!condition.equals("#")){
            if (condition.equals("1"))  falg=true;
            if (condition.equals("2")) map.put("key","2");
            if (condition.equals("3")) map.put("key","3");
            session.setAttribute("map",map);
        }

        //session域 记忆选择条件
        if (session.getAttribute("map") != null)
            map.putAll((Map) session.getAttribute("map"));
        if (session.getAttribute("map_name") != null)
            map.putAll((Map) session.getAttribute("map_name"));
        if (falg) map.clear();
        paging.setMap(map);

        if (!pageNum.equals("#")) paging.setPageNum(Integer.valueOf(pageNum));
        studentService.selectAllStudent(paging);

        request.setAttribute("college",collegeService.queryAllCollege());
        request.setAttribute("clazz", classService.queryStudentClass());
        request.setAttribute("paging",paging);

        Ajax ajax = new Ajax();
        ajax.setListStu(paging.getList());
        ajax.setState(1);
        ajax.setPaging(paging);
        ajax.setListCollege(collegeService.queryAllCollege());
        ajax.setListClazz(classService.queryStudentClass());
        String jsonString = JSON.toJSONString(ajax);

        return jsonString;
    }

    @PostMapping("/delstu/{id}")
    public void deleteStudent(@PathVariable("id") String id) throws IOException {
        studentService.DeleteStudentById(id);
    }

    @GetMapping("/selectstuid/{id}")
    public String selectStudentById(@PathVariable("id") String id, HttpServletRequest request) throws IOException {
        Student student = studentService.selectStudetById(Long.parseLong(id));
        request.setAttribute("updateStu",student);
        return "manager-updateStu";
    }

    @PostMapping("/updatestu/{stuId}/{stuName}/{stuAge}/{stuSex}/{stuSch}/{stuAdd}")
    public void updateStudentById(@PathVariable("stuId") String stuId,
                                  @PathVariable("stuName") String stuName,
                                  @PathVariable("stuAge") Integer stuAge,
                                  @PathVariable("stuSex") Integer stuSex,
                                  @PathVariable("stuSch") String stuSch,
                                  @PathVariable("stuAdd") String stuAdd,
                                  @PathVariable("stuInfo") String stuInfo){
        Student student = new Student();
        student.setStudent_id(Long.valueOf(stuId));
        student.setStudent_name(stuName);
        student.setAge(stuAge);
        student.setSex(stuSex);
        student.setSchool(stuSch);
        student.setAddress(stuAdd);
        student.setInfo(stuInfo);
        student.setDeleteFlag(1);
        System.out.println(student);
        try {
            student.setUpdateTime(Tools.getCurrentSystemDate());
            studentService.changeStudentInfo(student);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/addstu/{stuName}/{stuAge}/{stuSex}/{stuSch}/{stuAdd}/{stuClazz}")
    public void addStudent(@PathVariable("stuName") String stuName,
                           @PathVariable("stuAge") Integer stuAge,
                           @PathVariable("/stuSex") Integer stuSex,
                           @PathVariable("/stuSch") String stuSch,
                           @PathVariable("/stuAdd") String stuAdd,
                           @PathVariable("/stuClazz") String stuClazz) throws IOException {
        Student student = new Student();
        student.setStudent_name(stuName);
        student.setAge(stuAge);
        if (stuSex.equals("男")) student.setSex(1);
        else student.setSex(0);
        student.setSchool(stuSch);
        student.setAddress(stuAdd);
        student.setStudentClassId(Integer.valueOf(stuClazz));

        studentService.InsertStudentMessage(student);
    }

    @GetMapping(value = "/stu/data", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String dateView(){
        List<Student> students = studentService.selectStuInfo();
        List<StudentClass> studentClasses = classService.queryStudentClass();

        List<Map> maps = new ArrayList<>();

        for (int i = 0; i < studentClasses.size(); i++){
            int cnt = 0;
            for (int j = 0; j < students.size();j++){
                if (students.get(j).getStudentClassId().equals(studentClasses.get(i).getId())){
                    cnt++;
                }
            }
            Map map = new HashMap();
            map.put("name",studentClasses.get(i).getStudentclassName());
            map.put("value",cnt);
            maps.add(map);
        }

        //fastjson自身bug 将map转为json字符串时无分号解决方案
        String jsonString = JSON.toJSONString(maps, new NameFilter() {
            @Override
            public String process(Object object, String name, Object value) {
                return name;
            }
        });
        System.out.println(jsonString);
        return jsonString;
    }
}
