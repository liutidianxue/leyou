package com.leyou.item.api;

import com.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hewei
 * @date 2019/9/10 - 14:57
 */
@RequestMapping("brand")
public interface BrandApi {



    @GetMapping("{id}")
    public Brand queryBrandById(@PathVariable("id")Long id);
}
