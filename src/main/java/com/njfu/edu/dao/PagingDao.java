package com.njfu.edu.dao;

import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;

import java.sql.SQLException;
import java.util.List;

public interface PagingDao {

    long selectItems(Paging paging) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    List<Student> selectMessage(Paging paging);
}
