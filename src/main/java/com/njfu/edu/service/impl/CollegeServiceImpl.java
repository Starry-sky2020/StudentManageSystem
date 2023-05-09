package com.njfu.edu.service.impl;

import com.njfu.edu.mapper.CollegeMapper;
import com.njfu.edu.pojo.College;
import com.njfu.edu.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper mapper;

    @Override
    public List<College> queryAllCollege() {
        List<College> colleges = mapper.queryData();
        return colleges;
    }
}
