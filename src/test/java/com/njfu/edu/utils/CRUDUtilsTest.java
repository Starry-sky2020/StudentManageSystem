package com.njfu.edu.utils;

import org.junit.Test;

import java.sql.SQLException;

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
    public void testquery() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {

    }
}
