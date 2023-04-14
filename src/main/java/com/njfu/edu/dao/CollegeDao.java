package com.njfu.edu.dao;

import com.njfu.edu.pojo.College;

import java.sql.Connection;
import java.util.List;

public interface CollegeDao {

    /**
     * 查询学院
     * @param connection
     * @return
     */
    List<College> queryData(Connection connection);

}
