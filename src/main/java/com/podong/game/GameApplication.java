package com.podong.game;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
@MapperScan("com.podong.game")
@SpringBootApplication
public class GameApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GameApplication.class);
    }

    @Autowired
    public ApplicationContext applicationContext;
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        Resource[] arrResource = null;
        arrResource = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/**/*.xml");
        sqlSessionFactoryBean.setMapperLocations(arrResource);
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.getObject().getConfiguration().setDefaultFetchSize(10000);
        return sqlSessionFactoryBean.getObject();
    }
}
