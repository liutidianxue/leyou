package com.leyou.auth.client;

import com.leyou.user.pojo.com.leyou.user.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author hewei
 * @date 2019/9/20 - 1:54
 */
@FeignClient("user-service")
public interface UserClient extends UserApi {
}
