package com.njfu.edu.utils;

import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.pojo.User;
import org.junit.Test;

public class CRUDUtilsTest {

    @Test
    public void testInsert(){
        CRUDUtils<Object> objectCRUDUtils = new CRUDUtils<>();
        objectCRUDUtils.insert("insert into lessontraining.student(student_name,age,sex,school,address)" +
                "value(?,?,?,?,?)","杜牧",33,true,"北京大学","北京");
    }

    @Test
    public void tesdelete(){
        CRUDUtils<Object> objectCRUDUtils = new CRUDUtils<>();
        objectCRUDUtils.insert("delete from lessontraining.student where student_id = ?",24);
    }

    @Test
    public void testquery(){
        CRUDUtils<Object> objectCRUDUtils = new CRUDUtils<>();
        System.out.println(objectCRUDUtils.query(User.class, "select * from lessontraining.user"));
    }
}
