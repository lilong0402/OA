package com.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author 李龙
 * @Date 2023/2/27
 * @Descrition Druid
 */
public class DruidDataSourceFactory extends UnpooledDataSourceFactory {

    public DruidDataSourceFactory(){
        this.dataSource=new DruidDataSource();
    }

    /**
     * 获取数据源
     * @return DataSource
     */
    @Override
    public DataSource getDataSource() {
        try {
            ((DruidDataSource)this.dataSource).init();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return this.dataSource;
    }
}
