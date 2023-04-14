package com.njfu.edu.controller;

import com.njfu.edu.pojo.College;
import com.njfu.edu.service.CollegeService;
import com.njfu.edu.service.impl.CollegeServiceImpl;
import com.njfu.edu.utils.CRUDUtils;

import java.sql.Connection;
import java.util.List;

public class CollegeController {

    private CollegeService collegeService = new CollegeServiceImpl();

    public List<College> queryData() {
        return collegeService.queryAllCollege();
    }
}
