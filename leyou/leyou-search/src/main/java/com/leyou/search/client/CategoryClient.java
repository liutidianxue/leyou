package com.leyou.search.client;

import com.leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author hewei
 * @date 2019/9/14 - 1:13
 */
@FeignClient("item-service")
public interface CategoryClient extends CategoryApi {
}
