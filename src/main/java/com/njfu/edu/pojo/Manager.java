package com.njfu.edu.pojo;

import lombok.Data;

import java.io.Serializable;

//Serializable 序列化，为实现二级缓存
@Data
public class Manager implements Serializable {

    private Long manager_id;
    private String manager_name;
    private String password;
    private String remarks;

    public Manager(Long manager_id,String manager_name,String password){
        this.manager_id = manager_id;
        this.manager_name = manager_name;
        this.password = password;
    }

    public Manager(){

    }
}
