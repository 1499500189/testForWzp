package com.wzp.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author
 * @date 2022 年 01 月 19 日
 */
public class ESTest_Doc_Insert {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        //插入数据
        IndexRequest request = new IndexRequest();
        request.index("user").id("1001");
        User user = new User();
        user.setAge(99);
        user.setName("zhangsan");
        user.setSex("男");
        //向es中插入数据，必须将数据转换为JSON格式
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        request.source(userJson, XContentType.JSON);
      //  IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("user").id("1001");
        updateRequest.doc(XContentType.JSON,"sex","女");
      //  UpdateResponse response = esClient.update(updateRequest, RequestOptions.DEFAULT);

        GetRequest getRequest =new GetRequest();
        getRequest.index("user").id("1001");
        GetResponse response = esClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
        System.out.println(response.getId());



    }
}
