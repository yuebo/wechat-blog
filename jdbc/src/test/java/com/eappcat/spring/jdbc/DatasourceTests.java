package com.eappcat.spring.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasourceTests {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void thirdBean2(){
        DataSource dataSource1=(DataSource) applicationContext.getBean("dataSource");
        DataSource dataSource2=(DataSource) applicationContext.getBean("testDatasource");
        Assert.assertNotEquals(dataSource1,dataSource2);
        Assert.assertNotNull(dataSource1);
        Assert.assertNotNull(dataSource2);
    }


}
