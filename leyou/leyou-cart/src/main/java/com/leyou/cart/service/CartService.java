package com.leyou.cart.service;

import com.leyou.cart.client.GoodsClient;
import com.leyou.cart.interceptor.LoginInterCeptor;
import com.leyou.cart.pojo.Cart;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JsonUtils;
import com.leyou.item.pojo.Sku;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hewei
 * @date 2019/9/21 - 0:56
 */
@Service
public class CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private GoodsClient goodsClient;

    public static final String KEY_PREFFIX = "user:cart:";


    public void addCart(Cart cart) {
        //获取用户信息
        UserInfo userInfo = LoginInterCeptor.getUserInfo();

        //查询购物车记录
        BoundHashOperations<String, Object, Object> hashOperations = this.redisTemplate.boundHashOps(KEY_PREFFIX + userInfo.getId());

        String key = cart.getSkuId().toString();
        Integer num = cart.getNum();
        //判断当前的上是否在购物车中
        if (hashOperations.hasKey(key)) {
            //在，更新数量
            String cartJson = hashOperations.get(key).toString();
            cart = JsonUtils.parse(cartJson, Cart.class);
            cart.setNum(cart.getNum() + num);

        } else {
            //不在，新增购物车
            Sku sku = this.goodsClient.querySkuBuSkuId(cart.getSkuId());

            cart.setUserId(userInfo.getId());
            cart.setTitle(sku.getTitle());
            cart.setOwnSpec(sku.getOwnSpec());
            cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            cart.setPrice(sku.getPrice());
        }
        hashOperations.put(key, JsonUtils.serialize(cart));


    }

    public List<Cart> queryCarts() {
        UserInfo userInfo = LoginInterCeptor.getUserInfo();

        //判断用户是否有购物车记录
        if(!this.redisTemplate.hasKey(KEY_PREFFIX + userInfo.getId())){
            return null;
        }

        //获取用户的购物车记录
        BoundHashOperations<String, Object, Object> hashOperations = this.redisTemplate.boundHashOps(KEY_PREFFIX + userInfo.getId());

        //获取购物车Map中所有Cart值集合
        List<Object> cartsJson = hashOperations.values();

        //如果购物车集合为空，直接返回null
        if(CollectionUtils.isEmpty(cartsJson)){
            return null;
        }

        //把List<Object>集合转化为List<cart>集合
        return cartsJson.stream().map(cartJson -> JsonUtils.parse(cartJson.toString(), Cart.class)).collect(Collectors.toList());
    }

    public void updateNum(Cart cart) {
        UserInfo userInfo = LoginInterCeptor.getUserInfo();

        //判断用户是否有购物车记录
        if(!this.redisTemplate.hasKey(KEY_PREFFIX + userInfo.getId())){
            return;
        }

        Integer num = cart.getNum();

        //获取用户的购物车记录
        BoundHashOperations<String, Object, Object> hashOperations = this.redisTemplate.boundHashOps(KEY_PREFFIX + userInfo.getId());

        String cartJson = hashOperations.get(cart.getSkuId().toString()).toString();

        cart = JsonUtils.parse(cartJson, Cart.class);

        cart.setNum(num);

        hashOperations.put(cart.getSkuId().toString(),JsonUtils.serialize(cart));
    }

    public void deleteCart(String skuId) {
        // 获取登录用户
        UserInfo userInfo = LoginInterCeptor.getUserInfo();
        String key = KEY_PREFFIX + userInfo.getId();
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        hashOps.delete(skuId);
    }
}
