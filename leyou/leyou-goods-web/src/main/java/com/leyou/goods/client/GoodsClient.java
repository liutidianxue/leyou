package com.leyou.goods.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author hewei
 * @date 2019/9/14 - 0:49
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {


}
