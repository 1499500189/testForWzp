package com.wzp.test.dao;

import com.wzp.test.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @date 2022 年 01 月 20 日
 */
@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {


}
