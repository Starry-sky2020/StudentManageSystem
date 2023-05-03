package com.njfu.edu.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Ajax<T> {

    //业务逻辑验证
    public Integer state;
    //后端传输的数据 列表形式
    public List listStu;

    public List listCollege;
    public List listClazz;

    public List listUser;
    //后端传输的数据 对象形式
    public T obj;
    //分页
    public Paging paging;
}
