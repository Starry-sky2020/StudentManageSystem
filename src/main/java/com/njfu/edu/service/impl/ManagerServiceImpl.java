package com.njfu.edu.service.impl;

import com.njfu.edu.mapper.ManagerMapper;
import com.njfu.edu.mapper.OpreationLogMapper;
import com.njfu.edu.pojo.Manager;
import com.njfu.edu.pojo.SubmitResult;
import com.njfu.edu.service.ManagerService;
import com.njfu.edu.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper mapper;
    @Autowired
    private OpreationLogMapper logMapper;
    @Autowired
    private SubmitResult submitResult;

    @Override
    public SubmitResult createManger(Manager manager) throws IOException {
        try {
            List<Manager> managerList = mapper.selectManagerMessage();
            for (int i = 0; i < managerList.size(); i++) {
                if (manager.getManager_name().equals(managerList.get(i).getManager_name())) {
                    submitResult.setResult(false);
                    submitResult.setMessage("管理员已存在，请重新输入创建信息");
                    submitResult.setCode(SubmitResult.ERROR_CODE_3);
                    return submitResult;
                }
            }

            logMapper.insert(Tools.getOpreationLog("创建管理员", 1, "无"));

            submitResult.setResult(true);
            submitResult.setMessage("创建管理员成功");
            submitResult.setCode(SubmitResult.ERROR_CODE_4);
            mapper.insertManager(manager);

            return submitResult;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Long selectManagerIdByPhone(String phone) {
        Long aLong = mapper.selectManagerIdByPhone(phone);
        return aLong;
    }
}