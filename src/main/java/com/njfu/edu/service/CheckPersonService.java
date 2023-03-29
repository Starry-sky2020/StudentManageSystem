package com.njfu.edu.service;

import java.io.IOException;
import java.util.Map;

public interface CheckPersonService {
    /**
     * 用胡登录界面
     * @return
     * @throws IOException
     */
    Boolean UserLoginView(Map<String,String> map) throws IOException;

    /**
     *
     * @param map
     * @return
     * @throws IOException
     */
    Boolean ManagerLoginView(Map<String,String> map) throws IOException;
}
