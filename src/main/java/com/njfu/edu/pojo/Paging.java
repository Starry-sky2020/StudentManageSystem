package com.njfu.edu.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Paging<T> {

    //记录总条数
    private long recordTotal;
    //每页最大记录条数
    private Integer pageSize = 5;
    //总页数
    private Integer pageTotal;
    //开始记录条数
    private Integer startNum;
    //当前页数
    private Integer pageNum = 1;
    //分页的页面数据
    private List<T> list;
    //参数
    private Map<String,Object> map;

    public long getPageTotal() {
        long t = recordTotal / pageSize;
        if (recordTotal % pageSize != 0)
            t++;
        return t;
    }
}
