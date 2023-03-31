package com.njfu.edu.service;

import com.njfu.edu.dao.impl.PagingDaoImpl;
import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagingServiceImplTest {

    @Test
    public void testpage() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Paging<Object> objectPaging = new Paging<>();
        PagingDaoImpl<Student> objectPagingDaoImpl = new PagingDaoImpl<>();
        objectPaging.setPageNum(1);
        objectPaging.setPageSize(10);
        Map<String,Object> map = new HashMap<>();
        map.put("age",22);
        map.put("name","李");

        objectPaging.setMap(map);
        objectPaging.setRecordTotal(objectPagingDaoImpl.selectItems(objectPaging));

        List<Student> list = objectPagingDaoImpl.selectMessage(objectPaging);
        for (Student student : list){
            System.out.println(student);
        }


    }
}
