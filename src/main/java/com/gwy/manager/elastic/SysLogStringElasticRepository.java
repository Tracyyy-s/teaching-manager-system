package com.gwy.manager.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Tracy
 * @date 2020/12/18 16:00
 */
public interface SysLogStringElasticRepository extends ElasticsearchRepository<SysLogString, Integer> {
}
