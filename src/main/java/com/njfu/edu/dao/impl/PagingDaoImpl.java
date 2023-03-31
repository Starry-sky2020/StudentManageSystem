package com.njfu.edu.dao.impl;

import com.njfu.edu.dao.PagingDao;
import com.njfu.edu.pojo.Paging;
import com.njfu.edu.pojo.Student;
import com.njfu.edu.utils.CRUDUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagingDaoImpl<T> implements PagingDao {

    private Paging paging = new Paging();

    /**
     * 查询数据条数
     * @param paging
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public long selectItems(Paging paging) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {

        String sqlItems = "select count(*) from lessontraining.student where 1 = 1";

        List list = new ArrayList<>();
        if (paging.getMap().containsKey("age")){
            sqlItems += " and age = ?";
            list.add( paging.getMap().get("age"));
        }
        if (paging.getMap().containsKey("name")){
            sqlItems += " and student_name like concat('%',?,'%');";
            list.add(paging.getMap().get("name"));
        }

        Object[] args = new Object[list.size()];
        for(int i = 0; i < list.size(); i++){
            args[i] = list.get(i);
        }
        long cnt = CRUDUtils.selectItems(Student.class,sqlItems,args);

        return cnt;
    }

    /**
     * 获取数据信息
     * @param paging
     * @return
     */
    public List<Student> selectMessage(Paging paging){
        int page = paging.getPageNum()-1;
        int pageSize = paging.getPageSize();
        String sql = "select * from lessontraining.student where 1 = 1";

        List<T> list = new ArrayList<>();
        if (paging.getMap().containsKey("age")){
            sql += " and age = ?";
            list.add((T) paging.getMap().get("age"));
        }
        if (paging.getMap().containsKey("name")){
            sql += " and student_name like concat('%',?,'%')";
            list.add((T) paging.getMap().get("name"));
        }
        sql += "limit "+page+","+pageSize;

        Object[] args = new Object[list.size()];
        for(int i = 0; i < list.size(); i++){
            args[i] = list.get(i);
        }

        return CRUDUtils.query(Student.class,sql,args);
    }
}
