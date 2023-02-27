package com.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyBatisUtilsTest {
    @Test
    public  void teseCase1(){
        String str = (String) MyBatisUtils.executeQuery(sqlSession -> sqlSession.selectOne("test.sample"));
        System.out.println(str);
    }
}