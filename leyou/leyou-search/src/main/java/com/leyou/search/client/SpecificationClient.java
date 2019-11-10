package com.leyou.search.client;

import com.leyou.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author hewei
 * @date 2019/9/14 - 1:14
 */
@FeignClient("item-service")
public interface SpecificationClient extends SpecificationApi {
}
