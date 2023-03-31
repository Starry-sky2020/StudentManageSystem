package com.njfu.edu.utils;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

public class ToolTest {
    @Test
    public void testCurrentTime() throws ParseException {
        Timestamp currentSystemDate = Tools.getCurrentSystemDate();
        System.out.println(currentSystemDate);
    }
}
