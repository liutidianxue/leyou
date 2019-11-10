package com.leyou.search.client;

import com.leyou.item.api.GoodsApi;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hewei
 * @date 2019/9/14 - 0:49
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {


}
