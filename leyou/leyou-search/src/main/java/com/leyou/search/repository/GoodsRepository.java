package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author hewei
 * @date 2019/9/14 - 15:39
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
