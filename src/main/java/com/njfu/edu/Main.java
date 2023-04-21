package com.njfu.edu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main( String[] args ) throws IOException {
        Map map = new HashMap();
        map.put("杨帅领",1);
        map.put("李白",2);

        System.out.println(map.get("李白"));
    }
}
