package com.njfu.edu.dao.impl;

import com.njfu.edu.dao.CollegeDao;
import com.njfu.edu.pojo.College;
import com.njfu.edu.utils.CRUDUtils;

import java.sql.Connection;
import java.util.List;

public class CollegeDaoImpl implements CollegeDao {

    @Override
    public List<College> queryData(Connection connection) {
        String sql = "select * from college order by id asc";
        return CRUDUtils.query(connection, College.class, sql,null);
    }
}
