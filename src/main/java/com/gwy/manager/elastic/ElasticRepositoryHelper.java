package com.gwy.manager.elastic;

/**
 * @author Tracy
 * @date 2020/12/18 11:30
 */
//@Component
public class ElasticRepositoryHelper {

//    private static final String LOG_INDEX = "sys_log";
//
//    private static final String PREFIX = "<font color='#dd4b39'>";
//
//    private static final String SUFFIX = "</font>";
//
//    private static final String LOG_CONTENT = "content";
//
//    private static final Integer TIME_OUT_VALUE = 60;
//
//    private static final String CLASS_KEY = "_class";
//
//    private static final String ID = "id";
//
//    private static final String PARAMS = "params";
//
//    private static final String DATA = "data";
//
//    private static final String TYPE = "type";
//
//    private static final String NULL_STRING = "";
//
//    @Autowired
//    private RestHighLevelClient client;
//
//    /**
//     * 从ES中获得含有关键字的日志
//     * @param keyword   关键字
//     * @param pageNum   页号
//     * @param pageSize  页码
//     * @return  结果集
//     */
//    public List<Map<String, Object>> searchByKeyword(String keyword, int pageNum, int pageSize) throws IOException {
//
//        SearchRequest searchRequest = new SearchRequest(LOG_INDEX);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//        //分页处理
//        sourceBuilder.from(pageNum);
//        sourceBuilder.size(pageSize);
//
//        //模糊匹配
//        WildcardQueryBuilder wildcardQuery = QueryBuilders.wildcardQuery(LOG_CONTENT, "*" + keyword + "*");
//        sourceBuilder.query(wildcardQuery);
//        sourceBuilder.timeout(new TimeValue(TIME_OUT_VALUE, TimeUnit.SECONDS));
//
//        //高亮显示
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field(LOG_CONTENT);
//
//        //多个单词高亮的话，需要设置为trues
//        highlightBuilder.requireFieldMatch(false);
//        highlightBuilder.preTags(PREFIX);
//        highlightBuilder.postTags(SUFFIX);
//        sourceBuilder.highlighter(highlightBuilder);
//
//        searchRequest.source(sourceBuilder);
//
//        //获得查询响应
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
//        //获得结果集
//        SearchHit[] searchHits = searchResponse.getHits().getHits();
//
//        if (ArrayUtils.isEmpty(searchHits)) {
//            return null;
//        }
//
//        return doHighlight(keyword, searchHits);
//    }
//
//    /**
//     * 作高亮处理
//     *
//     * @param searchHits  结果集
//     */
//    @SuppressWarnings("unchecked")
//    private List<Map<String, Object>> doHighlight(String keyword, SearchHit[] searchHits) {
//
//        List<Map<String, Object>> resultList = new ArrayList<>();
//
//        for (SearchHit searchHit : searchHits) {
//
//            //存储高亮结果
//            HashMap<String, Object> map = new HashMap<>();
//
//            boolean confirmed = false;
//
//            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
//
//            //优先删除_class相关的key
//            sourceAsMap.remove(CLASS_KEY);
//
//            String content = (String) sourceAsMap.get(LOG_CONTENT);
//
//            //高亮后的结果
//            String highlighted = content.replace(keyword, PREFIX + keyword + SUFFIX);
//            Map<String, Object> stringMap = JSONObject.parseObject(highlighted, Map.class);
//
//            for (Map.Entry<String, Object> entry : stringMap.entrySet()) {
//
//                //结果key
//                String entryKey = entry.getKey();
//
//                if (entryKey.equals(ID)) {
//                    map.put(entryKey, entry.getValue());
//                    continue;
//                }
//
//                //结果value
//                String entryValue = null;
//                if (entry.getValue() instanceof String) {
//                     entryValue = (String) entry.getValue();
//                }
//
//                //如果key中含有关键字，则先替换
//                if (entry.getKey().contains(PREFIX) && entry.getKey().contains(SUFFIX)) {
//                    entryKey = entry.getKey().replace(PREFIX, NULL_STRING).replace(SUFFIX, NULL_STRING);
//                }
//
//                //去除不需要的信息
//                if (entryKey.equals(PARAMS) || entryKey.equals(DATA) || entryKey.equals(TYPE)) {
//                    continue;
//                }
//
//                //符合条件
//                if (entryValue != null && entryValue.contains(PREFIX) && entryValue.contains(SUFFIX)) {
//                    confirmed = true;
//                }
//
//                map.put(entryKey, entryValue);
//            }
//
//            if (confirmed) {
//                resultList.add(map);
//            }
//        }
//
//        return resultList;
//    }
}
