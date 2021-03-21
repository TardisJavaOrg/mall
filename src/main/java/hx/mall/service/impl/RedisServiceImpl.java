package hx.mall.service.impl;

import hx.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 *  实现 RedisService
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate strRedisTemplate;

    @Override
    public void set(String key, String value) {
        strRedisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String get(String key) {
        return strRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        return strRedisTemplate.expire(key,expire, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String key) {
        strRedisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return strRedisTemplate.opsForValue().increment(key,delta);
    }
}
