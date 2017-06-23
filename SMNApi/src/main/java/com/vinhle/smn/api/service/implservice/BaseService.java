package com.vinhle.smn.api.service.implservice;

import com.vinhle.smn.api.setting.RedisKeyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by VinhLe on 5/20/2017.
 */
public abstract class BaseService {

    /*----------------------------------- Variable $BaseService ---------------------------------------------*/

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /*----------------------------------- Method $BaseService ---------------------------------------------*/


    protected Object getCache(String key) throws Exception {
        return redisTemplate.opsForValue().get(key);
    }

    protected void writeCache(String key, Object obj) throws Exception {
        writeCache(key, obj, RedisKeyEntity.CACHE_NORMAL_SECONDS);
    }

    protected void writeCache(String key, Object obj, int seconds) throws Exception {
        if ((obj instanceof Serializable || obj instanceof Iterable) && obj != null)
            redisTemplate.opsForValue().set(key, obj, seconds, TimeUnit.SECONDS);
    }

    protected void deleteCache(String pattern) throws Exception {
        redisTemplate.delete(redisTemplate.keys(pattern));
    }

}
