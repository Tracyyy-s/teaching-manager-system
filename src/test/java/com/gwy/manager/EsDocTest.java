package com.gwy.manager;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.elastic.SysLogString;
import com.gwy.manager.elastic.SysLogStringElasticRepository;
import com.gwy.manager.entity.SysLog;
import com.gwy.manager.mapper.SysLogMapper;
import com.gwy.manager.util.DateUtilCustom;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Tracy
 * @date 2020/12/16 22:50
 */
@SpringBootTest
public class EsDocTest {

//    @Autowired
//    private RestHighLevelClient client;
//
//    @Test
//    void test01() throws IOException {
//
//        SysLog sysLog = new SysLog();
//        sysLog.setId(2);
//        sysLog.setCreateTime(DateUtilCustom.getTime());
//
//        BulkRequest bulkRequest = new BulkRequest();
////        bulkRequest.timeout("2m");
//
//        for (int i = 0; i < 10; i++) {
//            sysLog = new SysLog();
//            sysLog.setId(i);
//            sysLog.setCreateTime(DateUtilCustom.getTime());
//            IndexRequest request = new IndexRequest("tracy_index");
//            request.id("1");
//            bulkRequest.add(
//                    request
//                            .source(JSONObject.toJSONStringWithDateFormat(sysLog, DateUtilCustom.TIME_PATTERN), XContentType.JSON)
//            );
//        }
//
//        BulkResponse responses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
//
//        System.out.println(responses.status());
//    }
//
//    @Test
//    void testGetDocument() throws IOException {
//        GetRequest request = new GetRequest("tracy_index", "1");
//        GetResponse response = client.get(request, RequestOptions.DEFAULT);
//
//        //打印文档的内容
//        System.out.println(response.getSourceAsString());
//        System.out.println(response);
//    }
//
//    @Test
//    void testQuery() throws IOException {
//
//        SearchRequest request = new SearchRequest("tracy_index");
//
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//        TermQueryBuilder termQuery = QueryBuilders.termQuery("id", "1");
//
//        sourceBuilder.query(termQuery);
//
//        request.source(sourceBuilder);
//
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//
//        for (SearchHit hit : response.getHits().getHits()) {
//            System.out.println(hit.getSourceAsMap());
//        }
//    }
//
//    @Test
//    void testDoc() {
//
//        SysLog sysLog = new SysLog();
//        for (int i = 0; i < 10; i++) {
//            sysLog.setId(1);
//            sysLog.setIp("1");
//            sysLog.setData("dd");
//        }
//        sysLog.setCreateTime(DateUtilCustom.getTime());
//
//    }
//
//
//    @Autowired
//    private SysLogMapper logMapper;
//
//    @Autowired
//    private SysLogStringElasticRepository stringRepository;
//
//    @Test
//    void test() {
//        List<SysLog> sysLogs = logMapper.selectAll();
//
//        for (SysLog sysLog : sysLogs) {
//            stringRepository.save(new SysLogString(sysLog.getId(), JSONObject.toJSONStringWithDateFormat(sysLog, DateUtilCustom.TIME_PATTERN)));
//        }
//
//        Page<SysLogString> all = stringRepository.findAll(PageRequest.of(1, 10));
//
//        System.out.println(all);
//    }
//
//    @Test
//    void testHighLight() throws IOException {
//
//        SearchRequest searchRequest = new SearchRequest("sys_log");
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//        sourceBuilder.from(0);
//        sourceBuilder.size(10);
//
//        //精确匹配
//        WildcardQueryBuilder wildcardQuery = QueryBuilders.wildcardQuery("content", "*操*");
//        sourceBuilder.query(wildcardQuery);
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//
//
//        String prefix = "<font color='#dd4b39'>";
//        String suffix = "</font>";
//        //高亮显示
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("content");
//        highlightBuilder.requireFieldMatch(false);  //多个单词高亮的话，要把这个设置为trues
//        highlightBuilder.preTags(prefix);
//        highlightBuilder.postTags(suffix);
//        sourceBuilder.highlighter(highlightBuilder);
//
//        //执行搜索
//        searchRequest.source(sourceBuilder);
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
////        System.out.println(Arrays.toString(searchResponse.getHits().getHits()));
//
//        for (SearchHit hit : searchResponse.getHits().getHits()) {
//            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
//            sourceAsMap.remove("_class");
////            System.out.println(sourceAsMap);
////            SysLog sysLog = JSONObject.parseObject((String) sourceAsMap.get("content"), SysLog.class);
////            System.out.println(sysLog);
//
//            String content = (String) sourceAsMap.get("content");
//            String replace = content.replace("操", prefix + "操" + suffix);
//            Map<String, Object> stringMap = JSONObject.parseObject(replace, Map.class);
//            HashMap<String, Object> map = new HashMap<>();
//            for (Map.Entry<String, Object> entry : stringMap.entrySet()) {
//                String replace1 = entry.getKey();
//                if (entry.getKey().contains(prefix) && entry.getKey().contains(suffix)) {
//                    replace1 = entry.getKey().replace(prefix, "").replace(suffix, "");
//                }
//                map.put(replace1, entry.getValue());
//            }
//
//            System.out.println(map);
//        }

        //解析结果
//        ArrayList<Map<String, Object>> list = new ArrayList<>();
//        for (SearchHit hit : searchResponse.getHits().getHits()) {
//
//            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
//            HighlightField content = highlightFields.get("content");
//            System.out.println(content);
//            Map<String, Object> sourceAsMap = hit.getSourceAsMap();  //没高亮的数据
//
//            if (content != null) {
//                Text[] fragments = content.fragments();
//                StringBuilder n_title = new StringBuilder();
//                for (Text text : fragments) {
//                    n_title.append(text);
//                }
//                sourceAsMap.put("content", n_title.toString());   //把高亮字段替换掉原本的内容即可
//            }
//            list.add(sourceAsMap);
//        }
//
//
//        List<Map<String, Object>> maps = new ArrayList<>();
//        for (Map<String, Object> stringObjectMap : list) {
//            HashMap<String, Object> hashMap = new HashMap<>();
//            for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
//                if (entry.getKey().equals("_class")) {
//                    continue;
//                }
//
////                if (entry.getKey().contains(prefix) && entry.getKey().contains(suffix)) {
////                    entry.getKey();
////                }
////                SysLog sysLog = JSONObject.parseObject((String) entry.getValue(), SysLog.class);
////                hashMap.put(entry.getKey(), sysLog);
//                System.out.println(entry.getValue());
//            }
//            System.out.println(hashMap);
//        }
//    }
}
