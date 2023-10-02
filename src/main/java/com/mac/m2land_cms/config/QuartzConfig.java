package com.mac.m2land_cms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;


/**
 * The class QuartzConfig.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Configuration
public class QuartzConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;



    @Bean
    public SchedulerFactoryBean schedulerFactory(ApplicationContext applicationContext) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setApplicationContextSchedulerContextKey("applicationContext");
        schedulerFactory.setDataSource(dataSource());
        schedulerFactory.setJobFactory(new AutowireCapableBeanJobFactory(applicationContext));
        return schedulerFactory;
    }

    @Bean(name = "myDS")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}
