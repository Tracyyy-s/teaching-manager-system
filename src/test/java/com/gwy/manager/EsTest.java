package com.gwy.manager;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.io.IOException;

/**
 * @author Tracy
 * @date 2020/12/16 21:32
 */
@SpringBootTest
public class EsTest {

//    //@Autowired
//    private ElasticsearchRestTemplate restTemplate;
//
//    //@Autowired
//    private RestHighLevelClient client;
//
//    //@Test
//    void test01() throws IOException {
//        CreateIndexRequest request = new CreateIndexRequest("tracy_index");
//        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
//
//        System.out.println(response);
//    }
//
//    //@Test
//    void test02() throws IOException {
//
//        GetIndexRequest request = new GetIndexRequest();
//        request.indices("tracy_index");
//        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
//        System.out.println(exists);
//    }
//
//    //@Test
//    void test03() throws IOException {
//
//        DeleteIndexRequest request = new DeleteIndexRequest("tracy_index");
//        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
//
//        System.out.println(delete.isAcknowledged());
//    }
}
