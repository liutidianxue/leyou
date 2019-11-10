package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hewei
 * @date 2019/9/9 - 2:21
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点查询子节点
     * @param pid
     * @return
     */
    public List<Category> queryCategoriesByPid(Long pid) {
         Category record = new Category();
         record.setParentId(pid);
         return this.categoryMapper.select(record);
    }

    public List<String> queryNamesByIds(List<Long> ids){
        List<Category> categories = this.categoryMapper.selectByIdList(ids);
        //组成一个name集合，和vue有一个语法相似
        return categories.stream().map(category -> category.getName()).collect((Collectors.toList()));
    }
}
