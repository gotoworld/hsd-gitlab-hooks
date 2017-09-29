package com.hsd.gitlab.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;


@Configuration
@EnableTransactionManagement(order = 2)
@MapperScan(basePackages = {"com.hsd.gitlab.*.dao"})
public class MybatisPlusConfig {

    @Autowired
    DruidProperties druidProperties;

    
    private DruidDataSource dataSourceDevops(){
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    
    @Bean
    @ConditionalOnProperty(prefix = "devops", name = "muti-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
        return dataSourceDevops();
    }

    
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
