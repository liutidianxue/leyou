package com.leyou.cart.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author hewei
 * @date 2019/9/21 - 1:28
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
