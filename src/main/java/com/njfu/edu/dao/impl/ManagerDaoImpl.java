package com.njfu.edu.dao.impl;

import com.mchange.v1.cachedstore.CachedStore;
import com.njfu.edu.dao.ManagerDao;
import com.njfu.edu.pojo.Manager;
import com.njfu.edu.utils.CRUDUtils;

import java.sql.Connection;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {

    /**
     * 查询管理员信息
     * 主要用于账户匹配
     * @return
     */
    public List<Manager> selectManagerMessage(Connection connection) {
        String sql = "select * from lessontraining.manager";
        List<Manager> managerList = CRUDUtils.query(connection,Manager.class, sql, null);
        return managerList;
    }

    public void insertManager(Connection connection,Manager manager){
        String sql = "insert into lessontraining.manager(manager_name,password,remarks)" +
                "value(?,?,?)";
        CRUDUtils.insert(connection,sql, manager.getManager_name(),manager.getPassword(),manager.getRemarks());
    }

    /**
     * 根据管理员手机号获取id
     * 记录系统运行日志使用
     * @param connection
     * @param phone
     * @return
     */
    @Override
    public Long selectManagerIdByPhone(Connection connection, String phone) {
        String sql = "select manager_id from lessontraining.manager where password = ?";
        List<Manager> query = CRUDUtils.query(connection, Manager.class, sql, phone);
        return query.get(0).getManager_id();
    }
}
