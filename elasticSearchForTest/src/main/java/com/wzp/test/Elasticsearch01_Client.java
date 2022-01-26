package com.wzp.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.transport.TransportClient;

import java.io.IOException;

/**
 * @author
 * @date 2022 年 01 月 18 日
 */
public class Elasticsearch01_Client {
        public static void main(String[] args) throws IOException {
            //创建ES客户端
            RestHighLevelClient esClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost",9200,"http"))
            );
            //创建索引
            CreateIndexRequest request = new CreateIndexRequest("user");
            CreateIndexResponse createIndexResponse = esClient.indices().create(request, RequestOptions.DEFAULT);
            boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
            //响应状态
            System.out.println(shardsAcknowledged+"索引操作");

            //关闭ES客户端
            esClient.close();

        }
}
