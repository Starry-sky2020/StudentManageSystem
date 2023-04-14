package com.njfu.edu.service.impl;

import com.njfu.edu.dao.CollegeDao;
import com.njfu.edu.dao.impl.CollegeDaoImpl;
import com.njfu.edu.pojo.College;
import com.njfu.edu.service.CollegeService;
import com.njfu.edu.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

public class CollegeServiceImpl implements CollegeService {

    private CollegeDao collegeDao = new CollegeDaoImpl();
    @Override
    public List<College> queryAllCollege() {
        Connection connection = JDBCUtils.getConnection();
        List<College> colleges = collegeDao.queryData(connection);
        JDBCUtils.connRelease(connection);
        return colleges;
    }
}
