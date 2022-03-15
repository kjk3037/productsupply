package cn.zq.cache;

import cn.zq.utils.BeanUtils;
import org.apache.catalina.core.ApplicationContext;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

public class RedisCache<k,v> implements Cache<k,v> {
    private String cacheName;
    @Autowired
    RedisTemplate redisTemplate;
    public RedisCache(String cacheName){
        this.cacheName=cacheName;
    }

    @Override
    public v get(k k) throws CacheException {
        return (v) getRestTemplate().opsForHash().get(cacheName,k.toString());

    }

    @Override
    public v put(k k, v v) throws CacheException {
        getRestTemplate().opsForHash().put(cacheName,k.toString(),v);
        return null;
    }

    @Override
    public v remove(k k) throws CacheException {
        redisTemplate.opsForHash().delete(cacheName,k.toString());
        return null;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(cacheName);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<k> keys() {
        return null;
    }

    @Override
    public Collection<v> values() {
        return redisTemplate.opsForHash().values(cacheName);
    }

    public RedisTemplate getRestTemplate(){
        RedisTemplate redisTemplate= (RedisTemplate) BeanUtils.getBean("redisTemplate");
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
