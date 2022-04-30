package cn.zq.cache;

import cn.zq.utils.BeanUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

public class RedisCache<k,v> implements Cache<k,v> {
    private String cacheName;
    //private static final String prefix=":";
//    @Autowired
//    RedisTemplate redisTemplate;
    public RedisCache(String cacheName){
        this.cacheName=cacheName;
    }

    @Override
    public v get(k k) throws CacheException {
        //return (v) getRestTemplate().opsForHash().get(cacheName,k.toString());
        System.out.println("获取缓存 k->"+cacheName+" "+k.toString());
        v o = (v) getRedisTemplate().opsForHash().get(cacheName , k.toString());
        System.out.println("得到数据 v->"+o);
        return o;
    }

    @Override
    public v put(k k, v v) throws CacheException {
        System.out.println("进入缓存 k-> "+cacheName+ " "+k.toString());
        System.out.println("进入缓存 v-> "+v.getClass());
        //getRestTemplate().opsForHash().put(cacheName,k.toString(),v);
        getRedisTemplate().opsForHash().put(cacheName,k.toString(),v);
        return v;
    }

    @Override
    public v remove(k k) throws CacheException {
        v o = (v) getRedisTemplate().opsForHash().get(cacheName, k.toString());
        getRedisTemplate().opsForHash().delete(cacheName,k.toString());
        return o;
    }

    @Override
    public void clear() throws CacheException {
        Set keys = getRedisTemplate().opsForHash().keys(cacheName);
        if (null!=keys ||keys.size()>0){
            getRedisTemplate().opsForHash().delete(cacheName);
        }
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(cacheName).intValue();
    }

    @Override
    public Set<k> keys() {
        return getRedisTemplate().opsForHash().keys(cacheName);
    }

    @Override
    public Collection<v> values() {
        return getRedisTemplate().opsForHash().values(cacheName);
    }

    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate= (RedisTemplate) BeanUtils.getBean("shiroRedisTemplate");
        return redisTemplate;
    }
}
