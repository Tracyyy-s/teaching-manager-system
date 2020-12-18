package com.gwy.manager.service;

import com.gwy.manager.entity.SysLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


/**
 * @author Tracy
 * @date 2020/12/18 0:26
 */
public interface SysLogElasticRepository extends ElasticsearchRepository<SysLog, Integer>{

    List<SysLog> findByIpLike(String keyword);
}
