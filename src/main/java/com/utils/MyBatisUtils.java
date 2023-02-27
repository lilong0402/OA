package com.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;

public class MyBatisUtils {
    //数据库连接池
    private static SqlSessionFactory  sqlSessionFactory;
    static {
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory =new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行查询操作
     * @param func 需要执行查询的代码块
     * @return 查询结果
     */
    //第一个泛型说明那个数据库，第二个反省是返回值
    public static Object executeQuery(Function<SqlSession,Object> func){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return func.apply(sqlSession);
    }

    /**
     * 执行增删改操作
     * @para func 需要执行的语句代码块
     * @return 更新操作的返回结果
     */
    public  static Object executeUpdates(Function<SqlSession,Object> func){
        SqlSession sqlSession = sqlSessionFactory.openSession(false);

        try {
            Object obj= func.apply(sqlSession);
            //手动提交事务
            sqlSession.commit();
            return obj;
        }catch (Exception e){
            sqlSession.rollback();
            throw e;
        }finally {
            sqlSession.close();
        }

    }
}
