package com.njfu.edu.servlet;

import com.njfu.edu.pojo.College;
import com.njfu.edu.service.CollegeService;
import com.njfu.edu.service.impl.CollegeServiceImpl;

import java.util.List;

public class CollegeServlet {

    private CollegeService collegeService = new CollegeServiceImpl();

    public List<College> queryData() {
        return collegeService.queryAllCollege();
    }
}
