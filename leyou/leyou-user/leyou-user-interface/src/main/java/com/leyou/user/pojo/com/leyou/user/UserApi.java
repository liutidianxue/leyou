package com.leyou.user.pojo.com.leyou.user;

import com.leyou.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hewei
 * @date 2019/9/20 - 1:49
 */
public interface UserApi {

    @GetMapping("query")
    public User queryUser(@RequestParam("username")String username, @RequestParam("password")String password);
}
