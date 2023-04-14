package com.njfu.edu.service;

import com.njfu.edu.pojo.College;

import java.util.List;

public interface CollegeService {

    /**
     * 查询所有院
     * @return
     */
    List<College> queryAllCollege();
}
