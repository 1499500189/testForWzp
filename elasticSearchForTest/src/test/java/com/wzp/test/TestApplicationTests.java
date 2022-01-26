package com.wzp.test;

import com.wzp.test.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestApplicationTests {
    //注入 ElasticsearchRestTemplate
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private ProductDao productDao;
    @Test
    void contextLoads() {
    }
    @Test
    public  void  createIndex(){

        System.out.println("创建索引");
    }
    @Test
    public void deleteIndex(){
        //创建索引，系统初始化会自动创建索引
        boolean flg = elasticsearchRestTemplate.deleteIndex(Product.class);
        System.out.println("删除索引 = " + flg);

    }


}
