package com.njfu.edu.dao.impl;

import com.njfu.edu.dao.ManagerDao;
import com.njfu.edu.pojo.Manager;
import com.njfu.edu.utils.CRUDUtils;

import java.util.List;

public class ManagerDaoImpl implements ManagerDao {

    private CRUDUtils crudUtils = new CRUDUtils();
    /**
     * 查询管理员信息
     * 主要用于账户匹配
     * @return
     */
    public List<Manager> selectManagerMessage() {

        String sql = "select * from lessontraining.manager";
        List<Manager> managerList = CRUDUtils.query(Manager.class, sql, null);

        return managerList;
    }

    public void insertManager(Manager manager){
        String sql = "insert into lessontraining.manager(manager_name,password)" +
                "value(?,?)";
        crudUtils.insert(sql, manager.getManager_name(),manager.getPassword());
    }
}
