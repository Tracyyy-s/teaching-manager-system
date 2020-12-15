package com.gwy.manager.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/1 16:46
 */
@Configuration
@PropertySource({"classpath:db-config.properties"})
public class DataBaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "db")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置Druid的监控
     * 配置后台的servlet
     * @return  servlet
     */
    public ServletRegistrationBean<Servlet> statViewServlet() {
        ServletRegistrationBean<Servlet> servletRegistrationBean =
                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        Map<String, String> map = new HashMap<>(5);
        map.put("loginUsername","admin");
        map.put("loginPassword", "123456");

        //默认允许所有
        map.put("allow","127.0.0.1");

        servletRegistrationBean.setInitParameters(map);
        return servletRegistrationBean;
    }

    /**
     * 配置后台的filter
     * @return  filter
     */
    public FilterRegistrationBean<Filter> webStatFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        Map<String, String> map = new HashMap<>(5);
        bean.setInitParameters(map);
        map.put("exclusions","*.js,*.css,/druid/*");
        bean.setUrlPatterns(Collections.singletonList("/*"));
        return bean;
    }

}
