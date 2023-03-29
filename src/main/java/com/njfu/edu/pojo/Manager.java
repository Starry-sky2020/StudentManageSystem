package com.njfu.edu.pojo;

import lombok.Data;

@Data
public class Manager {

    private String manager_id;
    private String manager_name;
    private String password;

    public Manager(String manager_id,String manager_name,String password){
        this.manager_id = manager_id;
        this.manager_name = manager_name;
        this.password = password;
    }

    public Manager(){

    }
}
