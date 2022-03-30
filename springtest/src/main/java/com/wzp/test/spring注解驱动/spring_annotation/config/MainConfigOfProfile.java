package com.wzp.test.spring注解驱动.spring_annotation.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * profile :
 *        spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能；
 * 开发环境、测试环境、生产环境；
 * 数据源：（/A）（/B）（/C）；
 *
 * @Profile :指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下才能注册这个组件
 *   1)加了环境标识的bean， 只有这个环境被激活的时候才能注册到容器中，默认是default环境
 *      1使用命令行动态参数：在虚拟机参数位置加载 -Dspring.profile.active=test
 *      2使用代码激活某种环境
 *   2）可以写再类上 ，只有再某个环境的时候 整个配置类里面的所有配置才能开始生效
 *   3）没有标注环境标识的bean再任何环境下都加载的
 *
 */
@Configuration
public class MainConfigOfProfile  {
    @Bean("devDataSource")
    @Profile("dev")
    public DataSource dataSource(@Value("${db.password}") String  pwd ) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/wzp_test");
        dataSource.setDriverClass("com.wzp.jdbc.Driver");
        return dataSource;
    }
    @Bean("testDataSource")
    @Profile("test")
    public DataSource dataSourceTest() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/wzp_test");
        dataSource.setDriverClass("com.wzp.jdbc.Driver");
        return dataSource;
    }

}
